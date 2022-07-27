/**
 * Создал Андрей Антонов 26.07.2022 13:05
 **/
package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    /** конструктор */
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    /** метод возвращает всех людей */
    public List<Person> findAll() {
        return this.peopleRepository.findAll();
    }

    /** метод возвращает одного человека по его id
     * так как в PeopleRepository указан идентификатор Person как <Integer>
     * public interface PeopleRepository extends JpaRepository<Person, Integer> {*/
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);

        return foundPerson.orElse(null);
    }

    /** Сохраняем человека */
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
     }

     /** обновление человека */
     @Transactional
    public void update (int id, Person updatePerson) {
         /** для того чтобы эта запись обновилась, (а не вставилась как новая) проставляем ей id текущей записи */
         updatePerson.setId(id);
         peopleRepository.save(updatePerson);
     }

     /** Удаляем человека по его ID */
     @Transactional
    public void delete(int id) {
         peopleRepository.deleteById(id);
     }

    /** метод для тестирования методов с помощью дебагера repositories.PeopleRepository()*/
    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate Transaction");
    }
}
