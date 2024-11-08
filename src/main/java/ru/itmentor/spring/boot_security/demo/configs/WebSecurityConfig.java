package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.itmentor.spring.boot_security.demo.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImp userDetailsService;
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
    //        .and() // Включите CSRF защиту
            .authorizeRequests()
            .antMatchers("/register","/api/login","/api/register").permitAll() // Разрешите доступ к регистрации и логауту
            .antMatchers("/api/users/**").hasRole("ADMIN") // Доступ для администраторов
            .antMatchers("/admin/**").hasRole("ADMIN") // Доступ для администраторов
            .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ для пользователей и администраторов
            .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            .and()
            .formLogin().successHandler(successUserHandler).permitAll()
            .loginPage("/login")// Разрешите доступ к форме входа для всех
            .and()
            .logout()
            .logoutUrl("/logout") // URL для логаута
            .logoutSuccessUrl("/login?logout") // Перенаправление после успешного логаута
            .invalidateHttpSession(true) // Уничтожить сессию
            .deleteCookies("JSESSIONID") // Удалить куки сессии
            .permitAll()
            .and()
            .httpBasic();// Разрешите доступ к логауту для всех
    //http.sessionManagement()
    //        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); // Ожидание сессии для всех запросов

}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}