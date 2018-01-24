package ch.makery.shop.tests;

import ch.makery.shop.model.Task;
import ch.makery.shop.model.TaskType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Wojtek on 20.01.2018.
 */
public class TaskTest {
    @Test
    public void getID() throws Exception {
            Task task = new Task("Jan","Kowalski", 3);
            Assert.assertEquals(3, task.getID());
    }


    @Test
    public void getTitle() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        Assert.assertEquals("ZADANIE1", task.getTitle());
    }

    @Test
    public void setTitle() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setTitle("ZAD2");
        Assert.assertEquals("ZAD2", task.getTitle());
    }

    @Test
    public void getAuthor() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        Assert.assertEquals(" Jan Kowalski", task.getAuthor());
    }


    @Test
    public void setAuthor() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setAuthor("Adam Nowak");
        Assert.assertEquals("Adam Nowak", task.getAuthor());
    }

    @Test
    public void getDescription() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setDescription("Opis zadania 1");
        Assert.assertEquals("Opis zadania 1", task.getDescription());

    }

    @Test
    public void setDescription() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setDescription("Opis zadania 1");
        Assert.assertEquals("Opis zadania 1", task.getDescription());
    }

    @Test
    public void getPriority() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setPriority(TaskType.AVOID);
        Assert.assertEquals(TaskType.AVOID, task.getPriority());
    }

    @Test
    public void getSubject2() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setPriority(TaskType.AVOID);
        Assert.assertEquals("NOT IMPORTANT, NOT URGENT", task.getSubject2());
    }

    @Test
    public void setPriority() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setPriority(TaskType.AVOID);
        Assert.assertEquals(TaskType.AVOID, task.getPriority());
    }

    @Test
    public void getDueDate() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setDueDate(5);
        Assert.assertEquals(5, task.getDueDate());
    }


    @Test
    public void setDueDate() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setDueDate(5);
        Assert.assertEquals(5, task.getDueDate());
    }

    @Test
    public void isFinished() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setFinished(false);
        Assert.assertFalse(task.isFinished());
        task.setFinished(true);
        Assert.assertTrue(task.isFinished());
    }

    @Test
    public void isFinished2() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setFinished(true);
        Assert.assertEquals("finished",task.isFinished2());
        task.setFinished(false);
        Assert.assertEquals("in progress",task.isFinished2());
    }

    @Test
    public void setFinished() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        task.setFinished(false);
        Assert.assertFalse(task.isFinished());
    }

}