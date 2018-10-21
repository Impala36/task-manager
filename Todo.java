package com.project.taskmanager;

import java.io.IOException;

/**
 * Adds to the task list a Todo task with the given task description.
 */
class Todo extends Task {
    protected boolean isDone;

    Todo(String input) {
        super(input);
        isDone = false;
    }

    String isDone() {
        if (isDone) return "Yes";
        else return "No";
    }

    String getTask() {
        return task + "\n\tis done? " + isDone();
    }

    static void createTodo(String line) {
        try {
            if (line.substring("todo".length()).trim().isEmpty()) {
                throw new TaskManagerException
                        (UI.colorRed("Please type in the 'todo <something>' format."));
            } else if (line.contains("todo")) {
                TaskList.add(new Todo(line));
                Database.createTask(Database.filepath, line);
                UI.showTotal();
            }
        } catch (TaskManagerException e) {
            UI.printError(e.getMessage());
        } catch (IOException e) {
            UI.printError(UI.colorRed("IOException encountered: " + e.getMessage()));
        }
    }
}