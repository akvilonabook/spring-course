/**
* Создал Андрей Антонов 04.08.2022 16:08
**/
package ru.alishev.springcourse.FirstSecurityApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.alishev.springcourse.FirstSecurityApp.repositories.models.Person;

import java.util.Collection;

public class PersonDetails implements UserDetails {

    /** Основная переменная */
    private final Person person;

    /** конструктор по умолчанию */
    public PersonDetails(Person person) {
        this.person = person;
    }

    /** наследуемые методы от implements UserDetails */
    /** нужен для авторизации прав пользователя */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; /** будем получать роли пользователя из БД */
    }

    /** наследуемые методы от implements UserDetails */
    @Override
    public String getPassword() {
        return this.person.getPassvord();
    }

    /** наследуемые методы от implements UserDetails */
    @Override
    public String getUsername() {
        return this.person.getUsername();
    }

    /** наследуемые методы от implements UserDetails */
    /** аккаунт не просрочен он работает  = true */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** наследуемые методы от implements UserDetails */
    /** этот аккаунт не заблокирован он работает = true*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** наследуемые методы от implements UserDetails */
    /** пароль не просрочен он работает = true*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** наследуемые методы от implements UserDetails */
    /** аккаунт включен он работает = true*/
    @Override
    public boolean isEnabled() {
        return true;
    }

    /** создаем свой метод, нужно, чтобы получать данные аутентифицированного пользователя */
    public Person getPerson() {
        return this.person;
    }
}
