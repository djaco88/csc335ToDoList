package todo.test.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.util.ArrayList;

public class TabTemplateController {

	private TodoTask tempTask;
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
	private ChoiceBox<String> boxAMPM;
	@FXML
	private ChoiceBox<String> boxMin;
	@FXML
	private ChoiceBox<String> boxHR;
	@FXML
	private CheckBox chkCompleted;

	@FXML
	void initialize() {
		setupTable();

		setupDateTime();
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

	private void setupDateTime() {
		datePicker.setMaxWidth(110);

		setTimeVisible(false);
		ObservableList<String> hourList = FXCollections.observableArrayList("HH");
		for (int i = 1; i <= 12; i++)
			hourList.add(Integer.toString(i));

		boxHR.setItems(FXCollections.observableArrayList(hourList));
		boxHR.setValue(boxHR.getItems().get(0));

		ObservableList<String> minList = FXCollections.observableArrayList("MM");
		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) if (i < 10) minList.add("0" + i);
			else minList.add(Integer.toString(i));
		}
		boxMin.setItems(FXCollections.observableArrayList(minList));
		boxMin.setValue(boxMin.getItems().get(0));

		boxAMPM.setItems(FXCollections.observableArrayList("AM/PM", "AM", "PM"));
		boxAMPM.setValue(boxAMPM.getItems().get(0));
	}

	private void taskSelected() {
		clearPrompts();
		tempTask = table.getSelectionModel().getSelectedItem();
		if (tempTask == null) return;
		txtTitle.setText(tempTask.getTitle());
		txtTask.setText(tempTask.getDescription());
		datePicker.setValue(tempTask.getDate());
		if (tempTask.getHourValue() != null) {
			boxHR.setValue(tempTask.getHourValue());
			boxMin.setValue(tempTask.getMinValue());
			boxAMPM.setValue(tempTask.getTimeOfDay());
		}
		chkCompleted.setSelected(tempTask.isCompleted());
	}

	private void setTimeVisible(boolean visible) {
		boxMin.setVisible(visible);
		boxHR.setVisible(visible);
		boxAMPM.setVisible(visible);
	}

	@FXML
	private void clearPrompts() {
		tempTask = null;
		txtTask.clear();
		txtTask.setPromptText("Task goes here");
		txtTitle.clear();
		txtTitle.setPromptText("Task Title");
		datePicker.setValue(null);
		boxHR.setValue(boxHR.getItems().get(0));
		boxMin.setValue(boxMin.getItems().get(0));
		boxAMPM.setValue(boxAMPM.getItems().get(0));
		chkCompleted.setSelected(false);
	}

	@FXML
	private void submitTask() {
		if (tempTask == null) newTask();
		else updateTask();

		clearPrompts();
		table.getSelectionModel().clearSelection();
	}

	private void newTask() {
		if (boxHR.getValue().equals("HH") && boxMin.getValue().equals("MM") && boxAMPM.getValue().equals("AM/PM"))
			taskList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(),
			                          datePicker.getValue()));
		else if ((boxHR.getValue().equals("HH") || boxMin.getValue().equals("MM") || boxAMPM.getValue().equals("AM/PM"))) {
			// TODO: Throw error
			System.out.println("TIME ERROR");
		} else
			taskList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(),
			                          datePicker.getValue(), boxHR.getValue(), boxMin.getValue(), boxAMPM.getValue()));
	}

	private void updateTask() {
		if (boxHR.getValue().equals("HH") && boxMin.getValue().equals("MM") && boxAMPM.getValue().equals("AM/PM")) {
			tempTask.setHourValue(null);
			tempTask.setMinValue(null);
			tempTask.setTimeOfDay(null);
		} else if ((boxHR.getValue().equals("HH") || boxMin.getValue().equals("MM") || boxAMPM.getValue().equals("AM/PM"))) {
			// TODO: Throw error
			System.out.println("TIME ERROR");
		} else {
			tempTask.setHourValue(boxHR.getValue());
			tempTask.setMinValue(boxMin.getValue());
			tempTask.setTimeOfDay(boxAMPM.getValue());
		}

		tempTask.setTitle(txtTitle.getText());
		tempTask.setDescription(txtTask.getText());
		tempTask.setCompleted(chkCompleted.isSelected());
		tempTask.setDate(datePicker.getValue());
	}

	public void loadTasks(ArrayList<TaskData> taskDataList) {
		for (TaskData t : taskDataList) {
			taskList.add(new TodoTask(t.title(), t.description(), t.completed(), t.date(), t.hourValue(), t.minValue()
					, t.timeOfDay()));
		}
	}

	@FXML
	private void displayTime(ActionEvent event) {
		DatePicker date = (DatePicker) event.getSource();
		setTimeVisible(date.getValue() != null);
	}

	@FXML
	private void removeTask() {
		TodoTask task = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(task);
	}

	@FXML
	private void markCompleted() {
		if (table.getSelectionModel().getSelectedItem() != null)
			table.getSelectionModel().getSelectedItem().setCompleted(!table.getSelectionModel().getSelectedItem().isCompleted());
		taskSelected();
	}

	public ArrayList<TaskData> saveData() {
		ArrayList<TaskData> taskDataList = new ArrayList<>();
		for (TodoTask t : taskList) {
			taskDataList.add(new TaskData(t.getTitle(), t.getDescription(), t.isCompleted(), t.getDate(),
			                              t.getHourValue(), t.getMinValue(), t.getTimeOfDay()));
		}
		return taskDataList;
	}
}
