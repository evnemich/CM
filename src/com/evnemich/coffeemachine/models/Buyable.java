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

    public static void add(User admin, String name, int amount) throws SQLException {
	DataBase.addProduct(admin, name, amount);
    }

    public static int getAmount(User user, String name) throws SQLException {
	return DataBase.askAmount(user, name);
    }

    public static boolean buy(User user, String name, int amount) throws SQLException {
	if (amount > Buyable.getAmount(user, name))
	    return false;
	if (DataBase.buyProduct(user, name, amount))
	    user.pay(Buyable.getPrice(user, name));

	return true;
    }
}
