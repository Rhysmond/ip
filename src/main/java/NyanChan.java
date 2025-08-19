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
            String user_input = scanner.nextLine();
            // bye: Goodbye message
            if (user_input.equals("bye")) {
                System.out.print(line_break + "Purr... Hope to see you again!\n" + line_break);
                break;
            }

            // list: Listing out tasks
            else if (user_input.equals("list")) {
                if (task_list.isEmpty()) {
                    System.out.print(line_break + "Nothing stored yet!\n" + line_break);
                } else {
                    System.out.print(line_break);
                    for (int i = 0; i < task_list.size(); i++) {
                        System.out.println((i + 1) + "." + task_list.get(i));
                    }
                    System.out.print(line_break);
                }
            }

            // mark: Mark tasks
            else if (user_input.startsWith("mark ")) {
                try {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    Task t = task_list.get(task_index);
                    t.markAsDone();
                    System.out.print(line_break + "Nyan! I've marked this task as done:\n  " + t + "\n" + line_break);
                } catch (Exception e) {
                    System.out.print(line_break + "Hiss! Invalid task number.\n" + line_break);
                }
            }

            // unmark: Unmark tasks
            else if (user_input.startsWith("unmark ")) {
                try {
                    int task_index = Integer.parseInt(user_input.split(" ")[1]) - 1;
                    Task t = task_list.get(task_index);
                    t.markAsNotDone();
                    System.out.print(line_break + "Meow, I've marked this task as not done yet:\n  " + t + "\n" + line_break);
                } catch (Exception e) {
                    System.out.print(line_break + "Hiss! Invalid task number.\n" + line_break);
                }
            }

            // todo: Add todo task
            else if (user_input.startsWith("todo ")) {
                String description = user_input.substring(5).trim();
                Task task = new Todo(description);
                task_list.add(task);
                System.out.print(line_break + "Nyan! I've added this task:\n " + task + "\nNyow you have "
                + task_list.size() + " tasks in the list.\n" + line_break);
            }

            // deadline: Add a deadline task
            else if (user_input.startsWith("deadline ")) {
                try {
                    String[] parts = user_input.substring(9).split("/by", 2);
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    Task task = new Deadline(description, by);
                    task_list.add(task);
                    System.out.print(line_break + "Nyan! I've added this task:\n " + task + "\nNyow you have "
                    + task_list.size() + " tasks in the list.\n" + line_break);
                } catch (Exception e) {
                    System.out.print(line_break + "Hiss! Deadline format should be: deadline <desc> /by <time>\n"
                    + line_break);
                }
            }

            else if (user_input.startsWith("event ")) {
                try {
                    String[] parts = user_input.substring(6).split("/from", 2);
                    String description = parts[0].trim();
                    String[] time_parts = parts[1].split("/to", 2);
                    String from = time_parts[0].trim();
                    String to = time_parts[1].trim();
                    Task task = new Event(description, from, to);
                    task_list.add(task);
                    System.out.print(line_break + "Nyan! I've added this task:\n " + task + "\nNyow you have "
                            + task_list.size() + " tasks in the list.\n" + line_break);
                } catch (Exception e) {
                    System.out.print(line_break + "Hiss! Event format should be: event <desc> /from <start> /to <end>\n"
                    + line_break);
                }
            }

            // anything else: Add tasks
            else {
                Task task = new Task(user_input);
                task_list.add(task);
                System.out.print(line_break + "Nyan! I've added this task:\n " + task + "\nNyow you have "
                        + task_list.size() + " tasks in the list.\n" + line_break);
            }
        }
        scanner.close();
    }
}
