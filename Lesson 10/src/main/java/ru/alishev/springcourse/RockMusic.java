/**
 * Автор Андрей Антоно 06.06.2022 15:33
 */

package ru.alishev.springcourse;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
