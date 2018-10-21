package com.project.taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for keeping the in-memory task list. This class will use an ArrayList inside it.
 */
class TaskList {
    private static List<Task> tasks;

    TaskList(List<Task> tasks) {
        TaskList.tasks = tasks;
    }

    TaskList() {
        tasks = new ArrayList<>();
    }

    static int size() {
        return tasks.size();
    }

    static void add(Task task) {
        tasks.add(task);
    }

    static Task get(int index) {
        return tasks.get(index);
    }
}