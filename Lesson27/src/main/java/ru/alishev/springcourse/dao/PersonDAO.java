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
                /** и это значение мы помещаем в нашего человека person при помощи сеттора    */
                person.setId    (resultSet.getInt   ("id"));    /** идентификатор   */
                person.setName  (resultSet.getString("name"));  /** имя             */
                person.setEmail (resultSet.getString("email")); /** E-mail          */
                person.setAge   (resultSet.getInt   ("age"));   /** возраст         */

                /** добавляем человека  */
                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    /**  метод show возвращает одного человека он принимает на вход id должен
     * найти нашего Person возвращать из базы данных и отображать на странице */
    public Person show(int id) {

        /** объявим указатель на класс Person */
        Person person = null;
        try {
            /** делаем запрос к базе данных для получения полей одного пользователя по id */
            PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from Person where id = ?;");

            /** устанавливаем параметр в запрос строчкой выше connection.prepareStatement ()*/
            preparedStatement.setInt(1, id);

            /** выполняем запрос на Базе Данных */
            ResultSet resultSet = preparedStatement.executeQuery();

            /** получаем ПЕРВУЮ строчку из ResultSet(а) так как пока мы не умеем создавать уникальный id */
            resultSet.next();

            /** сошлем указатель на новый объект Person */
            person = new Person();

            /** устанавливаем в объекте Person все поля пришедшие из базы */
            person.setId(       resultSet.getInt(   "id"));     /** идентификатор записи */
            person.setName(     resultSet.getString("name"));   /** имя */
            person.setEmail(    resultSet.getString("email"));  /** Е-Mail */
            person.setAge(      resultSet.getInt(   "age"));    /** возраст */

        /** проверка на исключение */
        } catch (SQLException e) {

            /** сообщение об ошибке */
            throw new RuntimeException(e);
        }

        /** возвращаем найденного человека */
        return person;
    }

    /** сохранение данных, метод ничего не будет возвращать,
     * он принимает на вход объект класса Person и этот объект
     * будет добавлен в наш динамический список. */
    public void save(Person person){
        try {
        /** создадим объект PreparedStatement (позволяющий избежать SQL инъекции) на объекте Connection */
        PreparedStatement preparedStatement =       /** всталяем ? - имя, ? - возраст, ? - E-Mail */
                 connection.prepareStatement("INSERT INTO Person VALUES (1,?,?,?)");

        /** проставляем поля для вставки в базу данных, это делаем через класс connection.prepareStatement () */
        preparedStatement.setString (1, person.getName());  /** имя     */
        preparedStatement.setInt    (2, person.getAge());   /** возраст */
        preparedStatement.setString (3, person.getEmail()); /** E-mail  */

        /** вставляем строчку данных в базу через SQL */
        preparedStatement.executeUpdate();

        /** проверка на исключение */
        } catch (SQLException e) {

            /** сообщение об ошибке */
            throw new RuntimeException(e);
        }
    }
    /** сохранение донных */
    public void update(int id, Person updatePerson) {
        /** метод будет обновлять человека, обновлять значения строки в Базе данных */
        try {
            /** создаем запрос для обновления человека */
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person " +
                            "                        SET name   = ?," +
                            "                            age    = ?," +
                            "                            email  = ?" +
                            "                       WHERE " +
                            "                            id     = ?");

            /** проставляем значения ? в UPDATE Person */
            preparedStatement.setString (1, updatePerson.getName());  /** имя           */
            preparedStatement.setInt    (2, updatePerson.getAge());   /** возраст       */
            preparedStatement.setString (3, updatePerson.getEmail()); /** E-mail        */
            preparedStatement.setInt    (4, updatePerson.getId());    /** идентификатор */

            /** выполняем SQL обновление базы данных */
            preparedStatement.executeUpdate();

            /** обрабатываем ошибки */
        } catch (SQLException e) {

            /** сообщение об ошибке */
            throw new RuntimeException(e);
        }
    }
    /** метод удаления записи человека */
    public void delete (int id) {

        try {
            /** создаем запрос для удаления человека */
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id = ?;");

            /** проставляем значения ? в DELETE FROM Person */
            preparedStatement.setInt    (1, id) ;     /** идентификатор */

            /** выполняем SQL удаление из базы данных */
            preparedStatement.executeUpdate();

            /** обрабатываем ошибки */
        } catch (SQLException e) {

            /** сообщение об ошибке */
            throw new RuntimeException(e);
        }
    }
}

