package com.evnemich.coffeemachine;

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
    
    public Buyable products[] = {americano, cappuccino, latte, milk, sugar};
    
    User LogIn(String login, String password){
	return DataBase.LogIn(login, password);
    }
    
    void LogOut(User user) {
	DataBase.LogOut(user);
	
    }
    
    User Register(String login, String password){
	return DataBase.Register(login, password);
    }
    
    boolean Refill(Admin admin, int amount[]){
	// Code here
	if (products.length != amount.length)
	    return false;
	int i = 0;
	for (Buyable product : this.products) {
	    DataBase.AddProduct(product, amount[i++]); // MAY RETURN FALSE XXX
	}
	return true;
    }
    
    boolean Buy(User user, int amount[]){
	// Code here
	if (products.length != amount.length)
	    return false;
	int i = 0;
	for (Buyable buyable : products) {
	    buyable.Buy(amount[i++]); // NO MONEY // NO PRODUCT
	}
	return true;
		    
	
    }
    
    void AddMoney(User user, double amount){
	user.AddMoney(amount);	
    }

    public void SetPrices(double price[]){
	int i = 0;
	for (Buyable product : this.products) {
	    DataBase.SetPrice(product, price[i++]); // MAY RETURN FALSE XXX
	}
    }
    
    public static void main(String[] args) {

	CoffeeMachine cm = new CoffeeMachine();
	System.out.println(cm.milk.GetAmount());
	//cm.milk.Add(10);
	System.out.println(cm.milk.GetAmount());
	System.out.println(cm.americano.GetAmount());
	
	cm.milk.GetAmount();
	
	
    }
    
}
