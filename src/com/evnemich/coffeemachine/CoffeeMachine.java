package com.evnemich.coffeemachine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.User;

public class CoffeeMachine {

    public static ArrayList<String> drinks = new ArrayList<String>();
    public static ArrayList<String> ingredients = new ArrayList<String>();
    public static ArrayList<String> products = new ArrayList<String>();
    public static HashMap<String, Double> price = new HashMap<String, Double>();

    public static User logIn(String login, String password) throws ClassNotFoundException, SQLException {
	return DataBase.logIn(login, password);
    }

    public static void updateData(User user) throws SQLException {
	if (user.isValid()) {
	    drinks.clear();
	    ingredients.clear();
	    products.clear();
	    price.clear();
	    DataBase.getData(user);
	}
    }

    public static void logOut(User user) throws SQLException {
	DataBase.logOut(user);
    }

    public static User register(String login, String password) throws SQLException, ClassNotFoundException {
	User user = DataBase.register(login, password);
	DataBase.getData(user);
	return user;
    }

    public static boolean buy(User user, String product, int amount) throws SQLException {
	return Buyable.buy(user, product, amount);
    }

    public static boolean addNewProduct(User admin, String product, boolean drink) {
	return DataBase.addNewProduct(admin, product, drink);
    }

    public static boolean addProduct(User admin, String product, int amount) throws SQLException {
	return DataBase.addProduct(admin, product, amount);
    }

    public static void addMoney(User user, double amount) {
	user.addMoney(amount);
    }

    public static boolean setPrice(User admin, String product, double price) {
	return DataBase.setPrice(admin, product, price);
    }

    public static int getAmount(User user, String product) throws SQLException {
	return Buyable.getAmount(user, product);
    }

    public static double getPrice(User user, String product) {
	return Buyable.getPrice(user, product);
    }

    public static boolean removeProduct(User admin, String product) {
	return DataBase.removeProduct(admin, product);
    }

}
