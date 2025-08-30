package nyanchan.app;

import nyanchan.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new Task("Do homework");
        task2 = new Task("Go shopping");
    }

    @Test
    void testAddAndSize() {
        assertEquals(0, taskList.size(), "TaskList should start empty");

        taskList.add(task1);
        assertEquals(1, taskList.size(), "TaskList size should be 1 after adding a task");

        taskList.add(task2);
        assertEquals(2, taskList.size(), "TaskList size should be 2 after adding another task");
    }

    @Test
    void testGet() {
        taskList.add(task1);
        taskList.add(task2);

        assertEquals(task1, taskList.get(0), "First task should be task1");
        assertEquals(task2, taskList.get(1), "Second task should be task2");
    }

    @Test
    void testDelete() {
        taskList.add(task1);
        taskList.add(task2);

        Task removed = taskList.delete(0);
        assertEquals(task1, removed, "Deleted task should be task1");
        assertEquals(1, taskList.size(), "Size should decrease after deletion");
        assertEquals(task2, taskList.get(0), "Remaining task should be task2");
    }

    @Test
    void testIsEmpty() {
        assertTrue(taskList.isEmpty(), "TaskList should start empty");

        taskList.add(task1);
        assertFalse(taskList.isEmpty(), "TaskList should not be empty after adding a task");

        taskList.delete(0);
        assertTrue(taskList.isEmpty(), "TaskList should be empty after deleting the only task");
    }

    @Test
    void testGetAll() {
        taskList.add(task1);
        taskList.add(task2);

        List<Task> allTasks = taskList.getAll();
        assertEquals(2, allTasks.size(), "getAll should return all tasks");
        assertTrue(allTasks.contains(task1), "getAll should contain task1");
        assertTrue(allTasks.contains(task2), "getAll should contain task2");
    }
}

