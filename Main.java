package com.tripledigitaldaggers;
import java.util.Scanner;

public class Main {
static Scanner sc = new Scanner(System.in);
static String user;
static UserInterface userUI = new UserInterface();

    public static void main(String[] args) {
        userUI.displayHeader("Welcome to GapMatch! Please input your username: ");
        userUI.display();
        //If statement here, boolean for starting new user progress
        user = userUI.waitForInput();
        if (User.findUsername(user)){
            System.out.println("Welcome back!");
        } else {
            newUser(user);
        }
    }

    public static Student newUser(String user) {
        userUI.displayHeader("You don't seem to have an account yet.");
        System.out.println("To get started, let us get to know you.");
        //Obtain user's name
        System.out.println("What is your name?");
        String name = sc.nextLine();
        //Obtain user's program
        System.out.println("And what is your current program?");
        String program = sc.nextLine();
        //Add user's username to the username database & to the Student object
        User.addUsername(user);
        Student student = new Student(name, program, user);
        System.out.println("You're all set up!");
        return student;
    }
}