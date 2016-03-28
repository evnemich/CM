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
    
    Buyable products[] = {americano, cappuccino, latte, milk, sugar};
    
    User LogIn(String login, String password){
	//TODO Code here 
	return new User();
    }
    
    void LogOut(User user) {
	//TODO Code here
	
    }
    
    User Register(String login, String password){
	//TODO Code here
	return new User();
    }
    
    boolean Refill(Admin admin, int amount[]){
	// Code here
	if (products.length != amount.length)
	    return false;
	int i = 0;
	for (Buyable buyable : products) {
	    buyable.Add(amount[i++]);
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

    public static void main(String[] args) {

	CoffeeMachine cm = new CoffeeMachine();
	System.out.println(cm.milk.GetAmount());
	cm.milk.Add(10);
	System.out.println(cm.milk.GetAmount());
	System.out.println(cm.americano.GetAmount());
	
	
    }
    
}
