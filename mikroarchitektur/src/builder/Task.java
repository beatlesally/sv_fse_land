package builder;

import java.util.Date;

/**
 * Diese Klasse stellt das Task-Objekt dar.
 */
public class Task {


    private final long id;
    private String summary = "";
    private String description = "";
    private boolean done = false;
    private Date dueDate;

    public Task(long id) {
        this.id = id;
    }

    public Task(long id, String summary, String description, boolean done,
         Date dueDate) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.done = done;
        this.dueDate = dueDate;

    }

    public long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }


    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public Date getDueDate() {
        return new Date(dueDate.getTime());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", dueDate=" + dueDate +
                '}';
    }
}


