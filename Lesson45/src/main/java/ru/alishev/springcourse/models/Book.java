/**
 * Создал Андрей Антонов 08.07.2022 14:56
 **/
package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id; /** идентификатор книги */

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до  100 символов длинной")
    private String title;     /** название книги */

    @NotEmpty (message = "Автор не должен быть пустым")
    @Size (min = 2, max  = 100, message = "Имя автора должно быть от 2 до 100 символов")
    private String author; /** поле Автор */

    @Min(value = 1500, message = "Год должен быть больше, чем 1000")
    private int year; /** поле год создания книги */

    /** В спринге приложениях должен быть отдельно пустой конструктор */
    public Book() {
    }

    /** конструктор по всем полям кроме id */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    /** создаем геттеры и сеттеры */
    public int getId() {
        return id;
    }

    /** создаем геттеры и сеттеры */
    public void setId(int id) {
        this.id = id;
    }

    /** создаем геттеры и сеттеры */
    public String getTitle() {
        return title;
    }

    /** создаем геттеры и сеттеры */
    public void setTitle(String title) {
        this.title = title;
    }

    /** создаем геттеры и сеттеры */
    public String getAuthor() {
        return author;
    }

    /** создаем геттеры и сеттеры */
    public void setAuthor(String author) {
        this.author = author;
    }

    /** создаем геттеры и сеттеры */
    public int getYear() {
        return year;
    }

    /** создаем геттеры и сеттеры */
    public void setYear(int year) {
        this.year = year;
    }
}
