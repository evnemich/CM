package com.evnemich.coffeemachine.models;

import java.sql.SQLException;

import com.evnemich.coffeemachine.DataBase;

public class Buyable {
    
    private String name;
    
    public Buyable(String name){
	this.name = name;
    }
    
    public String getName(){
	return name;
    }

    public double getPrice(User user) {
	return DataBase.askPrice(user, this);
    }

    public boolean setPrice(User admin, double price){
	return DataBase.setPrice(admin, this, price);
    }
    
    public void add(User admin, int amount) throws SQLException{
	DataBase.addProduct(admin, this, amount);
    }
    
    public int getAmount(User user) throws SQLException {
	return DataBase.askAmount(user, this);
    }

    public boolean buy(User user, int amount) throws SQLException {
	if (amount > getAmount(user))
	    return false;
	if (DataBase.buyProduct(user, this, amount))
	    user.pay(this.getPrice(user));
	
	return true;
    }
}
