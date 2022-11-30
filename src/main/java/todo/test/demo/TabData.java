package todo.test.demo;

import java.io.Serializable;
import java.util.ArrayList;

public class TabData implements Serializable {
    private String name;
    private ArrayList<TaskData> tasks;

    public TabData(String name, ArrayList<TaskData> taskList) {
        this.name = name;
        this.tasks = taskList;
    }

    public void addTask(TaskData t) {
        tasks.add(t);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TaskData> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskData> tasks) {
        this.tasks = tasks;
    }
}
