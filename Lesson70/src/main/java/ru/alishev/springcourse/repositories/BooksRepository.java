/**
 * Создал Андрей Антонов 28.07.2022 19:20
 **/
package ru.alishev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.models.Book;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    /** метод прописан в документации Hibernate для поиска книг по любой части названия */
    List<Book> findByTitleStartingWith(String title);

}
