/**
 * Создал Андрей Антонов 07.07.2022 10:51
 **/
package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

/** Контроллер для установки прав администратора */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /** внедряем зависимость PersonDAO потому что нам нужно будет получать множество людей */
    private final PersonDAO personDAO;

    /** переопределяем конструктор */
    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /** для получения списка из всех людей, создаем страницу с выпадающим списком */
    @GetMapping()        /** пустой человек поместился в модель с помощью @ModelAttribute */
    public String adminPage(Model model, @ModelAttribute("person") Person person) {

        /** помещаем в модель список из людей, этот список будет в выпадающем списке */
        /** чтобы выпадающий список создать нам нужно создать список людей
         *  personDAO.index() -> возвращаем список из людей */
        model.addAttribute("people", personDAO.index());

        /** возвращаем название того представления, где будет лежать наш выпадающий список */
        return "adminPage";

        /** далее создаем это представление adminPage.html */
    }

    /** здесь принимается выбор пользователя в консоли
     * @ModelAttribute -> создает пустого человека объект класса Person,
     * но теперь аннотация увидит что приходит значение id и пустому человеку
     * она назначит значение id и мы сможем посмотреть его в консоли */
    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute Person person) {

        /** получаем информацию о выбранном значение */
        System.out.println(person.getId());

        /** переходим на страницу */
        return "redirect:/people";
    }
}
