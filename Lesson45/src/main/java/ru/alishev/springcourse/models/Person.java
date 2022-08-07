package ru.alishev.springcourse.models;

/**
 * @author Neil Alishev
 */

import javax.validation.constraints.*;

/** это человек у человека есть id уникальный идентификатор   */
public class Person {

    /** идентификатор записи человека */
    private int id;

    /** у человека есть имя */
    /**@ аннотациями устанавливаем валидацию полей т.е. правила заполнения  */
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длинной")
    private String fullName;

    @Min(value = 1000, message = "Год рождения должен быть больше чем 1000")
    private int yearOfBirth;

    /** создаем пустой конструктор, чтобы использовать в спринг */
    public Person () {
    }

    /** конструктор */
    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
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
    public String getFullName() {
        return fullName;
    }

    /** геттеры и сеттеры */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /** геттеры и сеттеры */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /** геттеры и сеттеры */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}