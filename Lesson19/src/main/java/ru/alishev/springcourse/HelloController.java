/**
 * Создал Андрей Антонов 14.06.2022 13:35
 **/
package ru.alishev.springcourse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello  () {
        return "/WEB-INF/views/hello_world.mhtml";
    }

    @GetMapping("/goodbye")
    public String goodByePage () {
        return "first/goodbye";
    }
}
