import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class NyanChan {
    public static void main(String[] args) {
        String line_break = "____________________________________________________________\n";
        Scanner scanner = new Scanner(System.in);

        List<String> task_list = new ArrayList<>();

        System.out.println(line_break + " Hello! I'm NyanChan!\n What can I do for you?\n" + line_break);

        while (true) {
            String user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                System.out.print(line_break + " Bye. Hope to see you again soon!\n" + line_break);
                break;
            } else if (user_input.equals("list")) {
                if (task_list.isEmpty()) {
                    System.out.print(line_break + " Nothing stored yet!\n" + line_break);
                } else {
                    System.out.print(line_break);
                    for (int i = 0; i < task_list.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + task_list.get(i));
                    }
                    System.out.print(line_break);
                }
            } else {
                task_list.add(user_input);
                System.out.print(line_break + " added: " + user_input + "\n" + line_break);
            }
        }
    }
}
