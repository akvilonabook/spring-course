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

    private int age;

    private String email;

    /** создаем пустой конструктор */
    public Person () {
    }

    public Person(int id, String name, int age, String email) {
        this.id     = id;
        this.name   = name;
        this.age    = age;
        this.email  = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
