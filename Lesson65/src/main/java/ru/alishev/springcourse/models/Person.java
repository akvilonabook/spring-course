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

    @Column (name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // dd/MM/yyyy
    private Date dateOfBirth;

    @Column(name ="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    /** список товаров человека */
    @OneToMany(mappedBy = "owner")
    private List<Item> items;

//    @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private Mood mood;

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

    /** сгенерированы геттеры и сеттеры */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /** сгенерированы геттеры и сеттеры */
    public Date getCreated_at() {
        return created_at;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /** сгенерированы геттеры и сеттеры */
    public Mood getMood() {
        return mood;
    }

    /** сгенерированы геттеры и сеттеры */
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", created_at=" + created_at +
                ", items=" + items +
                ", mood=" + mood +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
