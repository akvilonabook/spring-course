package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        /** получаем конфигурацию из файла resources/hibernate.properties
         * из класса org.hibernate.cfg.Configuration; и этой конфигурации
         * передаем класс, который является нашей сущностью Person().
         * Помечен аннотацией @Entity и как следствие в базе данных есть таблица person
         * с указанными полями */
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        /** Получаем сейшен фактори, чтобы из него получить сессию для работы с hibernate */
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        /** получаем сессию для работы с hibernate */
        Session session = sessionFactory.getCurrentSession();

        /** блок теоретически может выдать исключение поэтому оборачиваем его в блок try {} */
        try {

            /** начинаем транзакцию */
            session.beginTransaction();

            /** получим человека с первичным ключем  id = 1 с помощью hibernate  */
            Person person = session.get(Person.class, 1);

            /** Выводим на экран имя и возраст человека */
            System.out.println(person.getName());
            System.out.println(person.getAge());

            /** закрываем транзакцию */
            session.getTransaction().commit();
        } finally {

            /** закрываем сейшен фактори и этот код будет в любом случае выполнен  */
            sessionFactory.close();
        }

    }
}
