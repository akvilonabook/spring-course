/**
 * Создал Андрей Антонов 27.06.2022 14:21
 **/
package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alishev.springcourse.dao.PersonDAO;

/** создаем контроллер для массовой вставки (более 1000 записей в базу данных)*/
@Controller
@RequestMapping(" ")
public class BatchController {
    /** здесь в этом контроллере  будет три метода. Первый метод будет возвращать страницу с двумя кнопками,
     * когда мы будем нажимать первую кнопку, мы будем делать запрос с помощью пакетной вставки, когда мы будем
     * нажимать на вторую кнопку мы будем делать запрос с помощью обычных 1000 вставок. Создадим метод
     * с @GetMapping */

    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    /** первый метод контроллера - страница с двумя кнопками,
     * куда будет переходить человек при запросе "/test-batch-update */
    @GetMapping ()
    public String index () {
        return "batch/index"; /** возвращаемая страничка */
    }

    /** второй метод контроллера сделать 1000 запросов к базе данных без Batch Update(ов) */
    @GetMapping("/without")
     public String withoutBatch () {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    /** третий метод контроллера сделать 1000 запросов
     * к базе данных без Batch Update(ов)при помощи пакетной вставки  */
    @GetMapping("/with")
    public String withBatch () {
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }

}
