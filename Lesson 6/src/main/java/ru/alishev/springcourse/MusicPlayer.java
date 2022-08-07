/**
 * Автор Андрей Антоно 06.06.2022 15:49
 */

package ru.alishev.springcourse;

public class MusicPlayer {
    private Music music;

    /** Инверсия управления */
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic () {
        System.out.println("Playing: " + music.getSong());
    }
}
