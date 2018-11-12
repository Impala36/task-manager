package com.project.taskmanager;

/**
 * Responsible for interacting with the user. Ideally, only this class should interact with the user.
 */
class UI {
    void welcomeMessage() {
        System.out.println("\033[33m------------Welcome to TaskManager Level 12!------------\033[0m");
    }

    void commandsMessage() {
        System.out.println("\033[33mTo create a todo        | todo \033[0m<something>\033[33m");
        System.out.println("To create a deadline    | deadline \033[0m<something>\033[33m /by \033[0m<when>\033[33m");
        System.out.println("To complete a task      | done \033[0m<task index number>\033[33m");
        System.out.println("To reshow all commands  | commands");
        System.out.println("To print all tasks      | print");
        System.out.println("To exit from program    | exit\033[0m");
    }

    void enterCommand() {
        System.out.print("Your command? ");
    }

    void exit() {
        System.out.println("\n\033[33mThank you for using TaskManager. Goodbye!\033[0m");
    }

    void showTotal(TaskList tasks) {
        System.out.println("Tasks in the list: " + tasks.size());
    }

    void invalidCommand() {
        System.out.println(colorRed("Please enter a valid command."));
    }

    void printError(String s) {
        System.out.println(s);
    }

    void printTasks(TaskList tasks) {
        if (tasks.size() != 0) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + tasks.get(i).getTask());
            }
        } else System.out.println(colorRed("There are no tasks in the list to print."));
    }

    void markedAsDone(int index, TaskList tasks) {
        System.out.println("\033[33mTask [" + index + "] "
                + tasks.get(index - 1).getDescription() + " has been set as completed.\033[0m");
    }

    String colorRed(String input) {
        return "\033[31m" + input + "\033[0m";
    }
}