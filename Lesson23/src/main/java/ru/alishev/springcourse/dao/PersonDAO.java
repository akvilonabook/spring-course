package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

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

    /** списко который будет представлять собой базу даных людей - people
     * этот список параметризован калссом Person о списках и коллекциях подробна рассказано
     * в курсе продвинутая Java  https://youtu.be/QCjLMw988kQ */
    private List<Person> people;

    /** в DAO создадим несколько людей */
    {

        people = new ArrayList<>(); /** динамисечкий список пустой */
        /** создаем несколько людей тогда мы сможем вызывать людей из списка обращаясь к их id */
        people.add(new Person(++PEOPLE_COUNT, "Tom")); /** ++PEOPLE_COUNT = 1 */
        people.add(new Person(++PEOPLE_COUNT, "Bob")); /** ++PEOPLE_COUNT = 2 */
        people.add(new Person(++PEOPLE_COUNT, "Mike")); /** ++PEOPLE_COUNT = 3 */
        people.add(new Person(++PEOPLE_COUNT, "Katy")); /** ++PEOPLE_COUNT = 4 */
    }

    /** возвращает список из людей и называется index и этот список людей с помощью тайм лив */
    public List<Person> index() {
        return people;
    }

    /** второй метод show возвращает одного человека он принимает на вход id должен
     * найти нашего Person из списка и должен его вернуть */
    public Person show(int id) {
        /** используем лямда выражения ищем человек по id а если мы его не находим то возвращаем null*/
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    /** этот метод ничего не будет возвращать, он просто принимает на вход объект класса Person
     * и этот объект будет добавлен в наш динамический список. */
    public void save(Person person){
        /** id нашему классу персон будем
         *
         * в форме которая будет в файле people/new мы не будем сами указывать ID человека
         * этот ID должен генерироваться динамически при добавлении в базу данных
         * поэтому в форме new.html есть только одно поле name а ID для класса
         * Person формируется динамически здесь */
        person.setId(++PEOPLE_COUNT); /** при каждом добавление счетчик ID увеличивается на единицу  */

        /** объект person будет добавлен в наш динамический список.
         * В список people добавляем объект person */
        people.add(person);
     }
}

