/**
 * Автор Андрей Антоно 06.06.2022 15:29
 */

package ru.alishev.springcourse;

import org.springframework.stereotype.Component;

@Component
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
