package ch.makery.shop.model;

        import javafx.beans.property.*;

/**
 * Created by Wojtek on 20.03.2017.
 */
public class Task {
    private final IntegerProperty ID;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty description;
    private TaskType priority;
    private final IntegerProperty dueDate;
    private boolean isFinished;

    public Task() {
        this(null,null, 0);
    }

    public Task(String title, String author, int ID ) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.ID = new SimpleIntegerProperty(ID);
        this.description = new SimpleStringProperty("");
        isFinished =true;
        this.dueDate = new SimpleIntegerProperty(1);
        this.priority = TaskType.NONE;

    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public TaskType getPriority() {

        return priority;
    }
    public String getSubject2() {
        String subj;
        subj = "" + priority;
        return subj;
    }

    public void setPriority(TaskType sub){
        this.priority = sub;
    }
    public int getDueDate() {
        return dueDate.get();
    }

    public IntegerProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate.set(dueDate);
    }

    public boolean isFinished() {
        return isFinished;
    }
    public String isFinished2() {
       if (isFinished) return "finished";
       else return "in progress";
    }
    public void setFinished(boolean finished) {
        isFinished = finished;
    }


}
