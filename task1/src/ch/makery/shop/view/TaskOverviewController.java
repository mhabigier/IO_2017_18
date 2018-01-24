package ch.makery.shop.view;

import ch.makery.shop.controller.MainApp;
import ch.makery.shop.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

public class TaskOverviewController {
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, String> titleColumn;
    @FXML
    private TableColumn<Task, String> authorColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label priorityLabel;
    @FXML
    private Label dueDateLabel;
    @FXML
    private Label statusLabel;
    @FXML
    DatePicker datePicker;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TaskOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the task table with the two columns.
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(
                cellData -> cellData.getValue().authorProperty());

        // Clear task details.
        showTaskDetails(null);

        // Listen for selection changes and show the task details when changed.
        taskTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTaskDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        taskTable.setItems(mainApp.getTaskData());
    }

    private void showTaskDetails(Task task) {
        if (task != null) {
            // Fill the labels with info from the object.
            titleLabel.setText(task.getTitle());
            authorLabel.setText(task.getAuthor());
            descriptionLabel.setText(task.getDescription());
            dueDateLabel.setText(Integer.toString(task.getDueDate()));
            statusLabel.setText(task.isFinished2());
            priorityLabel.setText(task.getSubject2());
            datePicker.setValue(LocalDate.now().plusDays(task.getDueDate()));
            datePicker.setDisable(true);
            datePicker.setEditable(false);
            datePicker.setStyle("-fx-opacity: 1");
            datePicker.getEditor().setStyle("-fx-opacity: 1");

        } else {
            // Task is null, remove all the text.
            titleLabel.setText("");
            authorLabel.setText("");
            descriptionLabel.setText("");
            priorityLabel.setText("");
            dueDateLabel.setText("");
            statusLabel.setText("");
            datePicker.setValue(null);
            datePicker.setDisable(true);
            datePicker.setEditable(false);
            datePicker.setStyle("-fx-opacity: 1");
            datePicker.getEditor().setStyle("-fx-opacity: 1");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteTask() {
        int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            taskTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please select a task in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new task.
     */
    @FXML
    private void handleNewTask() {
        Task tempTask = new Task();
        boolean okClicked = mainApp.showTaskEditDialog(tempTask);
        if (okClicked) {
            mainApp.getTaskData().add(tempTask);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected task.
     */
    @FXML
    private void handleEditTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = mainApp.showTaskEditDialog(selectedTask);
            if (okClicked) {
                showTaskDetails(selectedTask);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select a task in the table.");

            alert.showAndWait();
        }
    }
}