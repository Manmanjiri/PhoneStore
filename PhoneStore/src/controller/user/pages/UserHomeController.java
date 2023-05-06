package controller.user.pages;

import controller.UserSessionController;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import model.Datasource;

/**
 * This class handles the users home page.
 * @author     Manjiri J
 */
public class UserHomeController {

    public Label productsCount;
    public Label ordersCount;

    /**
     * This method gets the products count for the user dashboard and sets it to the productsCount label.
     *
     */
    public void getDashboardProdCount() {
            productsCount.setText(String.valueOf(Datasource.getInstance().countAllProducts()));
    }

    /**
     * This method gets the orders count for the user dashboard and sets it to the ordersCount label
     * *
     */
    public void getDashboardOrdersCount() {
        ordersCount.setText(String.valueOf(Datasource.getInstance().countUserOrders()));

    }

}
