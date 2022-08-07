/**
 * Создал Андрей Антонов 04.07.2022 16:06
 **/
package ru.alishev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

/** класс должен быть помечен как @Component чтобы спринг внедрил PersonDAO за нас */
@Component
public class PersonValidator implements Validator {
    /** обращаемся к базе данных через DAO */
    private final PersonDAO personDAO;

    /** делаем внедрение через конструктор */
    @Autowired
    public PersonValidator( PersonDAO personDAO){
         this.personDAO = personDAO;
    }

    /** на объектах какого класса этот валидатор можно использовать */
    @Override
    public boolean supports(Class<?> aClass) {

        /** проверяем, что наш класс Person */
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        /** Преобразуем наш объект */
        Person person = (Person) o;

         /** нужно посмотреть, есть ли человек с таким же e-Mail */
         if (personDAO.show( person.getEmail(), person.getId()).isPresent()) {

             /** если такой человек есть то выводим ошибку */
             errors.rejectValue("email", "", "This email is already taken");
         }
    }
}
