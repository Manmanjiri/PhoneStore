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
import model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;


/**
 * This class handles the users orders page.
 * @author     Manjiri J
 */
public class UserOrdersController {
    public TableView tableOrdersPage;

    /**
     * This method lists all the orders to the view table.
     * It starts a new Task, gets all the products from the database then bind the results to the view.
     *
     */
    @FXML
    public void listOrders() {

        Task<ObservableList<Order>> getAllOrdersTask = new Task<ObservableList<Order>>() {
            @Override
            protected ObservableList<Order> call() {
                return FXCollections.observableArrayList(Datasource.getInstance().getOrders());
            }
        };

        tableOrdersPage.itemsProperty().bind(getAllOrdersTask.valueProperty());
        addActionButtonsToTable();
        new Thread(getAllOrdersTask).start();

    }

    private void addActionButtonsToTable() {
        TableColumn colBtnBuy = new TableColumn("Actions");

        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory = new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                return new TableCell<Order, Void>() {
                    private final Button returnButton = new Button("return");

                    {
                        returnButton.getStyleClass().add("button");
                        returnButton.getStyleClass().add("xs");
                        returnButton.getStyleClass().add("warning");
                        returnButton.setOnAction((ActionEvent event) -> {
                        Order orderData = getTableView().getItems().get(getIndex());
                        HelperMethods.alertBox("Do you really wants to return the product?", "", "Retun Order");

                        btnReturnProduct(orderData.getProduct_id(), orderData.getProduct_name());
                        System.out.println("returnButton Product");
                        System.out.println("product id: " + orderData.getId());
                        System.out.println("product name: " + orderData.getProduct_name());

                    });
                    }

                    private final HBox buttonsPane = new HBox();

                    {
                        buttonsPane.setSpacing(25);
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

        tableOrdersPage.getColumns().add(colBtnBuy);

    }


    private void btnReturnProduct(int order_id, String product_name) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("You are about to return " + product_name);
        alert.setTitle("Return product?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                int user_id = UserSessionController.getUser_ID();
                String order_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String order_status = "Returned";

                Task<Boolean> returnOrderTask = new Task<Boolean>() {
                    @Override
                    protected Boolean call() {
                        return Datasource.getInstance().returnOrder(order_id,product_name, user_id, order_date, order_status);
                    }
                };

                returnOrderTask.setOnSucceeded(e -> {
                    if (returnOrderTask.valueProperty().get()) {

                        Datasource.getInstance().increaseStock(order_id);
                        System.out.println("Order Returned!");
                    }
                });

                new Thread(returnOrderTask).start();
                System.out.println(order_id);
                refresh();
            }
        }

    }
    
     public void refresh() {
        Task<ObservableList<Order>> getAllOrdersTask = new Task<ObservableList<Order>>() {
            @Override
            protected ObservableList<Order> call() {
                return FXCollections.observableArrayList(Datasource.getInstance().getOrders());
            }
        };
        getAllOrdersTask.run();
        tableOrdersPage.refresh();
    }

    public void btnOrdersSearchOnAction(ActionEvent actionEvent) {
        // TODO
        //  Add orders search functionality.
        System.out.println("TODO: Add orders search functionality.");
    }
}
