/**
 * Автор Андрей Антоно 06.06.2022 15:33
 */

package ru.alishev.springcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class  RockMusic implements Music {
    private List<String> songs = new ArrayList<>();

    // Блок инициализации объекта ( англ. Instance initialization block)
    // Выполняется каждый раз, когда создается объект класса
    {
        songs.add("1 Wind cries Mary");
        songs.add("2 Paint it black");
        songs.add("3 Can't seem to make you mine");
    }

    @Override
    public  List<String> getSongs() {
        return songs;
    }
}
