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
 //   private final SuccessUserHandler successUserHandler;
    private final UserDetailsServiceImp userDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsServiceImp userDetailsService) {
    //    this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/register", "/logout").permitAll() // Разрешаем доступ ко всем этим URL
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                .formLogin()
                .loginPage("/login") // Указываем страницу логина
                .permitAll() // Разрешаем доступ ко всем
                .and()
                .logout()
                .logoutUrl("/logout") // URL для логаута
                .logoutSuccessUrl("/login?logout") // Перенаправление после успешного логаута
                .invalidateHttpSession(true) // Уничтожаем сессию
                .deleteCookies("JSESSIONID") // Удаляем куки сессии
                .permitAll(); // Разрешаем доступ ко всем
    }
*/
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login","/register","logout").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // доступ для USER и ADMIN
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
*/
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
    //        .and() // Включите CSRF защиту
            .authorizeRequests()
            .antMatchers("/register", "/logout").permitAll() // Разрешите доступ к регистрации и логауту
            .antMatchers("/admin/**").hasRole("ADMIN") // Доступ для администраторов
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ для пользователей и администраторов
            .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            .and()
            .formLogin() // Используйте форму по умолчанию
            .permitAll() // Разрешите доступ к форме входа для всех
            .and()
            .logout()
            .logoutUrl("/logout") // URL для логаута
            .logoutSuccessUrl("/login?logout") // Перенаправление после успешного логаута
            .invalidateHttpSession(true) // Уничтожить сессию
            .deleteCookies("JSESSIONID") // Удалить куки сессии
            .permitAll(); // Разрешите доступ к логауту для всех
}
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/login").permitAll()  // разрешение на доступ к /login
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()  // использование HTTP Basic аутентификации вместо формы
                .and()
                        .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
*/

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Отключение CSRF для API-запросов
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()  // Разрешение на доступ к API
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/login").permitAll()  // разрешение на доступ к /login
                .anyRequest().authenticated()
                .and()
                .httpBasic();  // Использование HTTP Basic аутентификации вместо формы
    }
*/

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .permitAll();
    }
*/
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
*/
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
*/
    /*
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
*/

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

/*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


  /*
    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
*/
}