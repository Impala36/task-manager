package com.project.taskmanager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for keeping the in-memory task list. This class will use an ArrayList inside it.
 */
class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        tasks = new ArrayList<>();
    }

    int size() {
        return tasks.size();
    }

    void add(Task task) {
        tasks.add(task);
    }

    Task get(int index) {
        return tasks.get(index);
    }

    void changeDone(String line, UI ui, TaskList tasks) {
        line = line.substring("done".length()).trim();
        try {
            if (!line.isEmpty()) {
                markAsDone(line, ui, tasks);
            } else {
                throw new TaskManagerException(ui.colorRed("Please type in the" +
                        " 'done <task index number>' format."));
            }
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
        }
    }

    private void markAsDone(String line, UI ui, TaskList tasks) {
        try {
            if (tasks.size() != 0) {
                int index = Integer.parseInt(line);
                tasks.get(index - 1).setDone(true);
                ui.markedAsDone(index, tasks);
                ui.showTotal(tasks);
            } else System.out.println(ui.colorRed("There are no tasks to mark as done."));
        } catch (NumberFormatException e) {
            System.out.println(ui.colorRed("Please type a number for the index."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ui.colorRed("Please type an index number from 1 to "
                    + tasks.size() + "."));
        }
    }
}