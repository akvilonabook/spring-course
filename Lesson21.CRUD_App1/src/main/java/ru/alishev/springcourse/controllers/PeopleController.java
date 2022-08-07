package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;

/**
 * @author Neil Alishev
 */
@Controller /** Spring создаст BIN этого класса и этот бин spring внедрит в контроллер  */
@RequestMapping("/people")
public class PeopleController {

     /** Внедряем DAO в контроллер spring это автоматически сделает из за анотации @Controller  */
    private final PersonDAO personDAO;

    /** по аннотации @Autowired spring(а) внедрит зависимость в наш контроллер если у нас
     * в контроллере есть DAO то мы можем получать людей нашей условной базы данных  */
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    /** получаем людей и положим их в модель index.html, чтобы передать их в представление  */
    @GetMapping()
    public String index(Model model) {
        /** Получим всех людей из DAO и передадим на отображение этих людей в представление
         * под ключем people будет лежать список из людей - динамический массив ArrayList из объектов класса Person  */

        model.addAttribute("people", personDAO.index());

        /** возвращаем ту страницу тот шаблон которая будет отображать список из людей */
        return "people/index";
    }


    /** мы извлечем этот id из url адреса и получим доступ внутри этого метода  */
    /** используем анноайию @PathVariable и используем целое число id это будет лежать
     * в адресе к запросу к этому м етоду  /people/id второй параметр Model  */

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        /** получим одного человека по его id из DAO и передадим на отображение в представление */

        model.addAttribute("person", personDAO.show(id));

        /** возвращаем из этго метода название того шаблона, где будет покзываться этот человек */
        return "people/show";
    }
}
