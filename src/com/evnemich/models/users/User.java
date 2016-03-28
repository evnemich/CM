package com.evnemich.models.users;

public class User {
    private double purse;
    
    public void AddMoney(double amount){
	purse += amount;
    }
    
    public double GetMoney() {
	return purse;
    }
    
    public boolean Pay(double amount){
	if (amount > purse)
	    return false;
	purse -= amount;
	return true;
	    
    }
}
