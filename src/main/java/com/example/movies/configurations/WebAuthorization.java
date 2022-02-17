package com.example.movies.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // JWTAuthenticationFilter jwtAuthenticationFilter =new JWTAuthenticationFilter(authenticationManagerBean());
       // jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        http.authorizeRequests()
                .antMatchers(  "/sendMail/**", "/actors/**", "/users/**","/genres/**","/movies/**", "/directors/**", "rest/**").permitAll()
                /*.antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/actors/**", "/users/**","/genres/**","/movies/**", "/directors/**").permitAll()
                .antMatchers(HttpMethod.POST, "/actors/**", "/users/**","/genres/**","/movies/**", "/directors/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/movies/**","/actors/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/actors/**", "/users/**","/genres/**","/movies/**", "/directors/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/rest/**").hasAuthority("ROLE_ADMIN")*/
                .antMatchers(HttpMethod.DELETE, "/actors/**", "/users/**","/genres/**","/movies/**", "/directors/**").permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login")
                .successHandler((req, res, auth) -> clearAuthenticationAttributes(req))
                .failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement()
                //.invalidSessionUrl("/login.html")
                .maximumSessions(2)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());

        http.cors();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            //res.sendRedirect("/login.html");
        });
        //http.addFilter(jwtAuthenticationFilter);
        http.addFilter(new JWTAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}