package controller.user.pages;

import app.utils.HelperMethods;
import controller.UserSessionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Datasource;
import model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * This class handles the users products page.
 * @author     Manjiri J
 */
public class UserProductsController {


    @FXML
    public TextField fieldProductsSearch;

    @FXML
    private TableView<Product> tableProductsPage;

    /**
     * This method lists all the product to the view table.
     * It starts a new Task, gets all the products from the database then bind the results to the view.
     *
     */
    @FXML
    public void listProducts() {

        Task<ObservableList<Product>> getAllProductsTask = new Task<ObservableList<Product>>() {
            @Override
            protected ObservableList<Product> call() {
                return FXCollections.observableArrayList(Datasource.getInstance().getAllProducts());
            }
        };

        tableProductsPage.itemsProperty().bind(getAllProductsTask.valueProperty());
        addActionButtonsToTable();
        new Thread(getAllProductsTask).start();

    }

    /**
     * This private method adds the action buttons to the table rows.
     *
     */
    @FXML
    private void addActionButtonsToTable() {
        TableColumn colBtnBuy = new TableColumn("Actions");

        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                return new TableCell<Product, Void>() {

                    private final Button buyButton = new Button("Buy");

                    {
                        buyButton.getStyleClass().add("button");
                        buyButton.getStyleClass().add("xs");
                        buyButton.getStyleClass().add("success");
                        buyButton.setOnAction((ActionEvent event) -> {
                            Product productData = getTableView().getItems().get(getIndex());
                            if (productData.getQuantity() <= 0 || productData.getAvailability()=="No") {
                                HelperMethods.alertBox("You can't buy this product because there is no stock!", "", "No Stock");
                            } else {
                                btnBuyProduct(productData.getId(), productData.getName());
                                System.out.println("Buy Product");
                                System.out.println("product id: " + productData.getId());
                                System.out.println("product name: " + productData.getName());
                            }
                        });
                    }

                    private final Button returnButton = new Button("return");

                    {
                        returnButton.getStyleClass().add("button");
                        returnButton.getStyleClass().add("xs");
                        returnButton.getStyleClass().add("warning");
                        returnButton.setOnAction((ActionEvent event) -> {
                            Product productData = getTableView().getItems().get(getIndex());
                            HelperMethods.alertBox("Do you really wants to return the product?", "", "Retun Order");

                            btnReturnProduct(productData.getId(), productData.getName());
                            System.out.println("returnButton Product");
                            System.out.println("product id: " + productData.getId());
                            System.out.println("product name: " + productData.getName());

                        });
                    }

                    private final HBox buttonsPane = new HBox();

                    {
                        buttonsPane.setSpacing(25);
                        buttonsPane.getChildren().add(buyButton);
                        buttonsPane.getChildren().add(returnButton);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(buttonsPane);
                        }
                    }
                };
            }
        };

        colBtnBuy.setCellFactory(cellFactory);

        tableProductsPage.getColumns().add(colBtnBuy);

    }


    /**
     * This private method handles the buy product functionality.
     * @param product_id        Product id.
     * @param product_name      Product name.
     *
     */
    @FXML
    private void btnReturnProduct(int product_id, String product_name) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You are about to return " + product_name);
        alert.setTitle("Return product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                int user_id = UserSessionController.getUser_ID();
                String order_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String order_status = "Received";

                Task<Boolean> returnProductTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() {
                        return Datasource.getInstance().returnOrder(product_id,product_name, user_id, order_date, order_status);
                    }
                };

                returnProductTask.setOnSucceeded(e -> {
                    if (returnProductTask.valueProperty().get()) {
                        Datasource.getInstance().decreaseOrderList(product_id);
                        Datasource.getInstance().increaseStock(product_id);
                        System.out.println("Order Returned!");
                    }
                });

                new Thread(returnProductTask).start();
                System.out.println(product_id);
            }
        }
        tableProductsPage.refresh();
    }

    @FXML
    private void btnBuyProduct(int product_id, String product_name) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("You are about to buy " + product_name);
        alert.setTitle("Buy product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                int user_id = UserSessionController.getUser_ID();
                String order_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String order_status = "Booked";

                Task<Boolean> addProductTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() {
                        return Datasource.getInstance().insertNewOrder(product_id,product_name, user_id, order_date, order_status);
                    }
                };

                addProductTask.setOnSucceeded(e -> {
                    if (addProductTask.valueProperty().get()) {
                        Datasource.getInstance().decreaseStock(product_id);
                        System.out.println("Order placed!");
                    }
                });

                new Thread(addProductTask).start();
                System.out.println(product_id);
                tableProductsPage.refresh();
            }
        }


    }

}