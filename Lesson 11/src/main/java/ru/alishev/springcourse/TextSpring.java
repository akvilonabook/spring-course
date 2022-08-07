/**
 * Автор Андрей Антоно 06.06.2022 13:59
 */

package ru.alishev.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class  TextSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
              "applicationContext.xml"
        );

        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.playMusic(MusicGenre.CLASSICAL);
        musicPlayer.playMusic(MusicGenre.ROCK);

        context.close();

    }
}
