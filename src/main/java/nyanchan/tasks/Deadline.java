package nyanchan.tasks;

import nyanchan.exceptions.IncorrectFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Deadline extends Task {
    private String by;
    private LocalDate deadline;

    // Formatter for parsing input
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Formatter for displaying nicely
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by) throws IncorrectFormatException {
        super(description);
        this.convertToDate(by);
    }

    public String getBy() {
        return this.by;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void convertToDate(String by) throws IncorrectFormatException {
        try {
            this.deadline = LocalDate.parse(by, INPUT_FORMAT);
            this.by = this.deadline.format(OUTPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new IncorrectFormatException("HISS, invalid date format! Use dd/MM/yyyy!");
        }
    }

    @Override
    public String toString() {
        String formattedDate = this.deadline.format(OUTPUT_FORMAT);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
