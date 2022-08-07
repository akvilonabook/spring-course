/**
 * Автор Андрей Антоно 06.06.2022 15:29
 */

package ru.alishev.springcourse.genres;

import ru.alishev.springcourse.Music;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
//@Scope("prototype")
/** Класс классической музики */
public class ClassicalMusic implements Music {

    /** росле вызова context.getBean() в классе TextSpring() сначала должен вызваться этот метод */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Do my initialization");
    }
    /** росле вызова context.getBean() в классе TextSpring() по окончанию всех действий должен вызваться этот метод */
    @PreDestroy
    public void doMyDestroy(){
        System.out.println("Doing my destruction");
    }

    /** Основной метод каласса */
    @Override
    public String getSong() {
         return "Hungarian Rhapsody";
    }
}
