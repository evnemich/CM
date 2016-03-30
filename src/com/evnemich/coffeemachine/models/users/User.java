package com.evnemich.coffeemachine.models.users;

import java.sql.Connection;
import java.sql.SQLException;

import com.evnemich.coffeemachine.DataBase;

public class User {

    private int id;
    private Connection con;
    private double purse;

    public User(int id) {
	// Empty constructor to make useless instance
    }

    public User(int id, Connection con) {
	this.id = id;
	this.con = con;
    }

    public int getId() {
	return id;
    }

    public Connection getConnection() {
	return con;
    }

    public void closeConnection() throws SQLException {
	con.close();
    }

    public boolean AddMoney(double amount) {
	return DataBase.AddMoney(this, amount);
    }

    public double GetMoney() {
	return DataBase.GetMoney(this);
    }

    public boolean Pay(double amount) {
	if (amount > purse)
	    return false;
	return DataBase.Pay(this, amount);

    }
}
