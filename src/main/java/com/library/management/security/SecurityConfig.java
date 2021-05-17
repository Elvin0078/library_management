package com.library.management.security;
import com.library.management.util.Md5Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public CustomUserDetailsService customUserDetailsService;

    public Md5Generator md5Generator;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, Md5Generator md5Generator) {
        this.customUserDetailsService = customUserDetailsService;
        this.md5Generator = md5Generator;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
//                .antMatchers("/api/**")
                .antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/ui/login")
                .defaultSuccessUrl("/ui/success", true)
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/ui/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
                http.csrf().disable();
//        http.headers().frameOptions().disable();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(getEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {

                return md5Generator.generateMd5(rawPassword.toString());
            }


            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return md5Generator.generateMd5(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }















































}
