/**
 * Создал Андрей Антонов 28.07.2022 18:02
 **/
package ru.alishev.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    /** поле таблицы "Book" */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** поле таблицы "Book" */
    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов длинной")
    @Column(name = "title")
    private String title;

    /** поле таблицы "Book" */
    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов")
    @Column(name = "author")
    private String author;

    /** поле таблицы "Book" */
    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    @Column(name = "year")
    private int year;

    /** поле таблицы "Book" */
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    /** поле таблицы "Book" */
    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    /** Техническое поле, его нет в таблице "Book"
     Hibernate не будет замечать этого поля, из-за аннотации @Transient что нам и нужно.
     По-умолчанию поле expired = false;

     Это поле не сохраняется, это поле помечается просрочена книга или нет.
     Используется в сервисе, где выщитывается время от поля takenAt (когда книга взята)
     сколько дней прошло и если там больше 10 дней прошло, то поле expired = true;
     */
    @Transient
    private boolean expired; /** Hibernate не будет замечать это поле, что нам и нужно. По-умолчанию expired = false */

    /** пустой конструктор */
    public Book() {
    }

    /** основной конструктор */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
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
    public String getTitle() {
        return title;
    }

    /** геттеры и сеттеры */
    public void setTitle(String title) {
        this.title = title;
    }

    /** геттеры и сеттеры */
    public String getAuthor() {
        return author;
    }

    /** геттеры и сеттеры */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** геттеры и сеттеры */
    public int getYear() {
        return year;
    }

    /** геттеры и сеттеры */
    public void setYear(int year) {
        this.year = year;
    }

    /** геттеры и сеттеры */
    public Person getOwner() {
        return owner;
    }

    /** геттеры и сеттеры */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /** геттеры и сеттеры */
    public Date getTakenAt() {
        return takenAt;
    }

    /** геттеры и сеттеры */
    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    /** геттеры и сеттеры */
    public boolean isExpired() {
        return expired;
    }

    /** геттеры и сеттеры */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
