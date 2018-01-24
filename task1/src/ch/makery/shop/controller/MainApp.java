package ch.makery.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import ch.makery.shop.taskService.TaskService;
import ch.makery.shop.model.TaskListWrapper;

import ch.makery.shop.model.Task;
import ch.makery.shop.model.TaskType;
import ch.makery.shop.view.RootLayoutController;
import ch.makery.shop.view.TaskEditDialogController;
import ch.makery.shop.view.TaskOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Task> taskData;// = FXCollections.observableArrayList();
    private ObservableList<TaskType> taskTypeChoiceBox = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TaskApp");

        initRootLayout();

        showTaskOverview();

    }

    public MainApp() {
        TaskService taskViewer = new TaskService();
        taskData = taskViewer.getTaskList();
        taskData.add(new Task("ZAD 1"," ", 1 ));
        taskData.add(new Task("ZAD 2"," ", 2));
        taskData.add(new Task("ZAD 3"," ", 3));
        taskData.add(new Task("ZAD 4"," ", 4));

        taskTypeChoiceBox.addAll(TaskType.NONE, TaskType.DO_NOW, TaskType.PLAN_NEXT, TaskType.MANAGE, TaskType.AVOID);

    }
    public ObservableList<Task> getTaskData() {
        return taskData;
    }

    public ObservableList<TaskType> getTypeData() {
        return taskTypeChoiceBox;
    }
    /**
     * Initializes the root layout.
     */
    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("../view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getTaskFilePath();
        if (file != null) {
            loadTaskDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showTaskOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/TaskOverview.fxml"));
            AnchorPane bookOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(bookOverview);

            // Give the controller access to the main app.
            TaskOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);

    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param  task task object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showTaskEditDialog(Task task) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/TaskEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TaskEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTask(task);
            controller.setMainApp(this);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the task file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getTaskFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setTaskFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("TaskApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("TaskApp");
        }
    }

    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public void loadTaskDataFromFile(File file) {
        try {
            System.out.println("Loading Data....");
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
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public void saveTaskDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(TaskListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            TaskListWrapper wrapper = new TaskListWrapper();
            wrapper.setTasks(taskData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setTaskFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
}