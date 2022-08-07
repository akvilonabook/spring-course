package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
/** DAO - Data Access Object */
/** далее будем использовать  это DAO в контроллере чтобы получать людей */
/** этот класс будет общаться со списком и будет извлекать людей из списка
 * находить какого то конкретного человека по id из списка, в дальнейшем будет:
 * добавлять человека в список
 * обновлять человека из списка
 * удалять человека из списка
 * список это условная база данных но обычно в DAO мы общаемся с обычной базой данных
 * пото этот список будет заменен на базу данных  */
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT; /** статическая целочисленная переменная по умолчанию она будет равна нулю */

    /** поля для подключения к базе данных */
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "QQqq1234";

    private static Connection connection;

    static {
        try {
            Class.forName( "org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** списко который будет представлять собой базу даных людей - people
     * этот список параметризован калссом Person о списках и коллекциях подробна рассказано
     * в курсе продвинутая Java  https://youtu.be/QCjLMw988kQ */
    private List<Person> people;

    /** возвращает список из людей и называется index и этот список людей с помощью тайм лив */
    public List<Person> index() {
        List <Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {

                Person person = new Person();

                /** получаем текущее значение id, для строки, на которой мы находимся  */
                /** и это значение мы помещаем в нашего человека person при помощи сеттора */
                person.setId    (resultSet.getInt   ("id"));
                person.setName  (resultSet.getString("name"));
                person.setEmail (resultSet.getString("email"));
                person.setAge   (resultSet.getInt   ("age"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    /** второй метод show возвращает одного человека он принимает на вход id должен
     * найти нашего Person из списка и должен его вернуть */
    public Person show(int id) {
        /** используем лямда выражения ищем человек по id а если мы его не находим то возвращаем null*/
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    /** этот метод ничего не будет возвращать, он просто принимает на вход  объект класса Person
     * и этот объект будет добавлен в наш динамический список. */
    public void save(Person person){
        /** id нашему классу
         * Person формируется здесь */
//        person.setId(++PEOPLE_COUNT); /** при каждом добавление счетчик ID увеличивается на единицу  */

        /** Объект person будет добавлен в наш динамический список.
         * В список people добавляем объект person */
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person Values(" + 1 + ", '" + person.getName() +
                    "'," + person.getAge() + ", '" + person.getEmail() + "')";

            statement.executeUpdate(SQL);
            // INSERT INTO Person VALUES (1, 'Tom', 12, 'asdf@mail.com')
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     /** сохранение донных */
     public void update(int id, Person updatePerson) {
//        Person personToBeUpdated = show (id);
//        personToBeUpdated.setName  (updatePerson.getName());
//        personToBeUpdated.setAge   (updatePerson.getAge());
//        personToBeUpdated.setEmail (updatePerson.getEmail());
     }

//     public void delete(int id) {people.removeIf(p -> p.getId() == id); }
}

