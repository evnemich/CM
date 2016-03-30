package com.evnemich.coffeemachine.models;

import java.sql.Connection;
import java.sql.SQLException;

import com.evnemich.coffeemachine.DataBase;

public class User {

    private int id;
    public final boolean admin;
    private Connection con;
    public User(int id) {
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
	return DataBase.pay(this, amount);

    }
}
