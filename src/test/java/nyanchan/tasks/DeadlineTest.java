package nyanchan.tasks;

import nyanchan.exceptions.IncorrectFormatException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testValidDeadlineCreation() throws IncorrectFormatException {
        Deadline d = new Deadline("Finish homework", "23/08/2025");

        // Check description inherited from Task
        assertEquals("Finish homework", d.getDescription());

        // Check getBy() returns correctly formatted date
        assertEquals("Aug 23 2025", d.getBy());

        // Check getDeadline() returns correct LocalDate
        LocalDate expectedDate = LocalDate.of(2025, 8, 23);
        assertEquals(expectedDate, d.getDeadline());
    }

    @Test
    void testInvalidDeadlineThrowsException() {
        // Invalid format should throw IncorrectFormatException
        assertThrows(IncorrectFormatException.class, () -> {
            new Deadline("Finish homework", "2025-08-23");
        });
    }

    @Test
    void testConvertToDateMethod() throws IncorrectFormatException {
        Deadline d = new Deadline("Task", "01/01/2025");

        // Change date using convertToDate
        d.convertToDate("15/12/2025");

        assertEquals("Dec 15 2025", d.getBy());
        assertEquals(LocalDate.of(2025, 12, 15), d.getDeadline());
    }

    @Test
    void testToString() throws IncorrectFormatException {
        Deadline d = new Deadline("Finish homework", "23/08/2025");
        String expected = "[D][ ] Finish homework (by: Aug 23 2025)";
        assertEquals(expected, d.toString());
    }
}

