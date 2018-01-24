package ch.makery.shop.taskService;

import ch.makery.shop.model.Task;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Wojtek on 07.12.2017.
 */
public interface ITaskService {
    public ObservableList<Task> getTaskList();
    public void addAuthor(String name,  int ID);
    public void refreshList();
}
