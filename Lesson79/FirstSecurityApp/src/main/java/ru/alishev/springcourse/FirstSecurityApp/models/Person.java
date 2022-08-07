/**
 * Создал Андрей Антонов 04.08.2022 14:47
 **/
package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/** @Entity - сущность */
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым ")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длинной")
    @Column(name = "username")
    private String username;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    @Column(name = "year_of_birth")
    private int year_of_birth;

    @Column(name = "passvord")
    private String passvord;

    /** конструктор по умолчанию */
    Person () {
    }

    /** конструктор для двух полей */
    public Person(String username, int year_of_birth) {
        this.username = username;
        this.year_of_birth = year_of_birth;
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
    public String getUsername() {
        return username;
    }

    /** геттеры и сеттеры */
    public void setUsername(String username) {
        this.username = username;
    }

    /** геттеры и сеттеры */
    public int getYear_of_birth() {
        return year_of_birth;
    }

    /** геттеры и сеттеры */
    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    /** геттеры и сеттеры */
    public String getPassvord() {
        return passvord;
    }

    /** геттеры и сеттеры */
    public void setPassvord(String passvord) {
        this.passvord = passvord;
    }

    /** Вывод информации в консоль объекты класса Person */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", year_of_birth=" + year_of_birth +
                ", passvord='" + passvord + '\'' +
                '}';
    }
}
