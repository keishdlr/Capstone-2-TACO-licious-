package com.pluralsight;

import UserInterface.Prompts;
import models.Order;
import models.TacoSize;

import java.time.LocalTime;
import java.util.Scanner;

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

        System.out.println("Welcome to the Seven Serpent shop");
        public Prompts() {
            this.currentOrder = new Order();
        }
        public class Prompts {
            private Scanner myScanner = new Scanner(System.in);
            private Order currentOrder;
        }

        Prompts.showHomeScreen();

        System.out.println("What would you like to Order?");
        Prompts.showOrderMenu();
        Prompts.promptForMeats();
        Prompts.promptForCheeses();
        Prompts.removeSauces();
        Prompts.promptForSides();
        Prompts.promptForDrinkSize();
        Prompts.promptForDrink();
        Prompts.promptForChips();
        Prompts.checkout();
    }}