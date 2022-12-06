package todo.test.demo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class TodoTask {

	private final StringProperty title;
	private final StringProperty description;
	private final BooleanProperty completed;
	private final StringProperty dueDisplay;
	private LocalDate date;
	private String hourValue;
	private String minValue;
	private String timeOfDay;


	// Constructor without time. Date can be null
	public TodoTask(String title, String description, boolean completed, LocalDate date) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		if (date != null) this.dueDisplay = new SimpleStringProperty(date.toString());
		else this.dueDisplay = new SimpleStringProperty();
		this.date = date;
		this.hourValue = null;
		this.minValue = null;
		this.timeOfDay = null;
	}

	// Constructor with time.
	public TodoTask(String title, String description, boolean completed, LocalDate date, String hourValue,
	                String minValue, String timeOfDay) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		this.date = date;
		if (date != null) this.dueDisplay = new SimpleStringProperty(date.toString());
		else this.dueDisplay = new SimpleStringProperty();
		this.hourValue = hourValue;
		this.minValue = minValue;
		this.timeOfDay = timeOfDay;

	}

	public LocalDate date() {
		return date;
	}

	public String getHourValue() {
		return hourValue;
	}

	public void setHourValue(String hourValue) {
		this.hourValue = hourValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
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

}
