package com.evnemich.coffeemachine;

import java.sql.SQLException;
import java.util.ArrayList;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.User;

public class CoffeeMachine {

    public static ArrayList<String> drinks = new ArrayList<String>();
    public static ArrayList<String> ingredients = new ArrayList<String>();

    public static User logIn(String login, String password) throws SQLException, ClassNotFoundException {
	DataBase.getData();
	return DataBase.logIn(login, password);
    }

    public static void logOut(User user) throws SQLException {
	DataBase.logOut(user);

    }

    public static User register(String login, String password) throws SQLException, ClassNotFoundException {
	return DataBase.register(login, password);
    }

    public static boolean buy(User user, String product, int amount) throws SQLException {
	Buyable.buy(user, product, amount); // NO MONEY // NO PRODUCT
	return true;
    }

    public static void addNewProduct(User admin, String product, boolean drink) {
	if (!admin.admin)
	    return;
	DataBase.addNewProduct(admin, product, drink);
    }

    public static void addMoney(User user, double amount) {
	user.addMoney(amount);
    }

    public static boolean setPrice(User admin, String product, double price) {
	DataBase.setPrice(admin, product, price); // MAY RETURN FALSE
	return true;
    }

}
