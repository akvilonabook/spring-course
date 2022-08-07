/**
 * Создал Андрей Антонов 24.06.2022 15:48
 **/
package ru.alishev.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.alishev.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

/** вызывается из PersonDAO.List<Person> index() */

public class PersonMapper implements RowMapper<Person> {
    /** мы должны получить строки и положить ее в класс Person */
    @Override
    public Person  mapRow(ResultSet resultSet, int i) throws SQLException {

        /** сошлем указатель на новый объект Person */
        Person person = new Person();

        /** устанавливаем в объекте Person все поля пришедшие из базы */
        person.setId(       resultSet.getInt(   "id"));     /** идентификатор записи */
        person.setName(     resultSet.getString("name"));   /** имя */
        person.setEmail(    resultSet.getString("email"));  /** Е-Mail */
        person.setAge(      resultSet.getInt(   "age"));    /** возраст */

        return person;
    }
 }
