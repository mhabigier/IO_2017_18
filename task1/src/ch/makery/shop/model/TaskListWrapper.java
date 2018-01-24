package ch.makery.shop.model;
/**
 * Created by Wojtek on 26.03.2017.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of tasks. This is used for saving the
 * list of tasks to XML.
 *
 */
@XmlRootElement(name = "tasks")

public class TaskListWrapper {
    private List<Task> tasks;

    @XmlElement(name = "task")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
