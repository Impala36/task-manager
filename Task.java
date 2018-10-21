package com.project.taskmanager;

/**
 * An abstract class that is inherited by the Todo and Deadline classes.
 */
abstract class Task {
    static String task;
    protected boolean isDone;
    private TaskList tasks;

    Task(String input) {
        task = input;
    }

    private void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    abstract String getTask();

    static void changeDone(String line) {
        line = line.substring("done".length()).trim();
        try {
            if (!line.isEmpty()) {
                markAsDone(line);
            } else {
                throw new TaskManagerException(UI.colorRed("Please type in the" +
                        " 'done <task index number>' format."));
            }
        } catch (TaskManagerException e) {
            UI.printError(e.getMessage());
        }
    }

    private static void markAsDone(String line) {
        try {
            if (TaskList.size() != 0) {
                int index = Integer.parseInt(line);
                TaskList.get(index - 1).setDone(true);
                UI.markedAsDone(index);
                UI.showTotal();
            } else System.out.println(UI.colorRed("There are no tasks to mark as done."));
        } catch (NumberFormatException e) {
            System.out.println(UI.colorRed("Please type a number for the index."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(UI.colorRed("Please type an index number from 1 to "
                    + TaskList.size() + "."));
        }
    }
}