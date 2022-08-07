package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.util.PersonValidator;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller /** Spring создаст BIN этого класса и этот бин spring внедрит в контроллер  */
@RequestMapping("/people")
public class  PeopleController {

     /** Внедряем DAO в контроллер spring э то автоматически сделает из-за аннотации @Controller  */
    private final PersonDAO personDAO;
    private final PersonValidator personValidator; /** объявляем класс валидатора */

    /** по аннотации @Autowired spring(а) внедрит зависимость в наш контроллер если у нас
     * в контроллере есть DAO то мы можем получать людей нашей условной базы данных  */
    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }


    /** получаем людей и положим их в модель index.html, чтобы передать их в представление  */
    @GetMapping()
    public String index(Model model) {
        /** Получим всех людей из DAO и передадим на отображение этих людей в представление
         * под ключом people будет лежать список из людей - динамический массив ArrayList из объектов класса Person  */
        model.addAttribute("people", personDAO.index());

        /** возвращаем ту страницу тот шаблон которая будет отображать список из людей */
        return "people/index";
    }

    /** мы извлечем этот id из url адреса и получим доступ внутри этого метода  */
    /** используем аннотацию @PathVariable и используем целое число id это будет лежать
     * в адресе к запросу к этому методу /people/id второй параметр Model  */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        /** получим одного человека по его id из DAO и передадим на отображение в представление */
        model.addAttribute("person", personDAO.show(id));

        /** возвращаем из этго метода название того шаблона, где будет покзываться этот человек */
        return "people/show";
    }

    /** возвращает html форму для создания нового человека */
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        /** когда мы создадим новый объект класса Person() c пустым конструктором */
         return "people/new";
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
    public String create (@ModelAttribute("person") @Valid Person person,
                          BindingResult bindingResult) {

        /** вызываем проверку уникальность e-mail на базе данных,
         * а в bindingResult будет возвращено значение ошибки */
        personValidator.validate(person, bindingResult);

        /** @Valid можно использовать если прописаны @аннотациями на полях в классе Person.
         * Проверяем на наличие ошибок в полях -> пере открываем эту форму */
        if (bindingResult.hasErrors())
            return "people/new";

        /** Объект класса person мы добавим в базу данных с помощью DAO
         * в этом DAO мы реализуем метод save, который принимает на вход объект класса person
         * и добавляет его в наш список при помощи метода save(). Этот метод мы реализуем на
         * этом уроке в контроллере PersonDAO. */
        personDAO.save(person);

        /**  Редирект. redirect - это механизм, который говорит браузеру перейти
         * на какую-то другую страницу. И браузер перейдет на эту страницу и сделает очередной
         * запрос по тому адресу, который мы ему указали. */
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable ("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping ("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        /** вызываем проверку уникальность e-mail на базе данных,
         * а в bindingResult будет возврaщенно значение ошибки */

        personValidator.validate(person, bindingResult);

        /** @Valid Можно использовать если прописаны @аннотациями на полях в классе Person.
         * Проверяем на наличие ошибок в полях -> переходим к списку людей */
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";

    }
}
