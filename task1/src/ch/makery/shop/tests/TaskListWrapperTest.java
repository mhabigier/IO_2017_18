package ch.makery.shop.tests;

import ch.makery.shop.model.Task;
import ch.makery.shop.model.TaskListWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wojtek on 20.01.2018.
 */
public class TaskListWrapperTest {
    @Test
    public void getTasks() throws Exception {
        TaskListWrapper taskListWrapper = new TaskListWrapper();
        Task task1 = new Task("Zad1","Kowalski", 3);
        Task task2 = new Task("Zad2","Nowak", 4);
        List<Task> tasks = Arrays.asList(task1,task2);
        taskListWrapper.setTasks(tasks);
        Assert.assertEquals("Zad1", taskListWrapper.getTasks().get(0).getTitle());
    }

    @Test
    public void name() throws Exception {

    }
}