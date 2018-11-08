package com.project.taskmanager;

/**
 * An abstract class that is inherited by the Todo and Deadline classes.
 */
abstract class Task {
    String task;
    private boolean isDone;

    Task(String input) {
        task = input;
    }

    void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    boolean getDone() {
        return isDone;
    }

    String getDescription() {
        return task;
    }

    abstract String getTask();
}