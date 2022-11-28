package todo.test.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class ToDoController {


	@FXML
	private Menu menuChangeTheme;
	@FXML
	private BorderPane borderPane;
	@FXML
	private TabPane tabPane;


	@FXML
		// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		//TODO: to update style for whole window update CSS Sheet for borderPane
		borderPane.getStylesheets().clear();
		borderPane.getStylesheets().add(getClass().getResource("LightTheme.css").toExternalForm());
		addTab();
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

	private void loadFromSave() {
		// TODO: add load logic
	}

	// TODO: to add more tab colors, add style to switch statement and selection option to ToDo-Test.fxml
	public void changeTabColor(ActionEvent event) {
		MenuItem callingItem = (MenuItem) event.getSource();
		Tab tab = tabPane.getSelectionModel().getSelectedItem();

		switch (callingItem.getId()) {
			case "DarkTheme" -> tab.setStyle("-fx-background-color: darkgray; ");
			case "LightTheme" -> tab.setStyle("-fx-background-color: ivory; ");
			case "RedTheme" -> tab.setStyle("-fx-background-color: crimson; ");
			case "OrangeTheme" -> tab.setStyle("-fx-background-color: darkorange; ");
			case "YellowTheme" -> tab.setStyle("-fx-background-color: gold; ");
			case "GreenTheme" -> tab.setStyle("-fx-background-color: green; ");
			case "BlueTheme" -> tab.setStyle("-fx-background-color: lightskyblue; ");
			case "PurpleTheme" -> tab.setStyle("-fx-background-color: mediumpurple; ");
			case "PinkTheme" -> tab.setStyle("-fx-background-color: magenta; ");
		}
	}

	public void saveState() {
		// TODO: Implement save data
		for (Tab tab : tabPane.getTabs()) {
			TabTemplateController temp = (TabTemplateController) tab.getContent().getUserData();
			temp.saveData();
		}
	}

	public void closeWindow() {
		// TODO: add exit saving state logic
		System.exit(0);
	}

	// TODO: implement adding themes dynamically
	private void addThemesDynamically() {
		MenuItem theme3 = new MenuItem("Theme 3");
		theme3.setId("theme3");
		theme3.idProperty().setValue("theme3");
		theme3.setOnAction(this::changeTheme);
		menuChangeTheme.getItems().add(theme3);
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
}

