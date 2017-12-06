package ch.makery.shop.view;

import ch.makery.shop.model.Product;
import ch.makery.shop.model.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.shop.MainApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import ch.makery.shop.model.Product;
import ch.makery.shop.model.ProductType;
public class BookOverviewController {
    @FXML
    private TableView<Product> bookTable;
    @FXML
    private TableColumn<Product, String> titleColumn;
    @FXML
    private TableColumn<Product, String> authorColumn;

    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label publisherLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label copiesLabel;
    @FXML
    private Label statusLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the book table with the two columns.
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(
                cellData -> cellData.getValue().authorProperty());

        // Clear book details.
        showBookDetails(null);

        // Listen for selection changes and show the book details when changed.
        bookTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        bookTable.setItems(mainApp.getBookData());
    }

    private void showBookDetails(Product book) {
        if (book != null) {
            // Fill the labels with info from the person object.
            titleLabel.setText(book.getTitle());
            authorLabel.setText(book.getAuthor());
            publisherLabel.setText(book.getPublisher());
            copiesLabel.setText(Integer.toString(book.getCopies()));
            statusLabel.setText(book.isAvailable2());
            subjectLabel.setText(book.getSubject2());
        } else {
            // Person is null, remove all the text.
            titleLabel.setText("");
            authorLabel.setText("");
            publisherLabel.setText("");
            subjectLabel.setText("");
            copiesLabel.setText("");
            statusLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteBook() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bookTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewBook() {
        Product tempBook = new Product();
        boolean okClicked = mainApp.showBookEditDialog(tempBook);
        if (okClicked) {
            mainApp.getBookData().add(tempBook);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditBook() {
        Product selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            boolean okClicked = mainApp.showBookEditDialog(selectedBook);
            if (okClicked) {
                showBookDetails(selectedBook);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select a book in the table.");

            alert.showAndWait();
        }
    }
}