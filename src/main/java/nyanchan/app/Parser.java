package nyanchan.app;

import nyanchan.exceptions.NyanException;
import nyanchan.exceptions.IncorrectFormatException;

import nyanchan.tasks.Task;
import nyanchan.tasks.Deadline;
import nyanchan.tasks.Todo;
import nyanchan.tasks.Event;

public class Parser {

    public static void handleCommand(String input, TaskList taskList, Ui ui, Storage storage)
            throws NyanException {
        input = input.trim();

        // Split the command keyword from the rest
        String command = input.contains(" ") ? input.substring(0, input.indexOf(" ")) : input;

        switch (command) {
            case "bye" -> {
                ui.showGoodbye();
                System.exit(0); // Or return a signal to main loop
            }
            case "list" -> ui.showTaskList(taskList);
            case "mark" -> handleMark(input, taskList, ui, storage, true);
            case "unmark" -> handleMark(input, taskList, ui, storage, false);
            case "delete" -> handleDelete(input, taskList, ui, storage);
            case "todo" -> handleTodo(input, taskList, ui, storage);
            case "deadline" -> handleDeadline(input, taskList, ui, storage);
            case "event" -> handleEvent(input, taskList, ui, storage);
            case "find" -> handleFind(input, taskList, ui);
            default -> throw new NyanException("I don't recognize that command.");
        }
    }

    // ---------------- Helper Methods ---------------- //

    private static int parseIndex(String input, TaskList taskList) throws NyanException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= taskList.size()) throw new NyanException("Invalid task number.");
            return index;
        } catch (NumberFormatException e) {
            throw new NyanException("Task number should be an integer.");
        }
    }

    private static void handleMark(String input, TaskList taskList, Ui ui, Storage storage, boolean mark)
            throws NyanException {
        int index = parseIndex(input, taskList);
        Task task = taskList.get(index);
        if (mark) task.markAsDone();
        else task.markAsNotDone();
        storage.save(taskList.getAll());
        if (mark) ui.showMarkTask(task);
        else ui.showUnmarkTask(task);
    }

    private static void handleDelete(String input, TaskList taskList, Ui ui, Storage storage)
            throws NyanException {
        int index = parseIndex(input, taskList);
        Task task = taskList.delete(index);
        storage.save(taskList.getAll());
        ui.showDeleteTask(taskList, task);
    }

    private static void handleTodo(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String description = input.length() > 5 ? input.substring(5).trim() : "";
        if (description.isEmpty()) throw new NyanException("The description of a todo cannot be empty.");
        Task task = new Todo(description);
        taskList.add(task);
        storage.save(taskList.getAll());
        ui.showAddTask(taskList, task);
    }

    private static void handleDeadline(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty())
            throw new NyanException("Deadline format should be: deadline <desc> /by <time>.");
        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.add(task);
            storage.save(taskList.getAll());
            ui.showAddTask(taskList, task);
        } catch (IncorrectFormatException e) {
            ui.showError("Invalid date/time format. Use dd/MM/yyyy.\n");
        }
    }

    private static void handleEvent(String input, TaskList taskList, Ui ui, Storage storage) throws NyanException {
        String[] parts = input.substring(6).split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty())
            throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("/to", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty())
            throw new NyanException("Event format should be: event <desc> /from <start> /to <end>.");
        try {
            Task task = new Event(description, timeParts[0].trim(), timeParts[1].trim());
            taskList.add(task);
            storage.save(taskList.getAll());
            ui.showAddTask(taskList, task);
        } catch (IncorrectFormatException e) {
            ui.showError("Invalid date/time format for event. Use dd/MM/yyyy.\n");
        }
    }

    private static void handleFind(String input, TaskList taskList, Ui ui) throws NyanException {
        String keyword = input.length() > 5 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) throw new NyanException("Please provide a keyword to search.");

        TaskList matchedTasks = new TaskList();

        for (Task task : taskList.getAll()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }

        ui.showFindResults(matchedTasks, keyword);
    }
}
