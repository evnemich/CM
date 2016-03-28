package com.evnemich.coffeemachine.models.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "458945";
    
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
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
