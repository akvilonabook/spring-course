/**
 * Создал Андрей Антонов 04.08.2022 16:52
 **/
package ru.alishev.springcourse.FirstSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.services.PersonDetailsService;

import java.util.Collection;
import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override   /** здесь лежат логин и пароль -> принципал (объект с данными о пользователе и его аккаунте) */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /** Получаем имя пользователя */
        String username = authentication.getName();

        /** теперь нужно найти с таким именем пользователя в таблице Person */
        personDetailsService.loadUserByUsername(username);

        /** данные с формы ввода пользователя приходят сюда */
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        /** получить пароль в виде строки */
        String password = authentication.getCredentials().toString();

        /** если пароли не совпадают, то мы выбрасываем исключение */
         if (!password.equals(personDetails.getPassword()))
             throw new BadCredentialsException("Incorrict passoword");

         /** возвращаем принципал (описание пользователя), с его паролем, и с пустым списком прав */
        /** если все хорошо, то человек успешно  аутентифицирован */
         return new UsernamePasswordAuthenticationToken(personDetails, password,
                 Collections.emptyList());
    }

    /** Определяет для какого объекта authenticate работает */
    @Override
    public boolean supports(Class<?> authentication) {
        /** логика работы с аутентификацией провайдеров */
        return true;
    }
}
