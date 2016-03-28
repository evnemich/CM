package com.evnemich.coffeemachine;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.users.User;

public class DataBase {

    boolean LogIn(User user, String login, String password){
	return true;
    }
    
    boolean Register(User user, String login, String password){
	return true;
    }
    
    void LogOut(User user){
	
    }
    
    int AskAmount(Buyable product){
	return 0;
    }
    
    boolean AddProduct(Buyable product){
	return true;
    }
    
    double AskPrice(Buyable product){
	return 0.;
    }
    
    boolean SetPrice(Buyable product, double price){
	return true;
    }
    
    double GetMoney(User user){
	return 0.;
    }
    
    boolean Pay(User user, double amount){
	return true;
    }
    
    boolean AddMoney(User user, int amount) {
	return true;
    }
    
    
}
