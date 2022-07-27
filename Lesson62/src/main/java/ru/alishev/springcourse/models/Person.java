package ru.alishev.springcourse.models;

/**
 * @author Neil Alishev
 */

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/** это человек у человека есть id уникальный идентификатор   */
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /** у человека есть имя */
    /**@ аннотациями устанавливаем валидацию полей */
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Возраст не должен быть меньше 0")
    @Column(name = "age")
    private int age;

    @Column (name = "email")
    @NotEmpty(message = "E-mail не должен быть пустым")
    @Email
    private String email;

    /** список товаров человека */
    @OneToMany(mappedBy = "owner")
    private List<Item> items;

    /** создаем пустой конструктор */
    public Person () {
    }

    /** создаем конструктор */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /** сгенерированы геттеры и сеттеры */
    public int getId() {
        return id;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setId(int id) {
        this.id = id;
    }

    /** сгенерированы геттеры и сеттеры */
    public String getName() {
        return name;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setName(String name) {
        this.name = name;
    }

    /** сгенерированы геттеры и сеттеры */
    public int getAge() {
        return age;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setAge(int age) {
        this.age = age;
    }

    /** сгенерированы геттеры и сеттеры */
    public String getEmail() {
        return email;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setEmail(String email) {
        this.email = email;
    }

    /** сгенерированы геттеры и сеттеры */
    public List<Item> getItems() {
        return items;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
