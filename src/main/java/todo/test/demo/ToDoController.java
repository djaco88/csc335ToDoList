package todo.test.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ToDoController {


	@FXML
	private BorderPane borderPane;

	@FXML
	private TabPane tabPane;


	@FXML
		// This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		//TODO: to update style for whole window update CSS Sheet for borderPane
//		borderPane.getStylesheets().clear();
//		borderPane.getStylesheets().add(getClass().getResource("testCSS2.css").toExternalForm());
		addTab();
	}

	public void addTab() {
		FXMLLoader loader = new FXMLLoader();
		loader.setController(new TabTemplateController());
		try {
			Tab newTab = new Tab();
			newTab.setContent(loader.load(getClass().getResource("TabTemplate.fxml")));
			newTab.setText("New Tab");
			newTab.setOnClosed(e -> updateClosable());
			tabPane.getTabs().add(tabPane.getTabs().size(), newTab);
			tabPane.getSelectionModel().selectLast();

			updateClosable();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// TODO: to add more tab colors, add style to switch statement and selection option to ToDo-Test.fxml
	public void changeTabColor(ActionEvent event) {
		MenuItem callingItem = (MenuItem) event.getSource();
		Tab tab = tabPane.getSelectionModel().getSelectedItem();

//		tab.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		switch (callingItem.getId()) {
			case "tabPurple" -> tab.setStyle("-fx-background-color: slateblue; ");
			case "tabSilver" -> tab.setStyle("-fx-background-color: silver; ");
		}
	}

	public void changeTheme(ActionEvent event) {
		MenuItem callingItem = (MenuItem) event.getSource();
		Tab tab = tabPane.getSelectionModel().getSelectedItem();
		AnchorPane ap = (AnchorPane) tab.getContent();
		ap.getStylesheets().clear();

		String style = null;
		switch (callingItem.getId()) {
			case "theme1" -> style = "testCSS.css";
			case "theme2" -> style = "testCSS2.css";
		}
		ap.getStylesheets().add(getClass().getResource(style).toExternalForm());
	}

	private void updateClosable() {
		if (tabPane.getTabs().size() < 2) {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		} else {
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
		}
	}

	public void saveState(ActionEvent event) {
		// TODO: add saving logic
		
	}
}

