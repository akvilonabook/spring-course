/**
 * Автор Андрей Антоно 06.06.2022 13:59
 */

package ru.alishev.springcourse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.alishev.springcourse.config.SpringConfig;

public class  TextSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext (
                SpringConfig.class
        );


        /** создаем бин класса musicPlayer **/
         MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class );

        System.out.println(musicPlayer.playMusic());

         context.close();
    }
}
