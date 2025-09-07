package nyanchan.app;

import nyanchan.tasks.Task;

import java.util.Scanner;

public class Ui {
    // Return welcome message
    public String showWelcome() {
        return "MEOW! I'm NyanChan!\nWhat can I do for you?";
    }

    // Return error message
    public String showError(String message) {
        return "HISS! " + message;
    }

    // Return goodbye
    public String showGoodbye() {
        return "Purr... Hope to see you again!";
    }

    // Return task list
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "Nothing stored yet!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    // Return find results
    public String showFindResults(TaskList matchedTasks, String keyword) {
        if (matchedTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchedTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(matchedTasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }

    // Return messages for add/delete/mark/unmark
    public String showMarkTask(Task t) {
        return "Meow, I've marked this task as done:\n  " + t;
    }

    public String showUnmarkTask(Task t) {
        return "Meow, I've marked this task as not done yet:\n  " + t;
    }

    public String showDeleteTask(TaskList tasks, Task t) {
        return "Meow, I've removed this task:\n  " + t + "\nNyow you have " + tasks.size() + " tasks.";
    }

    public String showAddTask(TaskList tasks, Task t) {
        return "Nyan! I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks.";
    }
}
