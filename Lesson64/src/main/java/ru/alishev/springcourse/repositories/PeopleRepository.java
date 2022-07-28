/**
 * Создал Андрей Антонов 26.07.2022 13:00
 **/
package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Person;

import java.util.List;

/** репозиторий для человека */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    /** кастомные методы для сущности человек */
    List<Person> findByName (String name);
    List<Person> findByNameOrderByAge(String name);
    List<Person> findByEmail(String email);
    List<Person> findByNameStartingWith(String startingWith);
    List<Person> findByNameOrEmail(String name, String email); /** вернет всех у которых или имя или email совпал */
}
