import java.util.Scanner;

public class NyanChan {
    public static void main(String[] args) {
        String line_break = "____________________________________________________________\n";
        Scanner scanner = new Scanner(System.in);

        System.out.println(line_break + "Hello! I'm NyanChan!\nWhat can I do for you?\n" + line_break);

        while (true) {
            String user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                System.out.println(line_break + "Bye. Hope to see you again soon!\n" + line_break);
                break;
            } else {
                System.out.println(line_break + user_input + "\n" + line_break);
            }
        }
    }
}
