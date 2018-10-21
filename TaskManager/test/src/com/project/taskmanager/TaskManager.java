package com.project.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private TaskList tasks;

    /**
     * @param filePath path where Tasks.txt will be created and accessed from.
     */
    private TaskManager(String filePath) {
        Database database = new Database(filePath);
        try {
            tasks = new TaskList(database.load());
            if (TaskList.size() == 0) throw new TaskManagerException
                    (UI.colorRed("\nTasks.txt has been created in your Desktop."));
            else System.out.println(UI.colorRed("\nTasks.txt found. Task list loaded."));
        } catch (TaskManagerException e) {
            UI.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    private void run() {
        UI.welcomeMessage();
        UI.enterCommand();
    }

    static void main(String[] args) {
        new TaskManager("C:\\Users\\USER\\Desktop\\Tasks.txt").run();
    }
}