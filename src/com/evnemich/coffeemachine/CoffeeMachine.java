package com.evnemich.coffeemachine;

import java.sql.SQLException;
import java.util.ArrayList;

import com.evnemich.coffeemachine.models.*;

public class CoffeeMachine {

    public static ArrayList<Drink> drinks = new ArrayList<Drink>();
    public static ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public User logIn(String login, String password) throws SQLException {
	DataBase.getData();
	return DataBase.logIn(login, password);
    }

    public void logOut(User user) throws SQLException {
	DataBase.logOut(user);

    }

    public User register(String login, String password) throws SQLException {
	return DataBase.register(login, password);
    }

    public boolean refill(User admin, ArrayList<Buyable> products, int amount[]) throws SQLException {
	// Code here
	if (!admin.admin || products.size() != amount.length)
	    return false;
	int i = 0;
	for (Buyable product : products) {
	    DataBase.addProduct(admin, product, amount[i++]); // MAY RETURN
							      // FALSE XXX
	}
	return true;
    }

    boolean buy(User user, ArrayList<Buyable> products, int amount[]) throws SQLException {
	// Code here
	if (products.size() != amount.length)
	    return false;
	int i = 0;
	for (Buyable buyable : products) {
	    buyable.buy(user, amount[i++]); // NO MONEY // NO PRODUCT
	}
	return true;
    }

    public void addNewProduct(User admin, Buyable product) {
	if(!admin.admin) return;
	if (product.getClass().getSimpleName().equals("Drink"))
	    DataBase.addNewProduct(admin, product, true);
	else
	    DataBase.addNewProduct(admin, product, false);
    }

    public void addMoney(User user, double amount) {
	user.addMoney(amount);
    }

    public boolean setPrices(User admin, ArrayList<Buyable> products, double price[]) {
	if (!admin.admin || products.size() != price.length)
	    return false;
	int i = 0;
	for (Buyable product : products) {
	    DataBase.setPrice(admin, product, price[i++]); // MAY RETURN FALSE
							   // XXX
	}
	return true;
    }

    public static void main(String[] args) throws SQLException {
	// *
	String login = "evnemich";
	String password = "p@ssw0rd";
	String name = "latte";
	CoffeeMachine cm = new CoffeeMachine();
	Drink latte = new Drink(name);
	/// User user = (User) cm.register(login, password);
	User user = cm.logIn(login, password);
	System.out.println(user.getId());
	System.out.println(user.getMoney());
	cm.addNewProduct(user, latte);
	System.out.println(latte.getAmount(user));
	latte.add(user, 1);
	System.out.println(latte.getAmount(user));
    }

}
