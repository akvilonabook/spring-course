package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Neil Alishev
 */
/** DAO - Data Access Object */
/** далее будем использовать это DAO в контроллере, чтобы получать людей */
/** этот класс будет общаться со списком и будет извлекать людей из списка
 * находить какого то конкретного человека по id из списка, в дальнейшем будет:
 * добавлять человека в список
 * обновлять человека из списка
 * удалять человека из списка
 * список это условная база данных? но обычно в DAO мы общаемся с обычной базой данных
 * потом этот список будет заменен на базу данных  */
@Component
public class PersonDAO {
    /** создаем поле которое является JDBC Template */
    private final JdbcTemplate jdbcTemplate;

    /** присваиваем jdbcTemplate конфигурацию из SpringConfig.jdbcTemplate () -> параметры соединения с базой данных */
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /** возвращает список из людей и называется index и этот список людей с помощью тайм лив */
    public List<Person> index() {
        /** каждая строка из этого SQL запроса попадает в класс PersonMapper */
/**        return jdbcTemplate.query("SELECT * FROM Person;", new PersonMapper())*/
/** так как поля в классе PersonMapper называются так же как их сеттеры (setId, setName, setEmail...),
 * то можно использовать BeanPropertyRowMapper() и поэтому
 * вместо new PersonMapper ()) мы написали new BeanPropertyRowMapper<>(Person.class)) */
        return jdbcTemplate.query("SELECT * FROM Person;", new BeanPropertyRowMapper<>(Person.class));
    }

    /**  метод show возвращает одного человека он принимает на вход id должен
     * найти нашего new Person[] возвращать из базы данных и отображать на странице */
    public Person show(int id) {
        /** возвращаем найденного человека */
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?;", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        /*  можно так:  new Object[]{id}, new PersonMapper ()).stream().findAny().orElse(new Error ("Человек с таким id не найден")); */
    }

    /** сохранение данных, метод ничего не будет возвращать,
     * он принимает на вход объект класса Person и этот объект
     * будет добавлен в наш динамический список. */
    public void save(Person person){
         jdbcTemplate.update("INSERT INTO Person (full_name, year_of_birth) VALUES (?, ?);",
                 person.getFullName(), person.getYearOfBirth());
    }
    /** сохранение донных */
    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET full_name = ?, year_of_birth = ? WHERE id = ?;",
                updatePerson.getFullName(), updatePerson.getYearOfBirth(), id);
    }
    /** метод удаления записи человека */
    public void delete (int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    /** Постгресс будет искать человека по его ФИО и если мы нашли, то в валидаторе мы будем выбрасывать ошибку.
     * Для валидации уникальности ФИО, мы в валидаторе используем -> util\PersonValidator.validate() */
    public Optional <Person> getPersonByFullName (String fullName ) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?",
                new Object[] {fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    /** Этот метод нужен для страницы человека, для того чтобы показать список книг
     * этого человека. Здесь Join не нужен. И так уже получили человека с помощью отдельного метода */
    public List<Book> getBookByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
