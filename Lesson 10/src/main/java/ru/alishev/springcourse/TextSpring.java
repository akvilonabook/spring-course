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

        Music music = context.getBean("rockMusic", Music.class);

        MusicPlayer musicPlayer = new MusicPlayer(music);

//        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();

        Music music2 = context.getBean("classicalMusic", Music.class);

        MusicPlayer classicalMusicPlayer = new MusicPlayer(music2);

        classicalMusicPlayer.playMusic();

        context.close();
    }
}
