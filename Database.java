package com.project.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps to load Tasks from the hard disk and save tasks to the hard disk.
 */
class Database {
    String filepath;
    private ArrayList<Task> loadedTasks = new ArrayList<>();

    Database(String filepath) {
        this.filepath = filepath;
    }

    ArrayList<Task> load(UI ui) {
        try {
            ArrayList<String> lines = getLines(filepath);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(filepath, line, null)); //convert line to task then add to list
            }
        } catch (FileNotFoundException e) {
            ui.printError(ui.colorRed("File not found: " + e.getMessage()));
            try {
                createTask(filepath, "", null);
            } catch (IOException f) {
                ui.printError(ui.colorRed("IOException encountered: " + f.getMessage()));
            }
        } catch (IOException e) {
            ui.printError(ui.colorRed("IOException encountered: " + e.getMessage()));
        }
        return loadedTasks;
    }

    private ArrayList<String> getLines(String filepath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filepath));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    Task createTask(String filepath, String line, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        switch (line.split(" ")[0]) {
            case "todo":
                line = line.substring("todo".length()).trim();
                fw.write("[" + tasks.size() + "] Todo (not done): " + line);
                fw.close();
                return new Todo(line);
            case "deadline":
                line = line.substring("deadline".length()).trim();
                String split[] = line.split("/by");
                fw.write("[" + tasks.size() + "] Deadline (not done): "
                        + split[0].trim() + " | Do by: " + split[1].trim());
                fw.close();
                return new Deadline(line);
            default:
                return new Todo("");
        }
    }
}