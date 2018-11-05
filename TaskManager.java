package com.project.taskmanager;

import java.util.Scanner;

/**
 * Does the delegation of all tasks to the different classes which handles and returns processed
 * output back to the TaskManager.
 *
 * @author Wang Zhenquan
 * @version Level 10, v1.0
 * @since 21/10/2018
 */
class TaskManager {
    private UI ui = new UI();
    private Database database;
    private TaskList tasks;
    private Parser parser = new Parser();
    private Scanner in = new Scanner(System.in);
    private boolean repeat = true;
    /**
     * @param filePath path where Tasks.txt will be created and accessed from.
     */
    private TaskManager(String filePath) {
        database = new Database(filePath);
        try {
            tasks = new TaskList(database.load(ui));
            if (tasks.size() == 0) {
                throw new TaskManagerException
                        (ui.colorRed("\nTasks.txt has been created in your Desktop."));
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
            repeat = parser.parseInput(in.nextLine(), ui, tasks, database);
        }
    }
    public static void main(String[] args) {
        new TaskManager("C:\\Users\\USER\\Desktop\\Tasks.txt").run();
    }
}