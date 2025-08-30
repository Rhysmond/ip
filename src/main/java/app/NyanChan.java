package app;

import exceptions.NyanException;
import exceptions.IncorrectFormatException;

import tasks.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class NyanChan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();
        List<Task> task_list = new ArrayList<>();

        try {
            Save.read(task_list);
        } catch (FileNotFoundException e) {
            ui.showError("File not found.");
        }

        ui.showWelcome();

        while (true) {
            String user_input = scanner.nextLine().trim();

            try {
                if (user_input.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                // LIST
                else if (user_input.equals("list")) {
                    ui.showTaskList(task_list);
                }

                // MARK
                else if (user_input.startsWith("mark ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("Invalid task number to mark.");
                    }
                    Task t = task_list.get(task_index);
                    t.markAsDone();
                    Save.write(task_list);
                    ui.showMarkTask(t);
                }

                // UNMARK
                else if (user_input.startsWith("unmark ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("Invalid task number to unmark.");
                    }
                    Task t = task_list.get(task_index);
                    t.markAsNotDone();
                    Save.write(task_list);
                    ui.showUnmarkTask(t);
                }

                // DELETE
                else if (user_input.startsWith("delete ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("Invalid task number to delete.");
                    }
                    Task t = task_list.get(task_index);
                    task_list.remove(task_index);
                    Save.write(task_list);
                    ui.showDeleteTask(task_list, t);
                }

                // TODO
                else if (user_input.startsWith("todo")) {
                    String description = user_input.length() > 5 ? user_input.substring(5).trim() : "";
                    if (description.isEmpty()) {
                        throw new NyanException("The description of a todo cannot be empty.");
                    }
                    Task task = new Todo(description);
                    task_list.add(task);
                    Save.write(task_list);
                    ui.showAddTask(task_list, task);
                }

                // DEADLINE
                else if (user_input.startsWith("deadline")) {
                    String[] parts = user_input.substring(9).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new NyanException("Deadline format should be: deadline <desc> /by <time>.");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    try {
                        Task task = new Deadline(description, by);
                        task_list.add(task);
                        Save.write(task_list);
                        ui.showAddTask(task_list, task);
                    } catch (IncorrectFormatException e) {
                        ui.showError("Invalid date/time format. Use dd/MM/yyyy.\n");
                    }
                }

                // EVENT
                else if (user_input.startsWith("event")) {
                    String[] parts = user_input.substring(6).split("/from", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
                    }
                    String description = parts[0].trim();
                    String[] time_parts = parts[1].split("/to", 2);
                    if (time_parts.length < 2 || time_parts[0].trim().isEmpty() || time_parts[1].trim().isEmpty()) {
                        throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
                    }
                    String from = time_parts[0].trim();
                    String to = time_parts[1].trim();
                    try {
                        Task task = new Event(description, from, to);
                        task_list.add(task);
                        Save.write(task_list);
                        ui.showAddTask(task_list, task);
                    } catch (IncorrectFormatException e) {
                        ui.showError("Invalid date/time format for event. Use dd/MM/yyyy.\n");
                    }
                }

                // UNKNOWN COMMAND
                else {
                    throw new NyanException("I don't recognize that command.");
                }

            } catch (NyanException e) {
                ui.showError(e.getMessage() + "\n");
            } catch (NumberFormatException e) {
                ui.showError("Task number should be an integer.\n");
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
    }
}
