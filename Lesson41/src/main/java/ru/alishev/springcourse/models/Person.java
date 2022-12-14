package ru.alishev.springcourse.models;

/**
 * @author Neil Alishev
 */

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/** это человек у человека есть id уникальный идентификатор   */
public class Person {
    private int id;
    /** у человека есть имя */
    /**@ аннотациями устанавливаем валидацию полей */
    @NotEmpty(message = "Name should not be empty")
    @Size(min =2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email ( message = "Email should be valid" )
    private String email;

    /** создаем пустой конструктор */
    public Person () {
    }
/**
    create table  Person
    (
            id    int generated by default as identity primary key,
            name  varchar not null,
            age   int check (age > 0),
            email varchar UNIQUE
    );
*/
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

    //    public int getEmail() {
//        return email;
//    }

    public void setEmail(String email) {
        this.email = email;
    }
}
