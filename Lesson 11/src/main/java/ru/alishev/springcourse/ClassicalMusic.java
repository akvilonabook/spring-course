/**
 * Автор Андрей Антоно 06.06.2022 15:29
 */

package ru.alishev.springcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClassicalMusic implements Music {
    private List<String> songs = new ArrayList<>();

    //  Блок инициализации объекта (англ. Instance initialization block)
    //  Выполняется каждый раз, когда создается объект класса
    {
            songs.add("1 Hungarian Rhapsody");
            songs.add("2 Symphony on. 5 in C Minor, op. 67");
            songs.add("3 Night on Bald Mountain");
    }

    @Override
    public List<String> getSongs() {
         return songs;
    }
}
