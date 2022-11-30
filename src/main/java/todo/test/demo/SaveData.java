package todo.test.demo;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveData implements Serializable {
    private ArrayList<TabData> tabs;

    public SaveData() {
        this.tabs = new ArrayList<>();
    }

    public void addTab(TabData t) {
        tabs.add(t);
    }

    public ArrayList<TabData> getTabs() {
        return tabs;
    }

    public void setTabs(ArrayList<TabData> tabs) {
        this.tabs = tabs;
    }
}
