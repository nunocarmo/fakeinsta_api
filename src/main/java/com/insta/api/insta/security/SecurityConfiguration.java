package com.insta.api.insta.security;

import com.insta.api.insta.persistence.repository.user.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserAuthDetailsService userAuthDetailsService;
    private IUserRepository userRepository;

    private final PasswordEncoder encoder;

    public SecurityConfiguration(UserAuthDetailsService userAuthDetailsService, IUserRepository userRepository, PasswordEncoder encoder) {
        this.userAuthDetailsService = userAuthDetailsService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //Permite usar outros métodos para além do GET (csrf útil em form based authentication, em jwt não é útil)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //State não é útil em jwt, mas sim em form based
                .and().cors()
                .and()
                //add jwt filters (1st. authentication, 2nd. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                //configure access rules
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
                .antMatchers("/api/v1/user/admin/**").hasRole("admin")
                .antMatchers("/api/v1/post/admin/**").hasRole("admin")
                .antMatchers("/api/v1/comment/admin/**").hasRole("admin")
                .antMatchers("/v2/api-docs", "/swagger-resources", "/swagger-resources/",
                        "/configuration/ui", "/configuration/security", "/swagger-ui.html",
                        "/webjars/", "/v3/api-docs/", "/swagger-ui/").permitAll()
                .anyRequest().authenticated();

    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(this.userAuthDetailsService);

        return daoAuthenticationProvider;
    }

}
