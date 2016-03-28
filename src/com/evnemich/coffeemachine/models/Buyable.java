package com.evnemich.coffeemachine.models;

import com.evnemich.coffeemachine.models.users.Admin;

public class Buyable {

private double price = 0;
private int amount = 0;
    
    public void Add(int amount){
	this.amount += amount;
    }
    
    public void SetPrice(Admin admin, int price){
	this.price = price;
    }
    
    public double GetPrice(){
	return this.price;
    }
    
    public int GetAmount(){
	return amount;
    }
    
    public boolean Buy(int amount){
	if(amount > this.amount)
	    return false;
	this.amount -= amount;
	return true;
    }
}
