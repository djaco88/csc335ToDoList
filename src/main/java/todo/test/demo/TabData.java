package todo.test.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class TabData implements Serializable {
	private final String name;
	private final ArrayList<TaskData> tasks;

	public TabData(String name, ArrayList<TaskData> taskList) {
		this.name = name;
		this.tasks = taskList;
	}

	public String getName() {
		return name;
	}

	public ArrayList<TaskData> getTasks() {
		return tasks;
	}

}
