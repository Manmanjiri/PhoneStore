package controller;


import model.Datasource;

import javax.sql.DataSource;

/**
 * This class acts as an user session to keep history of his shopping.
 * It is constructed with the Singleton Design Pattern.
 *
 */
public class UserSessionController {

    /**
     * Create an object of UserSessionController
     */
    private static final UserSessionController instance = new UserSessionController();

    /**
     * Make the constructor private so that this class cannot be instantiated
     */
    private UserSessionController() { }

    /**
     * Get the only object available
     * @return      UserSessionController instance.
     */
    public static UserSessionController getInstance() {
        return instance;
    }


    public static String getUserFullName() {
        return  System.getProperty("user.name") ;
    }

    public static int getUser_ID() {
        return 0;
    }
}
