import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class NyanChan {
    public static void main(String[] args) {
        String line_break = "____________________________________________________________\n";
        Scanner scanner = new Scanner(System.in);
        List<Task> task_list = new ArrayList<>();

        System.out.println(line_break + "MEOW! I'm NyanChan!\nWhat can I do for you?\n" + line_break);

        while (true) {
            String user_input = scanner.nextLine().trim();

            try {
                if (user_input.equals("bye")) {
                    System.out.print(line_break + "Purr... Hope to see you again!\n" + line_break);
                    break;
                }

                // LIST
                else if (user_input.equals("list")) {
                    if (task_list.isEmpty()) {
                        System.out.println(line_break + "Nothing stored yet!\n" + line_break);
                    } else {
                        System.out.print(line_break + "Here are the tasks in your list:\n");
                        for (int i = 0; i < task_list.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + task_list.get(i));
                        }
                        System.out.println(line_break);
                    }
                }

                // MARK
                else if (user_input.startsWith("mark ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("HISS! Invalid task number to mark.");
                    }
                    Task t = task_list.get(task_index);
                    t.markAsDone();
                    System.out.println(line_break + "Meow, I've marked this task as done:\n  " + t + "\n" + line_break);
                }

                // UNMARK
                else if (user_input.startsWith("unmark ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("HISS! Invalid task number to unmark.");
                    }
                    Task t = task_list.get(task_index);
                    t.markAsNotDone();
                    System.out.println(line_break + "Meow, I've marked this task as not done yet:\n  " + t + "\n" + line_break);
                }

                // DELETE
                else if (user_input.startsWith("delete ")) {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    if (task_index < 0 || task_index >= task_list.size()) {
                        throw new NyanException("HISS! Invalid task number to delete.");
                    }
                    Task t = task_list.get(task_index);
                    task_list.remove(task_index);
                    System.out.println(line_break + "Meow, I've removed this task:\n  " + t + "\n"+ "Nyow you have "
                    + task_list.size() + " tasks in the list.\n" + line_break);
                }

                // TODO
                else if (user_input.startsWith("todo")) {
                    String description = user_input.length() > 5 ? user_input.substring(5).trim() : "";
                    if (description.isEmpty()) {
                        throw new NyanException("HISS! The description of a todo cannot be empty.");
                    }
                    Task task = new Todo(description);
                    task_list.add(task);
                    System.out.println(line_break + "Nyan! I've added this task:\n  " + task + "\nNow you have "
                            + task_list.size() + " tasks in the list.\n" + line_break);
                }

                // DEADLINE
                else if (user_input.startsWith("deadline")) {
                    String[] parts = user_input.substring(9).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new NyanException("HISS! Deadline format should be: deadline <desc> /by <time>");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    Task task = new Deadline(description, by);
                    task_list.add(task);
                    System.out.println(line_break + "Nyan! I've added this task:\n  " + task + "\nNow you have "
                            + task_list.size() + " tasks in the list.\n" + line_break);
                }

                // EVENT
                else if (user_input.startsWith("event")) {
                    String[] parts = user_input.substring(6).split("/from", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                        throw new NyanException("HISS! Event format should be: event <desc> /from <start> /to <end>");
                    }
                    String description = parts[0].trim();
                    String[] time_parts = parts[1].split("/to", 2);
                    if (time_parts.length < 2 || time_parts[0].trim().isEmpty() || time_parts[1].trim().isEmpty()) {
                        throw new NyanException("HISS! Event format should be: event <desc> /from <start> /to <end>");
                    }
                    String from = time_parts[0].trim();
                    String to = time_parts[1].trim();
                    Task task = new Event(description, from, to);
                    task_list.add(task);
                    System.out.println(line_break + "Nyan! I've added this task:\n  " + task + "\nNow you have "
                            + task_list.size() + " tasks in the list.\n" + line_break);
                }

                // UNKNOWN COMMAND
                else {
                    throw new NyanException("HISS! I don't recognize that command.");
                }

            } catch (NyanException e) {
                System.out.println(line_break + e.getMessage() + "\n" + line_break);
            } catch (NumberFormatException e) {
                System.out.println(line_break + "HISS! Task number should be an integer.\n" + line_break);
            } catch (Exception e) {
                System.out.println(line_break + "HISS! Something went wrong: " + e.getMessage() + "\n" + line_break);
            }
        }

        scanner.close();
    }
}
