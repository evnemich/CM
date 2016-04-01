package com.evnemich.coffeemachine.models;

import java.sql.Connection;
import java.sql.SQLException;

import com.evnemich.coffeemachine.DataBase;

public class User {

    private final int id;
    public final boolean admin;
    private Connection con;

    public User(int id) {
	this.id = 0;
	admin = false;
	// Empty constructor to make useless instance
    }

    public User(int id, Connection con, boolean admin) {
	this.admin = admin;
	this.id = id;
	this.con = con;
    }

    public int getId() {
	return id;
    }

    public boolean isValid() {
	if (id != 0)
	    return true;
	return false;
    }

    public Connection getConnection() {
	return con;
    }

    public void closeConnection() throws SQLException {
	con.close();
    }

    public boolean addMoney(double amount) {
	return DataBase.addMoney(this, amount);
    }

    public double getMoney() {
	return DataBase.getMoney(this);
    }

    public boolean pay(double amount) {
	if (this.getMoney() < amount)
	    return false;
	return DataBase.pay(this, amount);

    }
}
