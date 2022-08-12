package com.insta.api.insta.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.insta.api.insta.exception.BadRequestException;
import com.insta.api.insta.persistence.model.user.User;
import com.insta.api.insta.persistence.repository.user.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final IUserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  IUserRepository userRepository) {

        super(authenticationManager);
        this.userRepository = userRepository;
    }

    //Ativado sempre que houver um request para fazer a authorization
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //Read the Authorization header, where the JWT token should be
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        //If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        //If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);

        if (token != null) {
            //Parse the token and validate it
            String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            //Search in the DB if we find the user by token subject (username)
            //If so, then grab user details and create Spring Auth Token using username, pass, authorities/roles
            if (email != null) {
                User user = this.userRepository.findByEmail(email)
                        .orElseThrow(() -> new BadRequestException("Email not found"));

                UserAuth principal = UserAuth.builder()
                        .user(user)
                        .build();

                return new UsernamePasswordAuthenticationToken(email,
                        null, principal.getAuthorities());
            }
            return null;
        }
        return null;
    }
}