package ch.makery.shop.tests;

import ch.makery.shop.controller.MainApp;
import ch.makery.shop.model.TaskType;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by Wojtek on 20.01.2018.
 */
public class MainAppTest {
    @Test
    public void getTaskData() throws Exception {
        /*Task task1 = new Task("Zad1","Kowalski", 3);
        Task task2 = new Task("Zad2","Nowak", 4);
        List<Task> tasks = Arrays.asList( task1, task2);*/
        MainApp mainApp = new MainApp();

        Assert.assertEquals("ZAD 1", mainApp.getTaskData().get(0).getTitle());
    }

    @Test
    public void getTypeData() throws Exception {
        MainApp mainApp = new MainApp();

        Assert.assertEquals(TaskType.NONE, mainApp.getTypeData().get(0));
    }


    @Test
    public void getTaskFilePath() throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        Assert.assertNotNull(filePath);
    }

    @Test
    public void setTaskFilePathTest() throws Exception {
        //Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        //String filePath = prefs.get("filePath", null);
        MainApp mainApp = new MainApp();
        File file = mainApp.getTaskFilePath();
        Assert.assertNotNull(file.getPath());


    }

    @Test
    public void saveTaskDataToFileTest() throws Exception {
        MainApp mainApp = new MainApp();
        File file = mainApp.getTaskFilePath();
        //mainApp.saveTaskDataToFile(file);
        Assert.assertTrue(file.length()!=0);
    }

    @Test
    public void loadTaskDataFromFileTest() throws Exception {
        MainApp mainApp = new MainApp();
        //File file = mainApp.getTaskFilePath();
        Assert.assertFalse(mainApp.getTaskData().isEmpty());


    }
}