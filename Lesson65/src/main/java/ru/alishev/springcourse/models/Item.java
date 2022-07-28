/**
 * Создал Андрей Антонов 27.07.2022 11:57
 **/
package ru.alishev.springcourse.models;

import org.springframework.web.bind.annotation.GetMapping;
import ru.alishev.springcourse.services.PeopleService;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Item")
public class Item {

    /** описание полей */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не доложно быть пустым")
    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    /** пустой конструктор */
    public Item(){
    }

    /** геттеры и сеттеры */
    public int getId() {
        return id;
    }

    /** геттеры и сеттеры */
    public void setId(int id) {
        this.id = id;
    }

    /** геттеры и сеттеры */
    public String getItemName() {
        return itemName;
    }

    /** геттеры и сеттеры */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /** геттеры и сеттеры */
    public Person getOwner() {
        return owner;
    }

    /** геттеры и сеттеры */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /** вывод в консоль */
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", owner=" + owner +
                '}';
    }
}
