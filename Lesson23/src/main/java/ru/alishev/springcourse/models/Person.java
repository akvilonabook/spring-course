package ru.alishev.springcourse.models;

/**
 * @author Neil Alishev
 */

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/** это человек у человека есть id уникальный идентификатор   */
public class Person {
    private int id;
    /** у человека есть имя */
    private String name;

    /** создаем пустой конструктор */
    public Person () {
    }

    /** создаем конструктор */
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /** сгенерированы геттроы и сетторы */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
