package todo.test.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

public class TabTemplateController {


	private ObservableList<TodoTask> taskList;


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
	private Spinner<String> spinHour;
	@FXML
	private Spinner<String> spinMin;
	@FXML
	private Spinner<String> spinAMPM;
	@FXML
	private CheckBox chkCompleted;

	@FXML
	void initialize() {
		setupTable();

		setupDateTime();
	}

	private void setupDateTime() {
		datePicker.setMaxWidth(110);

		timeVisible(false);
		ObservableList<String> hourList = FXCollections.observableArrayList("");
		for (int i = 1; i <= 12; i++)
			hourList.add(Integer.toString(i));
		spinHour.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(hourList));

		ObservableList<String> minList = FXCollections.observableArrayList("");
		for (int i = 0; i < 60; i++) {
			if (i < 10)
				minList.add("0" + i);
			else
				minList.add(Integer.toString(i));
		}

		spinMin.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(minList));
		spinAMPM.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.observableArrayList("", "AM", "PM")));
	}

	private void setupTable() {
		colNote.setCellValueFactory(c -> c.getValue().titleProperty());
		colDate.setCellValueFactory(c -> c.getValue().dueDisplayProperty());
		colCompleted.setCellFactory(CheckBoxTableCell.forTableColumn(colCompleted));
		colCompleted.setCellValueFactory(c -> c.getValue().completedProperty());

		taskList = FXCollections.observableArrayList();
		table.setItems(taskList);

		// displays selected task in view window when selected from table
		table.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TodoTask>) c -> taskSelected());
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
		taskList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(), due));
		clearPrompts();
	}

	private void clearPrompts() {
		txtTask.clear();
		txtTask.setPromptText("Task goes here");
		txtTitle.clear();
		txtTitle.setPromptText("Task Title");
		datePicker.setValue(null);
		chkCompleted.setSelected(false);
	}

	public void displayTime(ActionEvent event) {
		DatePicker date = (DatePicker) event.getSource();
		timeVisible(date.getValue() != null);
	}

	private void timeVisible(boolean visible) {
		spinHour.setVisible(visible);
		spinMin.setVisible(visible);
		spinAMPM.setVisible(visible);
//		lblAMPM.setVisible(visible);
	}
}
