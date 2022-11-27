package todo.test.demo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TodoTask {

	private LocalTime time;
	private LocalDate dueDate;
	private StringProperty title;
	private StringProperty description;
	private BooleanProperty completed;
	private StringProperty dueDisplay;
	private LocalDateTime dueDateTime;

	public TodoTask(String title, String description, boolean completed, LocalDate dueDate) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		this.dueDisplay = new SimpleStringProperty(dueDate.toString());
		this.dueDate = dueDate;
	}

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

		dueDateTime = LocalDateTime.of(dueDate, time);
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

	public StringProperty descriptionProperty() {
		return description;
	}

	public boolean isCompleted() {
		return completed.get();
	}

	public BooleanProperty completedProperty() {
		return completed;
	}

	public String getDueDisplay() {
		return dueDisplay.get();
	}

	public StringProperty dueDisplayProperty() {
		return dueDisplay;
	}

	public LocalDateTime getDueDateTime() {
		return dueDateTime;
	}

	public LocalDate getDate() {
		return dueDate;
	}

	public LocalTime getTime() {
		return time;
	}
}
