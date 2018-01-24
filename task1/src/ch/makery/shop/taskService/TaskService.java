package ch.makery.shop.taskService;

import ch.makery.shop.controller.MainApp;
import ch.makery.shop.model.Task;
import ch.makery.shop.model.TaskListWrapper;
import ch.makery.shop.taskService.ITaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by Wojtek on 07.12.2017.
 */
public class TaskService implements ITaskService {

    static ObservableList<Task> taskData = FXCollections.observableArrayList();

    @Override
    public ObservableList<Task> getTaskList() {
        return taskData;
    }

    public TaskService() {

        refreshList();
        /*File file = getTaskFilePath();
        if (file != null) {
            loadTaskDataFromFile(file);
        }*/
        /*taskData.add(new Task("ZAD 1"," ", 0));
        taskData.add(new Task("ZAD 2"," ", 1));
        taskData.add(new Task("ZAD 3"," ", 2));
        taskData.add(new Task("ZAD 4"," ", 3));*/

    }

    @Override
    public void addAuthor(String name, int ID) {
        for(Task task: taskData){
            if(task.getID()==ID){
                task.setAuthor(name);
                break;
            }

        }
    }

    @Override
    public void refreshList(){

        File file = getTaskFilePath();
        if (file != null) {
            loadTaskDataFromFile(file);
        }
    }

    public File getTaskFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void loadTaskDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TaskListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            TaskListWrapper wrapper = (TaskListWrapper) um.unmarshal(file);

            taskData.clear();
            taskData.addAll(wrapper.getTasks());

            // Save the file path to the registry.
            setTaskFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void setTaskFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

        } else {
            prefs.remove("filePath");
        }
    }
}
