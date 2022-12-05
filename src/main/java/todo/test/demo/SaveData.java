package todo.test.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveData implements Serializable {
	private final ArrayList<TabData> tabs;

	public SaveData() {
		this.tabs = new ArrayList<>();
	}

	public void addTab(TabData t) {
		tabs.add(t);
	}

	public ArrayList<TabData> getTabs() {
		return tabs;
	}

}
