/**
 * Автор Андрей Антоно 06.06.2022 15:29
 */

package ru.alishev.springcourse;

public class ClassicalMusic implements Music {
    private ClassicalMusic() {

    }

    public static ClassicalMusic getClassicalMusic() {
        return new ClassicalMusic();
    }

    public void doMyInit (){
        System.out.println("Doing my initialization");
    }

    public void doMyDestroy () {
        System.out.println ("Doing my destructiong");
    }
    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
