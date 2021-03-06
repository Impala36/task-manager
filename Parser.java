package com.project.taskmanager;

import java.io.IOException;

/**
 * Contains methods that deals with parsing the user command to extract meaningful details from it.
 */
class Parser {

    /**
     * @param line The entire user input.
     * @return Returns only the first word of the input, which should be the command.
     */
    String getCommandWord(String line) {
        return line.trim().split(" ")[0];
    }

    /**
     * @param filepath The file path as stated in the TaskManager class.
     * @param line The entire user input.
     * @param ui The UI created in the TaskManager class.
     * @param tasks The TaskList created in the TaskManager class.
     * @param database The Database created in the TaskManager class.
     * @return Returns true when an invalid command is given, to repeat user input.
     */
    boolean parseInput(String filepath, String line, UI ui, TaskList tasks, Database database) {

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
                tasks.changeDone(filepath, line, ui, tasks, database);
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
                database.createTask(database.filepath,line, ui, tasks);
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
                    database.createTask(database.filepath, line, ui, tasks);
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