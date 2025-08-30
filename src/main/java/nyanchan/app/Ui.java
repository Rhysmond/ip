package nyanchan.app;

import nyanchan.tasks.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    // Prints welcome message to user.
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("MEOW! I'm NyanChan!");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    // Prints line break
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    // Read user input
    public String readCommand() {
        return scanner.nextLine();
    }

    // Show error message
    public void showError(String message) {
        showLine();
        System.out.println("HISS! " + message);
        showLine();
    }

    // Show task list
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showLine();
            System.out.println("Nothing stored yet!\n");
            showLine();
        } else {
            showLine();
            if (tasks.isEmpty()) {
                System.out.println("Nothing stored yet!\n");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
            }
            showLine();
        }
    }

    // Show mark task
    public void showMarkTask(Task t) {
        showLine();
        System.out.println("Meow, I've marked this task as done:\n  " + t + "\n");
        showLine();
    }

    // Show unmark task
    public void showUnmarkTask(Task t) {
        showLine();
        System.out.println("Meow, I've marked this task as not done yet:\n  " + t + "\n");
        showLine();
    }

    // Show delete task
    public void showDeleteTask(TaskList tasks, Task t) {
        showLine();
        System.out.println("Meow, I've removed this task:\n  " + t + "\n"+ "Nyow you have "
                + tasks.size() + " tasks in the list.\n");
        showLine();
    }

    // Show todo
    public void showAddTask(TaskList tasks, Task t) {
        showLine();
        System.out.println("Nyan! I've added this task:\n  " + t + "\nNow you have "
                + tasks.size() + " tasks in the list.\n");
        showLine();
    }

    // Say goodbye to users
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Purr... Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
