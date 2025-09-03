package nyanchan.app;

import nyanchan.exceptions.NyanException;

import java.util.Scanner;

public class ChatBot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public ChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (NyanException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();

            try {
                if (userInput.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }
                Parser.handleCommand(userInput, taskList, ui, storage);
            } catch (NyanException e) {
                ui.showError(e.getMessage() + "\n");
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage() + "\n");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new ChatBot("./data/nyanchan.txt").run();
    }
}