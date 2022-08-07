/**
 * Автор Андрей Антоно 09.06.2022 15:44
 */

package ru.alishev.springcourse.genres;

import ru.alishev.springcourse.Music;

/** Класс джазовой музики */
public class JazzMusic implements Music {

    @Override
    public String getSong() {
        return "Take five";
    }
}
