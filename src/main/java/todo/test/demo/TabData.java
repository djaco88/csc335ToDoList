package todo.test.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class TabData implements Serializable {
	private final String name;
	private final ArrayList<TaskData> tasks;
	private final String theme;

	public TabData(String name, ArrayList<TaskData> taskList, String theme) {
		this.name = name;
		this.tasks = taskList;
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public ArrayList<TaskData> getTasks() {
		return tasks;
	}

	public String getTheme() {
		return theme;
	}
}
