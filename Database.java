package com.project.taskmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
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
                loadedTasks.add(createTask(filepath, line, ui, null)); //convert line to task then add to list
            }
        } catch (FileNotFoundException e) {
            ui.printError(ui.colorRed("File not found: " + e.getMessage()));
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

    Task createTask(String filepath, String line, UI ui, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filepath, true);
        String str = "";

        try {
            assert line != null;
            if (line.contains("todo")) str = "todo";
            else if (line.contains("deadline")) str = "deadline";
            else if (line.contains("Todo")) str = "Todo";
            else if (line.contains("Deadline")) str = "Deadline";
            else throw new TaskManagerException
                        (ui.colorRed("Task [" + line.charAt(1) + "] has been edited out of format."));
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
        }

        int index;
        boolean isDone = false;

        switch (str) {
            case "todo": //creating task from user
                line = line.substring("todo".length()).trim();
                fw.write("[" + tasks.size() + "] Todo (not done): " + line + System.lineSeparator());
                fw.close();
                return new Todo(line);
            case "deadline": //creating task from user
                line = line.substring("deadline".length()).trim();
                String split[] = line.split("/by");
                fw.write("[" + tasks.size() + "] Deadline (not done): "
                        + split[0].trim() + " | Do by: " + split[1].trim());
                fw.close();
                return new Deadline(line);
            case "Todo": //creating task from file
                index = Integer.parseInt(line.split("Todo")[0]
                        .replace("[", "").replace("] ", ""));
                line = line.split("Todo")[1];
                if (line.contains("(done)")) {
                    isDone = true;
                    line = line.substring("(done):".length()).trim();
                } else {
                    line = line.substring("(not done):".length()).trim();
                }
                fw.close();
                return new Todo("todo" + line, isDone);
            case "Deadline": //creating task from file
                index = Integer.parseInt(line.split("Deadline")[0]
                        .replace("[", "").replace("] ", ""));
                line = line.split("Deadline")[1];

                if (line.contains("(done)")) {
                    isDone = true;
                    line = line.substring("(done):".length()).trim();
                } else {
                    line = line.substring("(not done):".length()).trim();
                }
                fw.close();
                return new Deadline("deadline" + line, isDone);
            default:
                return new Todo("");
        }
    }

    void writeAsDone(int index) {
        try {
            Scanner s = new Scanner(new File(filepath));
            ArrayList<String> lines = new ArrayList<String>();
            while (s.hasNextLine()) {
                lines.add(s.nextLine());
            }
            s.close();
            FileWriter fw = new FileWriter(filepath, false);
            fw.write("");
            fw.close();
            fw = new FileWriter(filepath, true);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                if (line.contains("[" + String.valueOf(index) + "]")) {
                    line = line.replace("not done", "done");
                }
                fw.write(line + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}