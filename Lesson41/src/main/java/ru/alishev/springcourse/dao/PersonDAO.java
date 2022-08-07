package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

    /** метод для проверки наличия указанного e-mail у пользователя
     * (если такой e-mail уже есть, то при вставке записи произойдет ошибка)
     * метод вернет человека с указанным e-mail если он есть в базе денных */
    public Optional <Person> show (String email) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE email = ?",
                new Object[] {email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    /**  метод show возвращает одного человека он принимает на вход id должен
     * найти нашего new Person[] возвращать из базы данных и отображать на странице */
    public Optional<Person> show(int id) {

        /** возвращаем найденного человека */
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?;",
                new Object[]{id},
                new  BeanPropertyRowMapper<>(Person.class)).stream().findAny();
/*  можно так:  new Object[]{id}, new PersonMapper ()).stream().findAny().orElse(new Error ("Человек с таким id не найден")); */
    }

    /** сохранение данных, метод ничего не будет возвращать,
     * он принимает на вход объект класса Person и этот объект
     * будет добавлен в наш динамический список. */
    public void save(Person person){
         jdbcTemplate.update("INSERT INTO Person (name, age, email) VALUES (?, ?, ?);",
                 person.getName(), person.getAge(), person.getEmail());
    }
    /** сохранение донных */
    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email = ? WHERE id = ?;",
                updatePerson.getName(),
                updatePerson.getAge(),
                updatePerson.getEmail(),  
                id);
    }
    /** метод удаления записи человека */
    public void delete (int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);

    }

    ///////////////////////////////////////////////////
    // Тестируем производительность пакетной вставки //
    ///////////////////////////////////////////////////
    public void testMultipleUpdate (){
        List <Person> people = create1000People();

        /** получаем текущее время в миллисекундах (засекаем время)*/
        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person VALUES (?, ?, ?, ?);",
                    person.getId(), person.getName(), person.getAge(), person.getEmail());
        }

        /** получаем текущее время в миллисекундах (конечное время)*/
        long аfter = System.currentTimeMillis();
        System.out.println("Time: " + (аfter - before));
     }

     public void testBatchUpdate() {
        /** объявляем список из людей */
        List <Person> people = create1000People();

        /**  замеряем время работы до вставки */
        long before = System.currentTimeMillis();

        /** каждый раз мы будем идти по списку людей и вставлять в этот запрос значения для каждого человека */
        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES (?, ?, ?, ?);",

                /** заполняем вставку */
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {

                        /** обратимся к нашему preparedStatement */
                        preparedStatement.setInt (      1, people.get(i).getId());
                        preparedStatement.setString (   2, people.get(i).getName());
                        preparedStatement.setInt (      3, people.get(i).getAge());
                        preparedStatement.setString (   4, people.get(i).getEmail());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });

         /**  замеряем время работы после вставки */
         long after = System.currentTimeMillis();

         /** выводим в консоль время отработки данного кусочка кода */
         System.out.println("Time: " + (after - before));

     }

    private List <Person> create1000People() {
        List <Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name" + i, 30, "test" + i + "@mail.ru"));
        }
        return people;
    }
}

