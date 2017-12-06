package ch.makery.shop;

import java.io.IOException;

import ch.makery.shop.model.Product;
import ch.makery.shop.model.ProductType;
import ch.makery.shop.view.BookEditDialogController;
import ch.makery.shop.view.BookOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Product> bookData = FXCollections.observableArrayList();
    private ObservableList<ProductType> choiceBox = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LibraryApp");

        initRootLayout();

        showBookOverview();



    }

    public MainApp() {
        bookData.add(new Product("Lalka","Bolesław Prus"));
        bookData.add(new Product("Wiedźmin","Andrzej Sabkowski"));
        bookData.add(new Product("Mroczna Wieża","Stephen King"));
        bookData.add(new Product("Norweski dziennik Tom 1","Andrzej Pilipiuk"));

        choiceBox.addAll(ProductType.NONE, ProductType.FANTASY, ProductType.HISTORICAL, ProductType.HORROR);
        choiceBox.addAll(ProductType.HUMOUR, ProductType.ACTION, ProductType.NOVEL, ProductType.SCIENCE);

    }
    public ObservableList<Product> getBookData() {
        return bookData;
    }
    public ObservableList<ProductType> getTypeData() {
        return choiceBox;
    }
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showBookOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookOverview.fxml"));
            AnchorPane bookOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(bookOverview);

            // Give the controller access to the main app.
            BookOverviewController controller = loader.getController();
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
     * @param  book book object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showBookEditDialog(Product book) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(book);
            controller.setMainApp(this);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}