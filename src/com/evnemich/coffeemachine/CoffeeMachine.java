package com.evnemich.coffeemachine;

import com.evnemich.models.Buyable;
import com.evnemich.models.drinks.*;
import com.evnemich.models.ingredients.*;
import com.evnemich.models.users.*;

public class CoffeeMachine {
    
    static Americano americano = new Americano();
    static Cappuccino cappuccino;
    static Latte latte;
    static Milk milk = new Milk();
    static Sugar sugar;
    
    User LogIn(String login, String password){
	// Code here
	return new User();
    }
    
    void LogOut(User user) {
	// Code here
	
    }
    
    User Register(String login, String password){
	// Code here
	return new User();
    }
    
    void Refill(Admin admin, Drink drinks[], Ingredient ingredients[]){
	// Code here
	
    }
    
    boolean Buy(User user, Drink products[], int amount[]){
	// Code here
	if (products.length != amount.length)
	    return false;
	for (int i = 0; i < products.length; i++) {
	    products[i].Buy(amount[i]);;
	    //user.Pay(products[i].cost);
	}
	return true;
		    
	
    }
    
    void AddMoney(User user, double amount){
	user.AddMoney(amount);	
    }

    public static void main(String[] args) {

	System.out.println(CoffeeMachine.milk.GetAmount());
	CoffeeMachine.milk.Add(10);
	System.out.println(CoffeeMachine.milk.GetAmount());
	System.out.println(CoffeeMachine.americano.GetAmount());
	
	
    }
    
}
