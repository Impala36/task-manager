package com.project.taskmanager;

/**
 * Responsible for interacting with the user. Ideally, only this class should interact with the user.
 */
class UI {
    static void welcomeMessage() {
        System.out.println("\033[33m------------Welcome to TaskManager Level 7!--------------");
        System.out.println("To create a todo        | todo \033[0m<something>\033[33m");
        System.out.println("To create a deadline    | deadline \033[0m<something>\033[33m /by \033[0m<when>\033[33m");
        System.out.println("To complete a deadline  | done \033[0m<task index number>\033[33m");
        System.out.println("To print all tasks      | print");
        System.out.println("To exit from program    | exit\033[0m");
    }

    static void enterCommand() {
        System.out.print("Your command? ");
        Parser.getInput();
    }

    static void exit() {
        System.out.println("\n\033[33mThanks for using TaskManager. Goodbye!");
    }

    static void showTotal() {
        System.out.println("Tasks in the list: " + TaskList.size());
    }

    static void invalidCommand() {
        System.out.println(colorRed("Please enter a valid command."));
    }

    static void printError(String s) {
        System.out.println(s);
    }

    static void printTasks() {
        if (TaskList.size() != 0) {
            for (int i = 0; i < TaskList.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + TaskList.get(i).getTask());
            }
        } else System.out.println(colorRed("There are no tasks in the list to print."));
    }

    static void markedAsDone(int index) {
        System.out.println("Task [" + index + "] " + Task.task + " has been set as completed.");
    }

    static String colorRed(String input) {
        return "\033[31m" + input + "\033[0m";
    }
}