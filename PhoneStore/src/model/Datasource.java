package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is acting like a database for this Project.
 * Currently adding a static data
 * It is constructed with the Singleton Design Pattern.
 * @author     Manjiri J
 */
public class Datasource {
    /**
     * Create an object of Datasource
     */
    private static final Datasource instance = new Datasource();
    private static final List<Order>  orders = new ArrayList<>();
    private static List<Product> products ;


    //whoever currently logged in to windows!!
    public static String USERS_NAME;

    public static String getUsersName() {
        return USERS_NAME;
    }

    public static void setUsersName(String usersName) {
        USERS_NAME = System.getProperty("user.name");
    }

    /**
     * Make the constructor private so that this class cannot be instantiated
     */
    private Datasource() {

        init();//not complete
        getAllProducts();//to make a list of available products
    }

    /**
     * Get the only object available
     * @return      Datasource instance.
     */
    public static Datasource getInstance() {

        return instance;
    }

    /**
     * Get the only object available
     * @return      orders instance.
     */
    public static List<Order> getOrders() {

        return orders;
    }

    public static List<Product> getProducts() {
        if(products==null)
        products =  new ArrayList<>();


        return products;
    }
    /**
     * To populate data from excel sheet -- this has some extrernal libraries poi which has tobe included to make
     * this parse and a xlsx file
     */
    /**
     * This data population in table view has not been done...but can be done
     * */
    private void init() {
        try
        {
            File file = new File("C:\\demo\\phonebook.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
        //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:    // this function deprecated
                            System.out.print(cell.getStringCellValue() + "\t\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:    // this function deprecated

                            break;
                        default:
                    }
                }
                System.out.println("");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }




    /**
     * This method will get all the products from the database. now preparing static data
     */
    public List<Product> getAllProducts() {
        if(getProducts().size()==0){

        Product product = new Product();
        product.setId(1);
        product.setName("Samsung Galaxy S9");
        product.setDescription("");
        product.setPrice(1000);
        product.setQuantity(1);
        product.setCategory_name("Smart Phone");
        product.setAvailability("Yes");

        getProducts().add(product);

        Product product1 = new Product();
        product1.setId(2);
        product1.setName("2x Samsung Galaxy S8");
        product1.setDescription("");
        product1.setPrice(1000);
        product1.setQuantity(1);
        product1.setCategory_name("Smart Phone");
        product1.setAvailability("Yes");

        getProducts().add(product1);


        Product product3 = new Product();
        product3.setId(3);
        product3.setName("Motorola Nexus 6");
        product3.setDescription("");
        product3.setPrice(1000);
        product3.setQuantity(1);
        product3.setCategory_name("Smart Phone");
        product3.setAvailability("Yes");

        getProducts().add(product3);

        Product product4 = new Product();
        product4.setId(4);
        product4.setName("Oneplus 9");
        product4.setDescription("");
        product4.setPrice(1000);
        product4.setQuantity(0);
        product4.setCategory_name("Smart Phone");
        product4.setAvailability("No");

        getProducts().add(product4);

        Product product5 = new Product();
        product5.setId(5);
        product5.setName("Apple iPhone 13");
        product5.setDescription("");
        product5.setPrice(1000);
        product5.setQuantity(0);
        product5.setCategory_name("Smart Phone");
        product5.setAvailability("No");

        getProducts().add(product5);

        Product product6 = new Product();
        product6.setId(6);
        product6.setName("Apple iPhone 12");
        product6.setDescription("");
        product6.setPrice(1000);
        product6.setQuantity(1);
        product6.setCategory_name("Smart Phone");
        product6.setAvailability("Yes");

        getProducts().add(product6);

        Product product7 = new Product();
        product7.setId(7);
        product7.setName("Apple iPhone 11");
        product7.setDescription("");
        product7.setPrice(1000);
        product7.setQuantity(1);
        product7.setCategory_name("Smart Phone");
        product7.setAvailability("Yes");

        getProducts().add(product7);

        Product product8 = new Product();
        product8.setId(8);
        product8.setName("-iPhone X");
        product8.setDescription("");
        product8.setPrice(1000);
        product8.setQuantity(0);
        product8.setCategory_name("Smart Phone");
        product8.setAvailability("No");

        getProducts().add(product8);

        Product product9 = new Product();
        product9.setId(9);
        product9.setName("Nokia 3310");
        product9.setDescription("");
        product9.setPrice(1000);
        product9.setQuantity(0);
        product9.setCategory_name("Normal Phone");
        product9.setAvailability("No");;

        getProducts().add(product9);

        }
        return getProducts();


    }

    /**
     * This method gets all the product categories
     */
    public List<Categories> getProductCategories() {
            List<Categories> categories = new ArrayList<>();
            Categories category = new Categories();
            category.setId(1);
            category.setName("Smart Phone");
            categories.add(category);

            Categories category2 = new Categories();
            category2.setId(2);
            category2.setName("Normal Phone");
            categories.add(category2);
            return categories;
    }



    public List<Order> getAllOrders() {

            return getOrders();
    }

    public boolean insertNewOrder(int product_id, String product_name,int user_id, String order_date, String order_status) {
        Order order = new Order();
        order.setId(orders.size()+1);
        order.setProduct_id(product_id);
        order.setUser_id(user_id);
        order.setUser_full_name(System.getProperty("user.name"));//here it will be ordered person
        order.setOrder_date(order_date);
        order.setOrder_status(order_status);
        order.setProduct_name(product_name);
        order.setOrder_price(1000);
        getAllOrders().add(order);
        //updateProductsList(product_id);
        return true;
    }

    private void updateProductsList(int product_id) {
        for (Product product : getProducts()) {
            if(product.getId() == product_id)
            {

               getProducts().remove(product);
               product.setAvailability("No");
               getProducts().add(product);
            }

        }
    }

    public boolean decreaseStock(int product_id) {

        for (Product product : getProducts()) {
            if(product.getId() == product_id)
            {
                getAllProducts().remove(product);
                product.setAvailability("No");
                product.setQuantity(0);
                getProducts().add(product);
                return true;
            }

        }
        return false;

    }
    /**
     * This will return total orders of products
     * **/
    public Integer countUserOrders() {
        return getOrders().size();
    }


    /**
     * This will return total number of products in our case its 9
     * **/
    public Integer countAllProducts() {
        return getProducts().size();
    }

    public Boolean returnOrder(int product_id, String product_name, int user_id, String order_date, String order_status) {
        for (Order returnOrder : getOrders()) {
            if(returnOrder.getProduct_id() == product_id)
            {
                getOrders().remove(returnOrder);

               return true;
            }

        }
        return false;
    }


    public boolean increaseStock(int product_id) {

        for (Product product : getProducts()) {
            if(product.getId() == product_id)
            {
                getAllProducts().remove(product);
                product.setAvailability("Yes");
                product.setQuantity(1);
                getProducts().add(product);
                return true;
            }

        }
        return false;

    }

    public void decreaseOrderList(int product_id) {
    //here to update in list of available products
        for (Order returnOrder : getOrders()) {
            if(returnOrder.getProduct_id() == product_id)
            {
                getOrders().remove(returnOrder);


            }

        }
    }
}















