package com.evnemich.coffeemachine.models;

import java.sql.SQLException;

import com.evnemich.coffeemachine.DataBase;

public class Buyable {

    public static double getPrice(User user, String name) {
	return DataBase.askPrice(user, name);
    }

    public static boolean setPrice(User admin, String name, double price) {
	return DataBase.setPrice(admin, name, price);
    }

    public static boolean add(User admin, String name, int amount) throws SQLException {
	return DataBase.addProduct(admin, name, amount);
    }

    public static int getAmount(User user, String name) throws SQLException {
	return DataBase.askAmount(user, name);
    }

    public static boolean buy(User user, String name, int amount) throws SQLException {
	if (amount > Buyable.getAmount(user, name))
	    return false;
	if (user.pay(Buyable.getPrice(user, name)))
	    return DataBase.buyProduct(user, name, amount);
	return false;
    }
}
