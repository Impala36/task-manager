package com.project.taskmanager;

import java.io.IOException;

/**
 * Adds to the task list a Deadline task with the given task description
 * and with the deadline description.
 */
class Deadline extends Todo {
    private String[] split;

    Deadline(String input) {
        super(input);
        split = input.split("/by");
    }

    String getTask() {
        return split[0] + "\n\tis done? " + isDone() + "\n\tdo by: " + split[1];
    }

    static void createDeadline(String line) {
        try {
            if (line.substring("deadline".length()).trim().isEmpty() || !line.contains("/by")) {
                throw new TaskManagerException
                        (UI.colorRed("Please type in the 'deadline <something> /by <when>' format."));
            } else if (line.contains("deadline")) {
                String[] split = line.split("/by");
                if (split[0].isEmpty() || split[1].isEmpty()) {
                    throw new TaskManagerException
                            (UI.colorRed("Please type in the 'deadline " +
                                    "<something> /by <when>' format."));
                } else if (line.contains("deadline")) {
                    TaskList.add(new Deadline(line));
                    Database.createTask(Database.filepath, line);
                    UI.showTotal();
                }
            }
        } catch (TaskManagerException e) {
            UI.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(UI.colorRed("Please type in something for <when>" +
                    " after 'deadline <something> /by'."));
        } catch (IOException e) {
            UI.printError(UI.colorRed("IOException encountered: " + e.getMessage()));
        }
    }
}