package com.evnemich.coffeemachine;

import java.util.ArrayList;
import java.util.HashMap;

import com.evnemich.coffeemachine.models.User;

/**
 * A class realized standart functions of coffee-machine system
 * 
 * @author evnemich
 *
 */
public class CoffeeMachine {

    /**
     * ArrayList filled by drinks available to buy, empty by default. Filled by
     * calling updatData(user)
     */
    public static ArrayList<String> drinks = new ArrayList<String>();

    /**
     * ArrayList filled by ingredients available to buy, empty by default.
     * Filled by calling updatData(user)
     */
    public static ArrayList<String> ingredients = new ArrayList<String>();

    /**
     * ArrayList filled by products, empty by default. Filled by calling
     * updatData(user)
     */
    public static ArrayList<String> products = new ArrayList<String>();

    /**
     * HashMap filled by prices, empty by default. Keys is product names, values
     * is prices Filled by calling updatData(user)
     */
    public static HashMap<String, Double> price = new HashMap<String, Double>();

    /**
     * A login method to get connection to database.
     * 
     * @param login
     *            username
     * @param password
     *            password
     * @return instance of User (may be invalid if some errors occured)
     * @throws ClassNotFoundException
     *             registering mysql driver may cause problems
     */
    public static User logIn(String login, String password) throws ClassNotFoundException {
	return DataBase.logIn(login, password);
    }

    /**
     * Updates data into static class variables
     * 
     * @param user
     *            instance of User
     */
    public static void updateData(User user) {
	if (user.isValid()) {
	    drinks.clear();
	    ingredients.clear();
	    products.clear();
	    price.clear();
	    DataBase.getData(user);
	}
    }

    /**
     * Closes connection to database
     * 
     * @param user
     *            instance of User
     */
    public static void logOut(User user) {
	DataBase.logOut(user);
    }

    /**
     * Insert new user into database and logging in
     * 
     * @param login
     *            username
     * @param password
     *            password
     * @return instance of User
     * @throws ClassNotFoundException
     *             registering mysql driver may cause problems
     */
    public static User register(String login, String password) throws ClassNotFoundException {
	User user = DataBase.register(login, password);
	DataBase.getData(user);
	return user;
    }

    /**
     * Removing <code>amount</code> products from database if user can pay for
     * it
     * 
     * @param user
     *            instance of User
     * @param product
     *            name of product
     * @param amount
     *            amount of products
     * @return <code>true</code> if operation successful, <code>false</code> if
     *         have not enough product or user have not enough money
     */
    public static boolean buy(User user, String product, int amount) {
	if (amount > DataBase.askAmount(user, product))
	    return false;
	if (user.pay(DataBase.askPrice(user, product) * amount))
	    return DataBase.buyProduct(user, product, amount);
	return false;
    }

    /**
     * Adding new product to database
     * 
     * @param admin
     *            valid user instance with admin rights
     * @param product
     *            name of product
     * @param drink
     *            indicator is it a drink or not
     * @return <code>true</code> if operation successful, <code>false</code>
     *         user is not administrator
     */
    public static boolean addNewProduct(User admin, String product, boolean drink) {
	return DataBase.addNewProduct(admin, product, drink);
    }

    /**
     * Adding <code>amount</code> of products into database
     * 
     * @param admin
     *            User instance with admin rights
     * @param product
     *            name of product
     * @param amount
     *            amount of product
     * @return boolean indicator if operation was succeed
     */
    public static boolean addProduct(User admin, String product, int amount) {
	return DataBase.addProduct(admin, product, amount);
    }

    /**
     * Adding money for user
     * 
     * @param user
     *            valid user instance
     * @param amount
     *            amount of money to add
     * @return boolean indicator if operation was succeed
     */
    public static boolean addMoney(User user, double amount) {
	return user.addMoney(amount);
    }

    /**
     * Set price for <code>product</code>
     * 
     * @param admin
     *            User instance with admin rights
     * @param product
     *            name of product
     * @param price
     *            new price
     * @return boolean indicator if operation was succeed
     */
    public static boolean setPrice(User admin, String product, double price) {
	return DataBase.setPrice(admin, product, price);
    }

    /**
     * Getting amount of available <code>product</code>
     * 
     * @param user
     *            valid user instance
     * @param product
     *            product name
     * @return amount of available products
     */
    public static int getAmount(User user, String product) {
	return DataBase.askAmount(user, product);
    }

    /**
     * Getting price of <code>product</code>
     * 
     * @param user
     *            valid user instance
     * @param product
     *            product name
     * @return price of <code>product</code>
     */
    public static double getPrice(User user, String product) {
	return DataBase.askPrice(user, product);
    }

    /**
     * Removing product from database
     * 
     * @param admin
     *            valid user instance with admin rights
     * @param product
     *            name of product
     * @return boolean indicator if operation was succeed
     */
    public static boolean removeProduct(User admin, String product) {
	return DataBase.removeProduct(admin, product);
    }

}
