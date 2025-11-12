package com.pluralsight;

import UserInterface.*;
import models.*;
import java.time.LocalTime;
import java.util.*;

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

        System.out.println("Welcome to the Seven Serpent Taco shop");
        prompts prompts = new prompts();  //  create an instance
        prompts.showHomeScreen();         //  call the entry method

        System.out.println("What would you like to Order?");
        prompts.showOrderMenu();
        prompts.promptForTaco();
        prompts.checkout();
    }}