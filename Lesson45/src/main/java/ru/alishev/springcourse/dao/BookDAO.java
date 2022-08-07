/**
 * Создал Андрей Антонов 11.07.2022 11:44
 **/
package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    /** создаем объект коннекта к базе */
    private final JdbcTemplate jdbcTemplate;

    /** создаем конструктор */
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book( title, author, year) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }

    public void update (int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book SET title = ?, author = ?, year = ? WHERE id = ?",
                updateBook.getTitle(),
                updateBook.getAuthor(),
                updateBook.getYear(),
                id);
    }
    public void delete (int id) {jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);}

    /** вызывается из BooksController, чтобы получить владельца книги если он есть */
    public Optional<Person> getBookOwner (int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    /** удаляет связь книги и человека */
    public void release (int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?", id);
    }

    /** связывает книгу и человека */
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?",
                selectedPerson.getId(), id);
    }
}