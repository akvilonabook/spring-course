/**
 * Создал Андрей Антонов 08.07.2022 16:20
 **/
package ru.alishev.springcourse.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    /** объявляем два класса объектов */
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    /** заполнение зависимостей */
    @Autowired
    /** конструктор */
    public BooksController(BookDAO BookDAO, PersonDAO personDAO) {
        this.bookDAO = BookDAO;
        this.personDAO = personDAO;
    }

    /** получаем людей и положим их в модель index.html, чтобы передать их в представление  */
    @GetMapping()
    public String index(Model model) {
        /** Получим всех людей из DAO и передадим на отображение этих людей в представление
         * под ключом people будет лежать список из людей - динамический массив ArrayList из объектов класса Person  */
        model.addAttribute("books", bookDAO.index());

        /** возвращаем ту страницу тот шаблон которая будет отображать список из людей */
        return "books/index";
    }

    /** мы извлечем этот id из url адреса и получим доступ внутри этого метода  */
    /** используем аннотацию @PathVariable и используем целое число id это будет лежать
     * в адресе к запросу к этому методу /people/id второй параметр Model  */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));

        /** если книга принадлежит какому-то человеку, мы должны показывать этого человека */
        /** если книга свободна, мы должны показывать список из людей, чтобы из этого списка
         *  с помощью выпадающего списка мы могли выбрать того человека, кому мы хотим назначить
         *  данную книгу */

        /** получить владельца книги */
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        /** если у книги есть владелец bookOwner.isPresent() == true */
        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            /** если у книги нет владельца, значит книга свободна,
             * значит мы на этой странице должны показать выпадающий список
             * personDAO.index() кладем список из всех людей */
            model.addAttribute("people", personDAO.index());

        /** возвращаем из этго метода название того шаблона, где будет покзываться этот человек */
        return "books/show";
    }

    /** возвращает html форму для создания нового человека */
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book Book) {
        /** когда мы создадим новый объект класса Person() c пустым конструктором */
        return "books/new";
    }

    /** принимает на вход POST запрос, берет данные из этого пост запроса и будет
     * создавать нового человека добавлять его в базу данных  */
    /** Здесь мы никакой адрес не передаем по адресу /people мы должны поспасть
     в этот метод. Мы должны получить данные из формы, создать нового человека положить
     этого человека данные которые пришли из формы и этого человека мы должны и добавить
     нового человека в базу данных. Для того чтобы создать новый объект класса person
     и в него положить данные из формы мы используем аннотацию @ModelAttribute() указываем
     ключ "person" и далее в объекте класса Person будут лежать человек с данными из формы
     из той html форму, которую мы организуем в файле new.html */

    @PostMapping()
    public String create (@ModelAttribute("book") @Valid Book Book,
                          BindingResult bindingResult) {

        /** @Valid можно использовать если прописаны @аннотациями на полях в классе Person.
         * Проверяем на наличие ошибок в полях -> пере открываем эту форму */
        if (bindingResult.hasErrors())
            return "books/new";

        /** Объект класса person мы добавим в базу данных с помощью DAO
         * в этом DAO мы реализуем метод save, который принимает на вход объект класса person
         * и добавляет его в наш список при помощи метода save(). Этот метод мы реализуем на
         * этом уроке в контроллере PersonDAO. */
        bookDAO.save(Book);

        /**  Редирект. redirect - это механизм, который говорит браузеру перейти
         * на какую-то другую страницу. И браузер перейдет на эту страницу и сделает очередной
         * запрос по тому адресу, который мы ему указали. */
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable ("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        /** @Valid Можно использовать если прописаны @аннотациями на полях в классе Person.
         * Проверяем на наличие ошибок в полях -> переходим к списку людей */
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    /** этот метод освобождает книгу, при нажатии на кнопку */
    @PatchMapping("/{id}/release")
    public String release (@PathVariable ("id") int id) {

        /** этот метод убирает владельца книги */
        bookDAO.release(id);

        return "redirect:/books/" + id;
    }

    /** этот метод назначает книгу человеку, при нажатии на кнопку,
     * в том случае если книга свободна, выпадающий список находится в форме и эта
     * форма отправляется при помощи PATCH запроса на этот метод контроллера  */
    @PatchMapping("/{id}/assign")
    public String assign (@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {

        /** этот метод добавляет владельца книги в выпадающем списке */
        bookDAO.assign(id, selectedPerson);

        return "redirect:/books/" + id;
    }
}

