/**
 * Автор Андрей Антоно 06.06.2022 15:49
 */

package ru.alishev.springcourse;

public class MusicPlayer {
    private Music music;
    private String name;
    private int volume;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    /** Инверсия управления */
    public MusicPlayer(Music music) {
        this.music = music;
    }

    /** Создаем пустой конструкто */
    public MusicPlayer () {}

    public void setMusic(Music music){
        this.music = music;
    }

    public void playMusic () {
        System.out.println("Playing: " + music.getSong());
    }
}
