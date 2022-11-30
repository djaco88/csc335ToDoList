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

	@FXML
	private void loadFromSave() throws IOException, ClassNotFoundException {
		FileInputStream fi = new FileInputStream("saveData.todo");
		ObjectInputStream oi = new ObjectInputStream(fi);

		SaveData saveData = (SaveData) oi.readObject();

		for (TabData t : saveData.getTabs()) {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(new TabTemplateController());
			Tab newTab = new Tab();
			newTab.setContent(FXMLLoader.load(getClass().getResource("TabTemplate.fxml")));
			newTab.setText(t.getName());
			TabTemplateController temp = (TabTemplateController) newTab.getContent().getUserData();
			temp.loadTasks(t.getTasks());
			newTab.setOnClosed(e -> updateClosable());
			tabPane.getTabs().add(tabPane.getTabs().size(), newTab);
		}
		tabPane.getSelectionModel().selectLast();
		updateClosable();
	}

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

	private void updateClosable() {
		if (tabPane.getTabs().size() < 2) {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		} else {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
		}
	}

	public void closeWindow() throws IOException {
		saveState();
		System.exit(0);
	}

	public void saveState() throws IOException {
		SaveData saveData = new SaveData();
		FileOutputStream f = new FileOutputStream("saveData.todo");
		ObjectOutputStream o = new ObjectOutputStream(f);

		for (Tab tab : tabPane.getTabs()) {
			TabTemplateController temp = (TabTemplateController) tab.getContent().getUserData();
			saveData.addTab(new TabData(tab.getText(), temp.saveData()));
		}

		o.writeObject(saveData);
		o.close();
		f.close();
	}

	public void changeTheme(ActionEvent event) {
		MenuItem callingItem = (MenuItem) event.getSource();
		Tab tab = tabPane.getSelectionModel().getSelectedItem();
		AnchorPane ap = (AnchorPane) tab.getContent();
		ap.getStylesheets().clear();

		String style = null;
		switch (callingItem.getId()) {
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
	}

	public void renameTab() {
		Tab tab = tabPane.getSelectionModel().getSelectedItem();
		TextInputDialog input = new TextInputDialog("Tab Name");
		input.setHeaderText("Enter New Tab Name:");
		input.showAndWait();
		if (input.getResult() != null) tab.setText(input.getEditor().getText());
	}

	public void redirectToREADME() {
		new Application() {
			@Override
			public void start(Stage primaryStage) {

			}
		}.getHostServices().showDocument("https://github.com/djaco88/csc335ToDoList#readme");
	}
}

