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

//        Music music = context.getBean("musicBean", Music.class);
//        MusicPlayer firstMusicPlayer = new MusicPlayer(music);

        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean ("musicPlayer", MusicPlayer.class);

        boolean comparison = firstMusicPlayer == secondMusicPlayer;

        System.out.println(comparison);
        System.out.println(firstMusicPlayer);
        System.out.println(secondMusicPlayer);
        
        firstMusicPlayer.setVolume(10);

        firstMusicPlayer.playMusic();

        System.out.println(firstMusicPlayer.getName());
        System.out.println(firstMusicPlayer.getVolume() );
        System.out.println(secondMusicPlayer.getVolume() );

        context.close();
    }
}
