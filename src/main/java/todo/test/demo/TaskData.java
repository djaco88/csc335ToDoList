package todo.test.demo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TaskData implements Serializable {
    
    private String title;
    private String description;
    private boolean completed;
    private LocalTime time;
    private LocalDate date;

    public TaskData(String title, String description, boolean completed, LocalTime time, LocalDate date) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.time = time;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    
}
