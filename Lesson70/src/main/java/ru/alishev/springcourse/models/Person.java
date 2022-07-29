package ru.alishev.springcourse.models;

/**
 * @author Neil Alishev
 */

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/** это человек у человека есть id уникальный идентификатор   */
@Entity
@Table(name = "Person")
public class Person {

    /** поле таблицы "Person" */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** поле таблицы "Person" */
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "full_name")
    private String fullName;

    /** поле таблицы "Person" */
    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    /** список книг человека */
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    /** создаем пустой конструктор */
    public Person () {
    }

    /** создаем конструктор */
    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
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
    public String getFullName() {
        return fullName;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /** сгенерированы геттеры и сеттеры */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /** сгенерированы геттеры и сеттеры */
    public List<Book> getBooks() {
        return books;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
