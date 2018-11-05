package com.project.taskmanager;

import java.io.IOException;

/**
 * Contains methods that deals with parsing the user command to extract meaningful details from it.
 */
class Parser {

    String getCommandWord(String input) {
        return input.trim().split(" ")[0];
    }

    boolean parseInput(String line, UI ui, TaskList tasks, Database database) {

        String command = getCommandWord(line);
        switch (command) {
            case "commands":
                ui.commandsMessage();
                break;
            case "todo":
                createTodo(line, ui, tasks, database);
                break;
            case "deadline":
                createDeadline(line, ui, tasks, database);
                break;
            case "done":
                tasks.changeDone(line, ui, tasks);
                break;
            case "print":
                ui.printTasks(tasks);
                break;
            case "exit":
            case "": // also exit when user input is empty
                ui.exit();
                return false;
            default:
                ui.invalidCommand();
                return true;
        }
        return true;
    }
    private void createTodo(String line, UI ui, TaskList tasks, Database database) {
        try {
            if (line.substring("todo".length()).trim().isEmpty()) {
                throw new TaskManagerException
                        (ui.colorRed("Please type in the 'todo <something>' format."));
            } else if (line.contains("todo")) {
                tasks.add(new Todo(line));
                database.createTask(database.filepath, line, tasks);
                ui.showTotal(tasks);
            }
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError(ui.colorRed("IOException encountered: " + e.getMessage()));
        }
    }

    private void createDeadline(String line, UI ui, TaskList tasks, Database database) {
        try {
            if (line.substring("deadline".length()).trim().isEmpty() || !line.contains("/by")) {
                throw new TaskManagerException
                        (ui.colorRed("Please type in the 'deadline <something> /by <when>' format."));
            } else if (line.contains("deadline")) {
                String[] split = line.split("/by");
                if (split[0].isEmpty() || split[1].isEmpty()) {
                    throw new TaskManagerException
                            (ui.colorRed("Please type in the 'deadline " +
                                    "<something> /by <when>' format."));
                } else if (line.contains("deadline")) {
                    tasks.add(new Deadline(line));
                    database.createTask(database.filepath, line, tasks);
                    ui.showTotal(tasks);
                }
            }
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.colorRed("Please type in something for <when>" +
                    " after 'deadline <something> /by'."));
        } catch (IOException e) {
            ui.printError(ui.colorRed("IOException encountered: " + e.getMessage()));
        }
    }

}