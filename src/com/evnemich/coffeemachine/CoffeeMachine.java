package com.evnemich.coffeemachine;

import java.sql.SQLException;
import java.util.ArrayList;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.Drink;
import com.evnemich.coffeemachine.models.Ingredient;
import com.evnemich.coffeemachine.models.User;

public class CoffeeMachine {

    public static ArrayList<Drink> drinks = new ArrayList<Drink>();
    public static ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public User logIn(String login, String password) throws SQLException, ClassNotFoundException {
	DataBase.getData();
	return DataBase.logIn(login, password);
    }

    public void logOut(User user) throws SQLException {
	DataBase.logOut(user);

    }

    public User register(String login, String password) throws SQLException, ClassNotFoundException {
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

    public void addNewProduct(User admin, Buyable product, double price) {
	if(!admin.admin) return;
	if (product.getClass().getSimpleName().equals("Drink"))
	    DataBase.addNewProduct(admin, product, true);
	else
	    DataBase.addNewProduct(admin, product, false);
	DataBase.setPrice(admin, product, price);
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
	// *
	String login = "evnemich";
	String password = "p@ssw0r";
	String name = "latte";
	CoffeeMachine cm = new CoffeeMachine();
	Drink latte = new Drink(name);
	/// User user = (User) cm.register(login, password);
	User user = cm.logIn(login, password);
	//user.addMoney(100);
	System.out.println(user.getId());
	System.out.println(user.getMoney());
	//latte.setPrice(user, 10);
	cm.addNewProduct(user, latte, 10);
	System.out.println(latte.getAmount(user));
	latte.buy(user, 1);
	System.out.println(latte.getAmount(user));
    }

}
