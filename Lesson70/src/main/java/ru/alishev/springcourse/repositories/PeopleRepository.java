/**
 * Создал Андрей Антонов 26.07.2022 13:00
 **/
package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Person;

import java.util.Optional;

/** репозиторий для человека */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    /** метод прописан в документации Hibernate для поиска имени по любой части */
    Optional<Person> findByFullName(String fullName);
}
