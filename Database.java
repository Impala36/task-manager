package com.project.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Helps to load Tasks from the hard disk and save tasks to the hard disk.
 */
class Database {
    static String filepath;

    Database(String filepath) {
        Database.filepath = filepath;
    }

    List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            List<String> lines = getLines(filepath);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(filepath, line)); //convert line to task then add to list
            }
        } catch (FileNotFoundException e) {
            UI.printError(UI.colorRed("File not found: " + e.getMessage()));
            try {
                createTask(filepath, "");
            } catch (IOException f) {
                UI.printError(UI.colorRed("IOException encountered: " + f.getMessage()));
            }
        } catch (IOException e) {
            UI.printError(UI.colorRed("IOException encountered: " + e.getMessage()));
        }
        return loadedTasks;
    }

    private static List<String> getLines(String filepath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(filepath));
        List<String> list = new ArrayList<String>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    static Task createTask(String filepath, String line) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        switch (line.split(" ")[0]) {
            case "todo":
                line = line.substring("todo".length()).trim();
                fw.write("[" + TaskList.size() + "] Todo (not done): " + line);
                fw.close();
                return new Todo(line);
            case "deadline":
                line = line.substring("deadline".length()).trim();
                String split[] = line.split("/by");
                fw.write("[" + TaskList.size() + "] Deadline (not done): "
                        + split[0].trim() + " | Do by: " + split[1].trim());
                fw.close();
                return new Deadline(line);
            default:
                return new Todo("");
        }
    }
}