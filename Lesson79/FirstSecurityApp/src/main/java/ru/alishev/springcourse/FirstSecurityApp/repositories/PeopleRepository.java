/**
 * Создал Андрей Антонов 04.08.2022 17:07
 **/
package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository <Person, Integer>{

    /** Spring DATA JPA возвращает такого пользователя из базы данных из таблицы Person */
    Optional<Person> findByUsername(String username);
}
