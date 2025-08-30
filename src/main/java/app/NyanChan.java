package app;

import exceptions.NyanException;
import exceptions.IncorrectFormatException;

import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class NyanChan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage("./data/nyanchan.txt");
        TaskList taskList = new TaskList();

        try {
            taskList = new TaskList(storage.load());
        } catch (NyanException e) {
            ui.showError(e.getMessage());
        }

        ui.showWelcome();

        while (true) {
            String userInput = scanner.nextLine();
            try {
                Parser.handleCommand(userInput, taskList, ui, storage);
            } catch (NyanException e) {
                ui.showError(e.getMessage() + "\n");
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage() + "\n");
            }
        }
    }
}