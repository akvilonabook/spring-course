/**
 * Автор Андрей Антоно 06.06.2022 13:59
 */

package ru.alishev.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class  TextSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext (
                SpringConfig.class
        );

//        Music music = context.getBean("rockMusic", Music.class); /** ноаый вариант создания бина **/
//
//        MusicPlayer musicPlayer = new MusicPlayer(music);
//
////        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class); /**  старая версия создания бина **/
//        musicPlayer.playMusic();
//
//        Music music2 = context.getBean("classicalMusic", Music.class);
//
//        MusicPlayer classicalMusicPlayer = new MusicPlayer(music2);
//
//        classicalMusicPlayer.playMusic();

//        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
//        musicPlayer.playMusic();
//
//        Computer computer = context.getBean( "computer", Computer.class );
//        System.out.println(computer);

        /** создаем бин класса musicPlayer **/
         MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class );

        /** получаем переменные из файла musicPlayer.properties -> на экран выводится 'Some name'  '60' */
        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        /** создаем два бина класса classicalMusic **/
        ClassicalMusic classicalMusic1 = context.getBean("classicalMusic", ClassicalMusic.class);

         context.close();
    }
}
