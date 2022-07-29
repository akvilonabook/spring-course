/**
 * Создал Андрей Антонов 28.07.2022 13:53
 **/
package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** в методе будем получать всех людей и получать их связанные товары */
@Component
public class PersonDAO {

//    private final EntityManager entityManager;
//
//    @Autowired
//    public PersonDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    /** получаем сессию */
//    @Transactional(readOnly = true)
//    public void testNPlus1() {
//        Session session = entityManager.unwrap(Session.class);
//
//        /** в закоментаренном блоке, для каждого человека делается в цикле запрос для получения связанных товаров */
////        /** первый запрос получим всех людей */
////        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
////
////        /** N запросов к Базе Даннх -> получаем связанные товары */
////        for (Person person : people)
////            System.out.println("Person " + person.getName() + " has: " +  person.getItems());
//
//        /** по данному запросу буде делаться выборка из двух таблиц через Hibernate. Обрати внимание на слово FETCH. */
//        Set<Person> people = new HashSet<Person> (session.createQuery("select p from Person p LEFT JOIN FETCH p.items").getResultList());
//
//        /** проходимся по всем людям, но теперь не будут делаться дополнительные запросы по person.getItems()
//         * так как Hibernate понимает, что все записи были подгружены ранее */
//        for (Person person : people)
//            System.out.println("Person " + person.getName() + " has: " +  person.getItems());
//    }
}
