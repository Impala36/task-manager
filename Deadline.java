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

    String getTask() {
        return split[0] + "\n\tis done? " + isDone() + "\n\tdo by: " + split[1];
    }
}