package ch.makery.shop.tests;

import ch.makery.shop.controller.MainApp;
import ch.makery.shop.model.Task;
import ch.makery.shop.model.TaskListWrapper;
import ch.makery.shop.taskService.TaskService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Wojtek on 22.01.2018.
 */
class TaskServiceTest {
    @Test
    void getTaskList() {
        TaskService taskService = new TaskService();
       /* Task task1 = new Task("ZAD 1","STANISLAW", 1);
        Task task2 = new Task("ZAD 2","STEFAN", 2);
        List<Task> tasks = Arrays.asList(task1,task2);*/

        Assert.assertNotNull( taskService.getTaskList().get(0).getTitle());
    }

    @Test
    void addAuthor() {
        TaskService taskService = new TaskService();
        taskService.addAuthor("STEFAN",0);
        Assert.assertEquals("STEFAN",taskService.getTaskList().get(0).getAuthor());

    }

    @Test
    void refreshList() {
        TaskService taskService = new TaskService();
        taskService.refreshList();
        Assert.assertFalse(taskService.getTaskList().isEmpty());
    }

    @Test
    void getTaskFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        Assert.assertNotNull(filePath);
    }

    @Test
    void loadTaskDataFromFile() {
        TaskService taskService = new TaskService();
        Assert.assertFalse(taskService.getTaskList().isEmpty());
    }

    @Test
    void setTaskFilePath() {
        MainApp mainApp = new MainApp();
        File file = mainApp.getTaskFilePath();
        Assert.assertNotNull(file.getPath());
    }

}