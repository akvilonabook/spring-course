/**
 * Создал Андрей Антонов 13.07.2022 11:17
 **/
package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Person") /** указываем что таблица в базе данных называется Person */
public class Person {

    /** класс должен содержать пустой конструктор и должно быть поле с аннотацией @Id */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    /** класс должен содержать пустой конструктор */
    public Person() {}

    /** конструктор со всем полями */
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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
    public String getName() {
        return name;
    }

    /** геттеры и сеттеры */
    public void setName(String name) {
        this.name = name;
    }

    /** геттеры и сеттеры */
    public int getAge() {
        return age;
    }

    /** геттеры и сеттеры */
    public void setAge(int age) {
        this.age = age;
    }
}
