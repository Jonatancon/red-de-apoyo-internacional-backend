package com.pragma.reddeapoyointernacionalbackend.configuration;

import com.pragma.reddeapoyointernacionalbackend.configuration.jwt.JwtEntryPoint;
import com.pragma.reddeapoyointernacionalbackend.configuration.jwt.JwtProvider;
import com.pragma.reddeapoyointernacionalbackend.configuration.jwt.JwtTokenFilter;
import com.pragma.reddeapoyointernacionalbackend.domain.services.DetalleUsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityMain extends WebSecurityConfigurerAdapter {

    private final DetalleUsuarioServices detalleUsuarioServices;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtProvider jwtProvider;

    @Autowired
    public SecurityMain(DetalleUsuarioServices detalleUsuarioServices, JwtEntryPoint jwtEntryPoint,
                        JwtProvider jwtProvider) {
        this.detalleUsuarioServices = detalleUsuarioServices;
        this.jwtEntryPoint = jwtEntryPoint;
        this.jwtProvider = jwtProvider;
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter () {
        return new JwtTokenFilter(jwtProvider, detalleUsuarioServices);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalleUsuarioServices).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
