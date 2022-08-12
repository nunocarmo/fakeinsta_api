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

    public SecurityConfiguration(UserAuthDetailsService userAuthDetailsService, IUserRepository userRepository) {
        this.userAuthDetailsService = userAuthDetailsService;
        this.userRepository = userRepository;
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
                .and()
                //add jwt filters (1st. authentication, 2nd. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
                .authorizeRequests()
                //configure access rules
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
               /* .antMatchers(HttpMethod.POST, "/api/v1/staff/students").permitAll()
                .antMatchers("/api/v1/students/**").hasAnyRole("STUDENT", "STAFF")
                .antMatchers("/api/v1/teachers/**").hasAnyRole("TEACHER", "STAFF")
                .antMatchers("/api/v1/staff/**").hasRole("STAFF")*/
                .and()
                .httpBasic();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userAuthDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}