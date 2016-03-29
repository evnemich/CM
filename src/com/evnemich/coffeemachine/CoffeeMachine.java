package com.evnemich.coffeemachine;

import java.sql.SQLException;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.drinks.*;
import com.evnemich.coffeemachine.models.ingredients.*;
import com.evnemich.coffeemachine.models.users.*;

public class CoffeeMachine {

    Americano americano = new Americano();
    Cappuccino cappuccino = new Cappuccino();
    Latte latte = new Latte();
    Milk milk = new Milk();
    Sugar sugar = new Sugar();

    public Buyable products[] = { americano, cappuccino, latte, milk, sugar };

    public User LogIn(String login, String password) throws SQLException {
	return DataBase.LogIn(login, password);
    }

    public void LogOut(User user) {
	DataBase.LogOut(user);

    }

    public User Register(String login, String password) {
	return DataBase.Register(login, password);
    }

    public boolean Refill(Admin admin, int amount[]) {
	// Code here
	if (products.length != amount.length)
	    return false;
	int i = 0;
	for (Buyable product : this.products) {
	    DataBase.AddProduct(admin, product, amount[i++]); // MAY RETURN FALSE XXX
	}
	return true;
    }

    boolean Buy(User user, int amount[]) {
	// Code here
	if (products.length != amount.length)
	    return false;
	int i = 0;
	for (Buyable buyable : products) {
	    buyable.Buy(user, amount[i++]); // NO MONEY // NO PRODUCT
	}
	return true;
    }

    /*
     * public boolean AddProduct(User user, Buyable product){ if
     * (user.getClass().getSimpleName().equals(Admin.class.getSimpleName()))
     * return false; DataBase.AddNewProduct(product); return true; } //
     */

    public void AddMoney(User user, double amount) {
	user.AddMoney(amount);
    }

    public boolean SetPrices(User user, double price[]) {
	if (user.getClass().getSimpleName() != "Admin")
	    return false;
	int i = 0;
	for (Buyable product : this.products) {
	    DataBase.SetPrice(user, product, price[i++]); // MAY RETURN FALSE XXX
	}
	return true;
    }

    public static void main(String[] args) {

	CoffeeMachine cm = new CoffeeMachine();
	System.out.println(cm.milk.GetAmount(null));
	// cm.milk.Add(10);
	System.out.println(cm.milk.GetAmount(null));
	System.out.println(cm.americano.GetAmount(null));

	cm.milk.GetAmount();

    }

}
