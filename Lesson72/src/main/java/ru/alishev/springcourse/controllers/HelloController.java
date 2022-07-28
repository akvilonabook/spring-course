/**
 * Создал Андрей Антонов 28.07.2022 16:14
 **/
package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        System.out.println(this.hello);

        return "hello";
    }
}
