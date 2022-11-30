package todo.test.demo;

import java.io.Serializable;
import java.time.LocalDate;

public record TaskData(String title, String description, boolean completed, LocalDate date, String hourValue,
                       String minValue, String timeOfDay) implements Serializable {

}
