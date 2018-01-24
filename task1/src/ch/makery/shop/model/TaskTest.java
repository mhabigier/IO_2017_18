package ch.makery.shop.model;


import org.junit.Assert;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Wojtek on 20.01.2018.
 */
class TaskTest {
    @Test
    void getID() {
        Task task = new Task("Jan","Kowalski", 3);
        Assert.assertEquals(3, task.getID());
    }

    @Test
    public void IDProperty() throws Exception {

    }

    @Test
    public void getTitle() throws Exception {
        Task task = new Task("ZADANIE1"," Jan Kowalski", 3);
        Assert.assertEquals("ZADANIE1", task.getTitle());
    }

    @Test
    public void titleProperty() throws Exception {

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
    public void authorProperty() throws Exception {

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
    public void descriptionProperty() throws Exception {

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

    }

    @Test
    public void dueDateProperty() throws Exception {

    }

    @Test
    public void setDueDate() throws Exception {

    }

    @Test
    public void isFinished() throws Exception {

    }

    @Test
    public void isFinished2() throws Exception {

    }

    @Test
    public void setFinished() throws Exception {

    }

}