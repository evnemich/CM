package com.evnemich.models;

public class Buyable {

private int price = 0;
private int amount = 0;
    
    public void Add(int amount){
	this.amount += amount;
    }
    
    public void SetPrice(int price){
	this.price = price;
    }
    
    public int GetPrice(){
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
