package com.project.taskmanager;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Contains JUnit tests that ensure the program works correctly.
 */
public class Tester {
    @Test
    public void testParser() {
        Parser parser = new Parser();
        assertEquals("todo", parser.getCommandWord("todo submit project"));
        assertEquals("deadline", parser.getCommandWord("deadline run all tests /by tonight"));
        assertEquals("exit", parser.getCommandWord("exit"));
        assertEquals("xyz", parser.getCommandWord("   xyz   "));
    }

    @Test
    public void testTodo() {
        UI ui = new UI();
        Database database;
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        String filePath = "C:\\Users\\USER\\Desktop\\Tests.txt";

        database = new Database(filePath);
        parser.parseInput(filePath, "todo test 1", ui, tasks, database);

        assertEquals("todo test 1\n\tis done? No", tasks.get(0).getTask());
        ui.printTasks(tasks);
    }

    @Test
    public void testDeadline() {
        UI ui = new UI();
        Database database;
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        String filePath = "C:\\Users\\USER\\Desktop\\Tests.txt";

        database = new Database(filePath);
        parser.parseInput(filePath, "deadline test 2 /by before exiting", ui, tasks, database);

        assertEquals("deadline test 2\n\tis done? No\n\tdo by: before exiting", tasks.get(0).getTask());
        ui.printTasks(tasks);
    }

    @Test
    public void testChangeDone() {
        UI ui = new UI();
        Database database;
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        String filePath = "C:\\Users\\USER\\Desktop\\Tests.txt";

        database = new Database(filePath);
        parser.parseInput(filePath, "todo test 3", ui, tasks, database);
        tasks.changeDone(filePath, "done 1", ui, tasks, database);

        assertTrue(tasks.get(0).getDone());
        ui.printTasks(tasks);
    }

    @Test
    public void testExit() {
        UI ui = new UI();
        Database database;
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        String filePath = "C:\\Users\\USER\\Desktop\\Tests.txt";

        database = new Database(filePath);
        assertFalse(parser.parseInput(filePath, "exit", ui, tasks, database));
    }
}