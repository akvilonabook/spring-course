/**
 * Создал Андрей Антонов 04.08.2022 17:15
 **/
package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    /** загружает пользователя по имени пользователя */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person =  peopleRepository.findByUsername(username);

        if (person.isEmpty())
            /** вызываем исключение если пользователь не найден */
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }
}
