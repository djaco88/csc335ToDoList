package todo.test.demo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class TodoTask {

	private final StringProperty title;
	private final StringProperty description;
	private final BooleanProperty completed;
	private final StringProperty dueDisplay;
	private LocalTime time;
	private LocalDate date;


	// Constructor without time. Date can be null
	public TodoTask(String title, String description, boolean completed, LocalDate date) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		if (date != null) this.dueDisplay = new SimpleStringProperty(date.toString());
		else this.dueDisplay = new SimpleStringProperty();
		this.date = date;
		this.time = null;
	}

	// Constructor with time.
	public TodoTask(String title, String description, boolean completed, LocalDate date, String hourValue,
	                String minValue, String timeOfDay) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		this.dueDisplay = new SimpleStringProperty(date.toString());
		this.date = date;
		if (timeOfDay.contains("P")) time = LocalTime.of(Integer.parseInt(hourValue) + 12, Integer.parseInt(minValue));
		else time = LocalTime.of(Integer.parseInt(hourValue), Integer.parseInt(minValue));

	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		if (date != null) this.dueDisplay.setValue(date.toString());
		this.date = date;
	}

	public String getTitle() {
		return title.get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public boolean isCompleted() {
		return completed.get();
	}

	public void setCompleted(boolean completed) {
		this.completed.set(completed);
	}

	public BooleanProperty completedProperty() {
		return completed;
	}

	public StringProperty dueDisplayProperty() {
		return dueDisplay;
	}

	@Override
	public String toString() {
		return "TodoTask{" + "title=" + title + ", description=" + description + ", completed=" + completed + ", " +
				"dueDisplay=" + dueDisplay + ", time=" + time + ", date=" + date + "}";
	}
}
