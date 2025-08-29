package app;

import exceptions.NyanException;
import exceptions.IncorrectFormatException;

import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Save {
    private static final String FILE_PATH = "./data/nyanchan.txt";

    // Read from nyanchan.txt and load into task_list
    public static void read(List<Task> to) throws FileNotFoundException {
        File file = new File(FILE_PATH);

        // Create folder and file if missing
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                return;
            } catch (IOException e) {
                System.out.println("HISS! Error in creating file.");
                return;
            }
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                to.add(Save.unserialize(line));
            } catch (IncorrectFormatException e) {
                System.out.println("HISS! Data corrupted.");
            }
        }
    }

    // Write into nyanchan.txt and save the tasks from task_list
    public static void write(List<Task> from) {
        File file = new File(Save.FILE_PATH);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task t : from) {
                try {
                    writer.write(Save.serialize(t) + System.lineSeparator());
                } catch (IncorrectFormatException e) {
                    System.out.println("HISS! Error in serializing.");
                }
            }
        } catch (IOException e) {
            System.out.println("HISS! Error in writer.");
        }
    }

    // Serialize a task into its string format
    public static String serialize(Task t) throws IncorrectFormatException {
        String done = t.getDone() ? "1" : "0";
        if (t instanceof Todo) {
            return "T | " + done + " | " + t.getDescription();
        } else if (t instanceof Deadline d) {
            return "D | " + done + " | " + d.getDescription() + " | " + d.getBy();
        } else if (t instanceof Event e) {
            return "E | " + done + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        } else {
            throw new IncorrectFormatException();
        }
    }

    // Unserialize a line of string into a task
    public static Task unserialize(String s) throws IncorrectFormatException {
        String[] elements = s.split(" \\| ");
        Task task;
        if (elements[0].equals("T")) {
            task = new Todo(elements[2]);
        } else if (elements[0].equals("D")) {
            task = new Deadline(elements[2], elements[3]);
        } else if (elements[0].equals("E")) {
            task = new Event(elements[2], elements[3], elements[4]);
        } else {
            throw new IncorrectFormatException();
        }
        boolean done = elements[1].equals("1");
        if (done) {
            task.markAsDone();
        }
        return task;
    }
}
