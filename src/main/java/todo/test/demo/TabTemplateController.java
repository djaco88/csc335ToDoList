package todo.test.demo;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

public class TabTemplateController {

	@FXML
	private TableView<TodoTask> table;
	@FXML
	private TableColumn<TodoTask, Boolean> colCompleted;
	@FXML
	private TableColumn<TodoTask, String> colNote;
	@FXML
	private TableColumn<TodoTask, String> colDate;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextArea txtTask;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Spinner spinHour;
	@FXML
	private Spinner spinMin;
	@FXML
	private CheckBox chkCompleted;
	@FXML
	private Button btnSubmit;

	@FXML
	void initialize() {
		// sets up table and columns
		colNote.setCellValueFactory(c -> c.getValue().titleProperty());
		colDate.setCellValueFactory(c -> c.getValue().dueDisplayProperty());
		colCompleted.setCellFactory(CheckBoxTableCell.forTableColumn(colCompleted));
		colCompleted.setCellValueFactory(c -> c.getValue().completedProperty());
		table.setItems(Main.todoList);

		datePicker.setMaxWidth(110);
		// displays selected task in view window when selected from table
		table.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TodoTask>) c -> {
			taskSelected();
		});
	}

	public void taskSelected() {
		TodoTask task = table.getSelectionModel().getSelectedItem();
		txtTitle.setText(task.getTitle());
		txtTask.setText(task.getDescription());
		chkCompleted.setSelected(task.isCompleted());
	}

	public void newTask() {
		clearPrompts();
	}

	public void addTask() {
		String due = "";
		if (datePicker.getValue() != null)
			due = datePicker.getValue().toString();
		Main.todoList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(), due));
		clearPrompts();
	}

	private void clearPrompts() {
		txtTask.clear();
		txtTask.setPromptText("Task goes here");
		txtTitle.clear();
		txtTitle.setPromptText("Task Title");
		chkCompleted.setSelected(false);
		datePicker.setValue(null);
	}

}
