/**
 * Создал Андрей Антонов 04.08.2022 11:32
 **/
package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
