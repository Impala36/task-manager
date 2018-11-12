package com.project.taskmanager;

/**
 * Adds to the task list a Todo task with the given task description.
 */
class Todo extends Task {

    Todo(String input) {
        super(input);
    }

    Todo(String input, boolean isDone) {
        super(input);
        setDone(isDone);
    }

    String isDone() {
        if (getDone()) return "Yes";
        else return "No";
    }

    String getTask() {
        return task + "\n\tis done? " + isDone();
    }
}