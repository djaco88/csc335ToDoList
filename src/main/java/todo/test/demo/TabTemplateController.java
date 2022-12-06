package todo.test.demo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.util.ArrayList;

/**
 * Controller Class for TabTemplate.fxml
 */
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
	private String theme;

	/**
	 * This method will automatically be called when tab is created.
	 */
	@FXML
	void initialize() {
		setupTable();

		setupDateTime();
		theme = "LightTheme";
	}

	/**
	 * Initialize task table.
	 */
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

	/**
	 * Initializes date and time selectors
	 */
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

	/**
	 * Updates task title, description, date, time, and completed checkbox when task is selected.
	 */
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

	/**
	 * Allows users to set task time.
	 *
	 * @param visible true if user is allowed to set time.
	 */
	private void setTimeVisible(boolean visible) {
		boxMin.setVisible(visible);
		boxHR.setVisible(visible);
		boxAMPM.setVisible(visible);
	}

	/**
	 * Clears display for new task entries.
	 */
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

	/**
	 * Called when submit button is pushed.
	 * Saves task data to table, or creates new task.
	 */
	@FXML
	private void submitTask() {
		if (tempTask == null) newTask();
		else updateTask();

		clearPrompts();
		table.getSelectionModel().clearSelection();
	}

	/**
	 * Stores user submitted information to task list as new task.
	 * If user only inputs 1 of the 3 time selections, dialog box will appear.
	 * (HH - MM - AM/PM) must all be set to continue.
	 */
	private void newTask() {
		if (boxHR.getValue().equals("HH") && boxMin.getValue().equals("MM") && boxAMPM.getValue().equals("AM/PM"))
			taskList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(),
			                          datePicker.getValue()));
		else if ((boxHR.getValue().equals("HH") || boxMin.getValue().equals("MM") || boxAMPM.getValue().equals("AM/PM"))) {
			dialogBoxOps();
		}
		taskList.add(new TodoTask(txtTitle.getText(), txtTask.getText(), chkCompleted.isSelected(),
		                          datePicker.getValue(), boxHR.getValue(), boxMin.getValue(), boxAMPM.getValue()));
	}

	/**
	 * If task already exists in taskList, task is updated with user input.
	 * If user only inputs 1 of the 3 time selections, dialog box will appear.
	 * (HH - MM - AM/PM) must all be set to continue.
	 */
	private void updateTask() {
		if (boxHR.getValue().equals("HH") && boxMin.getValue().equals("MM") && boxAMPM.getValue().equals("AM/PM")) {
			tempTask.setHourValue(null);
			tempTask.setMinValue(null);
			tempTask.setTimeOfDay(null);
		} else if ((boxHR.getValue().equals("HH") || boxMin.getValue().equals("MM") || boxAMPM.getValue().equals("AM/PM"))) {
			dialogBoxOps();
		}
		tempTask.setHourValue(boxHR.getValue());
		tempTask.setMinValue(boxMin.getValue());
		tempTask.setTimeOfDay(boxAMPM.getValue());

		tempTask.setTitle(txtTitle.getText());
		tempTask.setDescription(txtTask.getText());
		tempTask.setCompleted(chkCompleted.isSelected());
		tempTask.setDate(datePicker.getValue());
	}

	/**
	 * Choice dialog displayed when user doesn't set all three time values.
	 */
	private void dialogBoxOps() {
		String[] hr = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] min = {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
		String[] amPM = {"AM", "PM"};
		ChoiceDialog<String> hours = new ChoiceDialog<>(hr[0], hr);
		ChoiceDialog<String> minutes = new ChoiceDialog<>(min[0], min);
		ChoiceDialog<String> pmAM = new ChoiceDialog<>(amPM[0], amPM);
		if (boxHR.getValue().equals("HH")) {
			hours.setHeaderText("Must enter number for HH:");
			hours.showAndWait();
			boxHR.setValue(hours.getSelectedItem());
		}
		if (boxMin.getValue().equals("MM")) {
			minutes.setHeaderText("Must enter number for MM:");
			minutes.showAndWait();
			boxMin.setValue(minutes.getSelectedItem());
		}
		if (boxAMPM.getValue().equals("AM/PM")) {
			pmAM.setHeaderText("Must enter number for AM/PM:");
			pmAM.showAndWait();
			boxAMPM.setValue(pmAM.getSelectedItem());
		}
	}

	/**
	 * Updates taskList with taskList from save state.
	 *
	 * @param taskDataList from save file / external source.
	 */
	public void loadTasks(ArrayList<TaskData> taskDataList) {
		for (TaskData t : taskDataList) {
			taskList.add(new TodoTask(t.title(), t.description(), t.completed(), t.date(), t.hourValue(), t.minValue()
					, t.timeOfDay()));
		}
	}

	/**
	 * Checks if date is selected.
	 * If selected, user is allowed to input time.
	 *
	 * @param event calling MenuItem ActionEvent
	 */
	@FXML
	private void displayTime(ActionEvent event) {
		DatePicker date = (DatePicker) event.getSource();
		setTimeVisible(date.getValue() != null);
	}

	/**
	 * removes selected task from taskList
	 */
	@FXML
	private void removeTask() {
		TodoTask task = table.getSelectionModel().getSelectedItem();
		table.getItems().remove(task);
	}

	/**
	 * Marks selected task complete.
	 */
	@FXML
	private void markCompleted() {
		if (table.getSelectionModel().getSelectedItem() != null)
			table.getSelectionModel().getSelectedItem().setCompleted(!table.getSelectionModel().getSelectedItem().isCompleted());
		taskSelected();
	}

	/**
	 * Compiles all tab tasks into ArrayList for saving.
	 *
	 * @return {@code ArrayList<TaskData>}
	 */
	public ArrayList<TaskData> saveData() {
		ArrayList<TaskData> taskDataList = new ArrayList<>();
		for (TodoTask t : taskList) {
			taskDataList.add(new TaskData(t.getTitle(), t.getDescription(), t.isCompleted(), t.getDate(),
			                              t.getHourValue(), t.getMinValue(), t.getTimeOfDay()));
		}
		return taskDataList;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
