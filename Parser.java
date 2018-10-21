package com.project.taskmanager;

import java.util.Scanner;

/**
 * Contains methods that deals with parsing the user command to extract meaningful details from it.
 */
class Parser {
    private static Scanner in = new Scanner(System.in);

    static String getCommandWord(String input) {
        return input.trim().split(" ")[0];
    }

    static void getInput() {
        boolean isExit = false;
        String line = in.nextLine();
        String command = getCommandWord(line);
        switch (command) {
            case "todo":
                Todo.createTodo(line);
                break;
            case "deadline":
                Deadline.createDeadline(line);
                break;
            case "done":
                Task.changeDone(line);
                break;
            case "print":
                UI.printTasks();
                break;
            case "exit":
            case "": // also exit when user input is empty
                isExit = true;
                UI.exit();
                break;
            default:
                UI.invalidCommand();
                break;
        }
        if (!isExit) UI.enterCommand();
    }


}