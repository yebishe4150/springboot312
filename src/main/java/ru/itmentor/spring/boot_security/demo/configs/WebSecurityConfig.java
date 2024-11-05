package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.itmentor.spring.boot_security.demo.service.UserDetailsServiceImp;

@Configuration
@EnableWebSecurity
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
            .antMatchers("/register").permitAll() // Разрешите доступ к регистрации и логауту
            .antMatchers("/admin/**").hasRole("ADMIN") // Доступ для администраторов
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ для пользователей и администраторов
            .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            .and()
            .formLogin() // Используйте форму по умолчанию
            .permitAll()
            .and()
            .formLogin().successHandler(successUserHandler)
            .loginPage("/login")// Разрешите доступ к форме входа для всех
            .and()
            .logout()
            .logoutUrl("/logout") // URL для логаута
            .logoutSuccessUrl("/login?logout") // Перенаправление после успешного логаута
            .invalidateHttpSession(true) // Уничтожить сессию
            .deleteCookies("JSESSIONID") // Удалить куки сессии
            .permitAll(); // Разрешите доступ к логауту для всех
}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}