package todo.test.demo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TodoTask {

	private StringProperty title;
	private StringProperty description;
	private BooleanProperty completed;
	private StringProperty dueDisplay;

	public TodoTask(String title, String description, boolean completed, String dueDisplay) {
		this.title = new SimpleStringProperty(title);
		this.description = new SimpleStringProperty(description);
		this.completed = new SimpleBooleanProperty(completed);
		this.dueDisplay = new SimpleStringProperty(dueDisplay);
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
}
