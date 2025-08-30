package nyanchan.app;

import nyanchan.exceptions.NyanException;
import nyanchan.tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    // Loads tasks from file into memory
    public List<Task> load() throws NyanException {
        List<Task> tasks = new ArrayList<>();
        try {
            Save.read(tasks);
        } catch (FileNotFoundException e) {
            throw new NyanException("Could not find save file at: " + filepath);
        } catch (Exception e) {
            throw new NyanException("Error while loading tasks.");
        }
        return tasks;
    }

    // Saves the given tasks to the file
    public void save(List<Task> tasks) throws NyanException {
        try {
            Save.write(tasks);
        } catch (Exception e) {
            throw new NyanException("Error while saving tasks.");
        }
    }
}
