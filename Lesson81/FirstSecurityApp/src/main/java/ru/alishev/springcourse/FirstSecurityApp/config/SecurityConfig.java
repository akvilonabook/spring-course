/**
 * Создал Андрей Антонов 04.08.2022 16:43
 **/
package ru.alishev.springcourse.FirstSecurityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

/** конфигурационный класс для спринг секюрити  */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    /** Конфигурируем сам спринг секюрити
     * и конфигурируем авторизацию - давать доступ
     * к страницам пользователя на основание его статуса.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        /** Если оставить только эти четыре строки,
         * то они отключают аутентификацию и позволят
         * пользователю входить без пароля
         * */
//        http.formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/hello", true)
//                .failureUrl("/auth/login?error");

        http.csrf().disable() // Отключаем защиту от меж сайтовой подделки запросов
            .authorizeRequests()
            .antMatchers("/auth/login", "error" ).permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin().loginPage("/auth/login")
        .loginProcessingUrl("/process_login")
        .defaultSuccessUrl("/hello", true)
        .failureUrl("/auth/login?error");
   }

    /** настраивает аутентификацию пользователей */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
