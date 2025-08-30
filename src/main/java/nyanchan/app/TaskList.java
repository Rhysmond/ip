package nyanchan.app;

import nyanchan.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    // add task to list
    public void add(Task task) {
        tasks.add(task);
    }

    // delete task from list, returns task
    public Task delete(int index) {
        return tasks.remove(index);
    }

    // get task from list
    public Task get(int index) {
        return tasks.get(index);
    }

    // return size of task
    public int size() {
        return tasks.size();
    }

    // return list
    public List<Task> getAll() {
        return tasks;
    }

    // return is empty
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
