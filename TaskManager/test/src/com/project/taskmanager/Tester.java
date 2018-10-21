package com.project.taskmanager;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Contains JUnit tests that ensure the program works correctly.
 */
class Tester {
    @Test
    void getCommandWord() {
        assertEquals("todo", Parser.getCommandWord("todo read book"));
        assertEquals("deadline", Parser.getCommandWord("deadline return book /by next Friday"));
        assertEquals("exit", Parser.getCommandWord("exit"));
        assertEquals("xyz", Parser.getCommandWord("   xyz   ")); // leading and trailing spaces
        // ...
    }

    @Test
    void createTodo() {
//                Todo actual = Todo.createTodo("todo read book");
//                Todo expected = new Todo("read book");
//                assertEquals(expected.toString(), actual.toString());
    }
}