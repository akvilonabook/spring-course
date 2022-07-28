/**
 * Создал Андрей Антонов 27.07.2022 12:11
 **/
package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Item;
import ru.alishev.springcourse.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    /**
     * метод получающий список товаров по имени их владельца
     */
    List<Item> findByItemName(String itemName);

    /** метро получающий список товаров по владельцу
     * (какая разница между этим и строчкой выше пока не понимаю)
     * person.getItems() тоже самое, что этот метод */
    List<Item> findByOwner(Person owner);

}
