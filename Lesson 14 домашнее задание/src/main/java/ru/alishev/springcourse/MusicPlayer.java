/**
 * Автор Андрей Антоно 06.06.2022 15:49
 */

package ru.alishev.springcourse;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Random;

//@Component
public class MusicPlayer {

    @Value("${musicPlayer.name}")
    private String name;

    @Value("${musicPlayer.volume}")
    private int volume;

    private List<Music> musicList;

    /** Setter */
    public MusicPlayer(List<Music> musicList) {
        this.musicList = musicList;
    }

    /** Getter */
    public String getName() {
        return name;
    }

    /** Getter */
    public int getVolume() {
        return volume;
    }

    public String playMusic (){
        Random random = new Random();

        return "Playing: " + musicList.get (random.nextInt (musicList.size())).getSong()
                + " with volue  " + this.volume;

    }
}