package builder;
import java.util.Date;

/**
 * Diese Klasse implementiert den Builder für ein komplexes Task-Objekt
 * Hier wurde er als normale Klasse implementiert, da es keine weiteren Konkretisierungen gibt.
 * Der Builder kann auch als Interface mit Konkretisierungen implementiert werden
 */
public class TaskBuilder {

    private final long id;
    private String summary = "";
    private String description = "";
    private boolean done = false;
    private Date dueDate;

    public TaskBuilder(long id) {
        this.id = id;
    }


    public TaskBuilder setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setDone(boolean done) {
        this.done = done;
        return this;
    }

    public TaskBuilder setDueDate(Date dueDate) {
        this.dueDate = new Date(dueDate.getTime());
        return this;
    }
    public Task build() {
        return new Task(id,summary,description,done, dueDate);
    }
}