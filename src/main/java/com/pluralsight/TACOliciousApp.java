package com.pluralsight;

import java.time.LocalTime;

public class TACOliciousApp {
    public static void main(String[] args) {

        int hour = LocalTime.now().getHour();

        String greeting;

        if (hour >= 5 && hour < 12) {
            greeting = "Good morning ☕🌄";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good afternoon ☀️";
        } else if (hour >= 17 && hour < 21) {
            greeting = "Good evening 🌇";
        } else {
            greeting = "Good night 🌙";
        }
        System.out.println(greeting);

    }
    Prompts prompts = new Prompts();
    prompts.showHomeScreen();
}
