package com.project.taskmanager;

/**
 * Inherits the Exception class. <p>
 * Overrides the constructor that takes a String parameter with custom error information
 * when you create a TaskManagerException object.<p>
 * Throw a new TaskManagerException object when you detect some necessary information
 * is missing in the command. <p>
 * Catch that exception somewhere else and print the message inside the exception object.
 */
class TaskManagerException extends Exception {
    TaskManagerException(String input) {
        super(input);
    }
}