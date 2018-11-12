package com.project.taskmanager;

import java.util.Scanner;

/**
 * Does the delegation of all tasks to the different classes which handles and returns processed
 * output back to the TaskManager.
 *
 * @author Wang Zhenquan
 * @version Level 12, v1.2
 * @since 12/11/2018
 */
class TaskManager {
    private UI ui = new UI();
    private Database database;
    private TaskList tasks;
    private Parser parser = new Parser();
    private Scanner in = new Scanner(System.in);
    private boolean repeat = true;
    private String filePath = "C:\\Users\\USER\\Desktop\\Tasks.txt";

    private TaskManager() {
        database = new Database(filePath);
        try {
            tasks = new TaskList(database.load(ui));
            if (tasks.size() == 0) {
                throw new TaskManagerException
                        (ui.colorRed("A Tasks.txt will be created in your Desktop " +
                                "when you create a new task."));
            } else {
                System.out.println(ui.colorRed("\nTasks.txt found. Task list loaded."));
            }
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
        }
    }

    private void run() {
        ui.welcomeMessage();
        ui.commandsMessage();
        while (repeat) {
            ui.enterCommand();
            repeat = parser.parseInput(filePath, in.nextLine(), ui, tasks, database);
        }
    }

    public static void main(String[] args) {
        new TaskManager().run();
    }
}