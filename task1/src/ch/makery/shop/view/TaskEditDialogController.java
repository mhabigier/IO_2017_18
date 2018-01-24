package ch.makery.shop.view;

import ch.makery.shop.controller.MainApp;
import ch.makery.shop.taskService.TaskService;
import ch.makery.shop.model.TaskType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ch.makery.shop.model.Task;


public class TaskEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField descriptionField;

    @FXML
    private TextField dueDateField;
    @FXML
    private CheckBox statusField;
    @FXML
    private ComboBox<TaskType> taskTypeComboBox;

    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

    private MainApp mainApp;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        taskTypeComboBox.setItems(mainApp.getTypeData());
        taskTypeComboBox.setValue(TaskType.NONE);
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param task
     */
    public void setTask(Task task) {
        this.task = task;

        titleField.setText(task.getTitle());
        authorField.setText(task.getAuthor());
        descriptionField.setText(task.getDescription());
        dueDateField.setText(Integer.toString(task.getDueDate()));
        //statusField.setText(Boolean.toString(task.isFinished()));
        statusField.setSelected(task.isFinished());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            task.setTitle(titleField.getText());
            task.setAuthor(authorField.getText());
            task.setDescription(descriptionField.getText());
            task.setDueDate(Integer.parseInt(dueDateField.getText()));
            task.setPriority(taskTypeComboBox.getValue());
            task.setFinished(statusField.isSelected());
            okClicked = true;
            dialogStage.close();
        }

       //TaskService taskViewer= new TaskService();
       // System.out.println(taskViewer.getTaskList().get(0).getTitle());
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0) {
            errorMessage += "No valid title!\n";
        }
        if (authorField.getText() == null || authorField.getText().length() == 0) {
            errorMessage += "No valid author!\n";
        }
        if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }

        if (dueDateField.getText() == null || dueDateField.getText().length() == 0) {
            errorMessage += "No valid due date!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(dueDateField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid due date (must be an integer)!\n";
            }
        }

        if (statusField.getText() == null || statusField.getText().length() == 0) {
            errorMessage += "No valid status!\n";
        }



        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}