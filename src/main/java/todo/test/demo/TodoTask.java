package todo.test.demo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class TodoTask {

	private final LocalTime time;
	private final LocalDate dueDate;
	private final StringProperty title;
	private final StringProperty description;
	private final BooleanProperty completed;
	private StringProperty dueDisplay;


	// Constructor without time. Date can be null
	public TodoTask(String title, String description, boolean completed, LocalDate dueDate) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		if (dueDate != null)
			this.dueDisplay = new SimpleStringProperty(dueDate.toString());
		this.dueDate = dueDate;
		this.time = null;
	}

	// Constructor with time.
	public TodoTask(String title, String description, boolean completed, LocalDate dueDate, String hourValue, String minValue, String timeOfDay) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		this.dueDisplay = new SimpleStringProperty(dueDate.toString());
		this.dueDate = dueDate;
		if (timeOfDay.contains("P"))
			time = LocalTime.of(Integer.parseInt(hourValue) + 12, Integer.parseInt(minValue));
		else
			time = LocalTime.of(Integer.parseInt(hourValue), Integer.parseInt(minValue));

	}

	public String getTitle() {
		return title.get();
	}

	public StringProperty titleProperty() {
		return title;
	}

	public String getDescription() {
		return description.get();
	}

	public boolean isCompleted() {
		return completed.get();
	}

	public BooleanProperty completedProperty() {
		return completed;
	}

	public StringProperty dueDisplayProperty() {
		return dueDisplay;
	}

	public LocalDate getDate() {
		return dueDate;
	}

	public LocalTime getTime() {
		return time;
	}
}
