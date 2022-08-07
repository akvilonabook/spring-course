/**
 * Автор Андрей Антоно 08.06.2022 16:29
 */

package ru.alishev.springcourse;

import org.springframework.stereotype.Component;

@Component
public class  Computer {
    private int id;
    private MusicPlayer musicPlayer;

    public Computer(MusicPlayer musicPlayer) {
        this.id             = 1;
        this.musicPlayer    = musicPlayer;
    }

    @Override
    public String toString() {
        return "Computer " + id + " " + musicPlayer.playMusic();
    }
}
