package com.project.taskmanager;

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

    Deadline(String input, boolean isDone) {
        super(input);
        split = input.split("\\| Do by:");
        setDone(isDone);
    }

    String getTask() {
        return split[0].trim() + "\n\tis done? " + isDone() + "\n\tdo by: " + split[1].trim();
    }
}