package todo.test.demo;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;

/**
 * Controller class for Todo-Test.fxml
 */
public class ToDoController {

	@FXML
	private BorderPane borderPane;
	@FXML
	private TabPane tabPane;

	@FXML
	void initialize() throws ClassNotFoundException {
		borderPane.getStylesheets().clear();
		borderPane.getStylesheets().add(getClass().getResource("LightTheme.css").toExternalForm());

		try {
			loadFromSave();
		} catch (IOException e) {
			addTab();
		}
	}

	/**
	 * Loads saved data file and updates display.
	 *
	 * @throws IOException            If file does not exist.
	 * @throws ClassNotFoundException if file does not contain SaveData
	 */
	@FXML
	private void loadFromSave() throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream("saveData.todo");
		ObjectInputStream oi = new ObjectInputStream(fi);

		SaveData saveData = (SaveData) oi.readObject();

		if (saveData != null && saveData.getTabs() != null) {
			tabPane.getTabs().clear();
			for (TabData t : saveData.getTabs()) {
				FXMLLoader loader = new FXMLLoader();
				loader.setController(new TabTemplateController());
				Tab newTab = new Tab();
				newTab.setContent(FXMLLoader.load(getClass().getResource("TabTemplate.fxml")));
				newTab.setText(t.getName());
				TabTemplateController temp = (TabTemplateController) newTab.getContent().getUserData();
				temp.loadTasks(t.getTasks());
				newTab.setOnClosed(e -> updateClosable());
				changeTheme(t.getTheme(), newTab, (AnchorPane) newTab.getContent());
				tabPane.getTabs().add(tabPane.getTabs().size(), newTab);
			}
		} else addTab();
		tabPane.getSelectionModel().selectLast();
		updateClosable();
	}

	/**
	 * Adds new tab to tab pane
	 */
	public void addTab() {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(new TabTemplateController());
		try {
			Tab newTab = new Tab();
			newTab.setContent(FXMLLoader.load(getClass().getResource("TabTemplate.fxml")));
			newTab.setText("New Tab");
			newTab.setOnClosed(e -> updateClosable());
			tabPane.getTabs().add(tabPane.getTabs().size(), newTab);
			tabPane.getSelectionModel().selectLast();

			updateClosable();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Restricts user from closing tab if only 1 tab open.
	 */
	private void updateClosable() {
		if (tabPane.getTabs().size() < 2) {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		} else {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
		}
	}

	/**
	 * Saves the current state and closes application.
	 *
	 * @throws IOException
	 */
	public void closeWindow() throws IOException {
		saveState();
		System.exit(0);
	}

	/**
	 * Writes application state to save file.
	 *
	 * @throws IOException
	 */
	public void saveState() throws IOException {
		SaveData saveData = new SaveData();
		FileOutputStream f = new FileOutputStream("saveData.todo");
		ObjectOutputStream o = new ObjectOutputStream(f);

		for (Tab tab : tabPane.getTabs()) {
			TabTemplateController temp = (TabTemplateController) tab.getContent().getUserData();
			saveData.addTab(new TabData(tab.getText(), temp.saveData(), temp.getTheme()));
		}

		o.writeObject(saveData);
		o.close();
		f.close();
	}

	/**
	 * Updates tab theme from menu-bar action event.
	 *
	 * @param event calling MenuItem
	 */
	public void changeTheme(ActionEvent event) {
		MenuItem callingItem = (MenuItem) event.getSource();
		Tab tab = tabPane.getSelectionModel().getSelectedItem();
		AnchorPane ap = (AnchorPane) tab.getContent();
		ap.getStylesheets().clear();

		changeTheme(callingItem.getId(), tab, ap);
	}

	/**
	 * Updates tab theme, and saves new theme to tab's controller.
	 *
	 * @param theme Theme to be set
	 * @param tab   Tab to be set
	 * @param ap    AnchorPane of tab to be set
	 */
	private void changeTheme(String theme, Tab tab, AnchorPane ap) {
		String style = null;
		switch (theme) {
			case "DarkTheme" -> {
				style = "DarkTheme.css";
				tab.setStyle("-fx-background-color: darkgray; ");
			}
			case "LightTheme" -> {
				style = "LightTheme.css";
				tab.setStyle("-fx-background-color: white; ");
			}
			case "RedTheme" -> {
				style = "RedTheme.css";
				tab.setStyle("-fx-background-color: red; ");
			}
			case "OrangeTheme" -> {
				style = "OrangeTheme.css";
				tab.setStyle("-fx-background-color: orange; ");
			}
			case "YellowTheme" -> {
				style = "YellowTheme.css";
				tab.setStyle("-fx-background-color: gold; ");
			}
			case "GreenTheme" -> {
				style = "GreenTheme.css";
				tab.setStyle("-fx-background-color: green; ");
			}
			case "BlueTheme" -> {
				style = "BlueTheme.css";
				tab.setStyle("-fx-background-color: lightskyblue; ");
			}
			case "PurpleTheme" -> {
				style = "PurpleTheme.css";
				tab.setStyle("-fx-background-color: mediumpurple; ");
			}
			case "PinkTheme" -> {
				style = "PinkTheme.css";
				tab.setStyle("-fx-background-color: pink; ");
			}
		}
		ap.getStylesheets().add(getClass().getResource(style).toExternalForm());
		TabTemplateController tabController = (TabTemplateController) tab.getContent().getUserData();
		tabController.setTheme(theme);
	}

	/**
	 * Displays input dialog to change current tab name.
	 */
	public void renameTab() {
		Tab tab = tabPane.getSelectionModel().getSelectedItem();
		TextInputDialog input = new TextInputDialog("Tab Name");
		input.setHeaderText("Enter New Tab Name:");
		input.showAndWait();
		if (input.getResult() != null) tab.setText(input.getEditor().getText());
	}

	/**
	 * Opens browser and directs to group gitHub page.
	 */
	public void redirectToREADME() {
		new Application() {
			@Override
			public void start(Stage primaryStage) {

			}
		}.getHostServices().showDocument("https://github.com/djaco88/csc335ToDoList#readme");
	}
}

