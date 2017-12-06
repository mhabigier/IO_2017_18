package ch.makery.shop.view;

import ch.makery.shop.MainApp;
import ch.makery.shop.model.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import ch.makery.shop.model.Product;


public class BookEditDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField publisherField;

    @FXML
    private TextField copiesField;
    @FXML
    private CheckBox statusField;
    @FXML
    private ComboBox<ProductType> comboBox;

    private Stage dialogStage;
    private Product product;
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
        comboBox.setItems(mainApp.getTypeData());
        comboBox.setValue(ProductType.NONE);
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
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;

        titleField.setText(product.getTitle());
        authorField.setText(product.getAuthor());
        publisherField.setText(product.getPublisher());
        copiesField.setText(Integer.toString(product.getCopies()));
        //statusField.setText(Boolean.toString(product.isAvailable()));
        statusField.setSelected(product.isAvailable());
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
            product.setTitle(titleField.getText());
            product.setAuthor(authorField.getText());
            product.setPublisher(publisherField.getText());
            product.setCopies(Integer.parseInt(copiesField.getText()));
            product.setSubject(comboBox.getValue());
            product.setAvailable(statusField.isSelected());
            okClicked = true;
            dialogStage.close();
        }
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
        if (publisherField.getText() == null || publisherField.getText().length() == 0) {
            errorMessage += "No valid publisher!\n";
        }

        if (copiesField.getText() == null || copiesField.getText().length() == 0) {
            errorMessage += "No valid copies!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(copiesField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid copies (must be an integer)!\n";
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