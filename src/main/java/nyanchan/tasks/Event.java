package nyanchan.tasks;

import nyanchan.exceptions.IncorrectFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class Event extends Task {
    private String from;
    private String to;
    private LocalDate startDate;
    private LocalDate endDate;

    // Formatter for parsing input
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Formatter for displaying nicely
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, String from, String to) throws IncorrectFormatException {
        super(description);
        this.convertToDates(from, to);
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    private void convertToDates(String from, String to) throws IncorrectFormatException {
        try {
            this.startDate = LocalDate.parse(from, INPUT_FORMAT);
            this.endDate = LocalDate.parse(to, INPUT_FORMAT);
            this.from = this.startDate.format(OUTPUT_FORMAT);
            this.to = this.endDate.format(OUTPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new IncorrectFormatException("HISS, invalid date format! Use dd/MM/yyyy!");
        }
    }

    @Override
    public String toString() {
        String formattedFrom = this.startDate.format(OUTPUT_FORMAT);
        String formattedTo = this.endDate.format(OUTPUT_FORMAT);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }
}
