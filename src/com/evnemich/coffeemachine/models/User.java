package com.evnemich.coffeemachine.models;

import java.sql.Connection;

import com.evnemich.coffeemachine.DataBase;

/**
 * User contains all data to work with database
 * 
 * @author evnemich
 *
 */
public class User {

    /**
     * user_id - primary key to access users table
     */
    private final int id;

    /**
     * Indicator is it admin or not
     */
    public final boolean admin;

    /**
     * Connection to database
     */
    private Connection con;

    /**
     * Invalid class constructor
     */
    public User() {
	this.id = 0;
	admin = false;
	// Empty constructor to make useless instance
    }

    /**
     * valid class constructor
     * 
     * @param id
     *            user_id field in users table
     * @param con
     *            connection to database
     * @param admin
     *            indicator is it admin profile
     */
    public User(int id, Connection con, boolean admin) {

	this.admin = admin;
	this.id = id;
	this.con = con;
    }

    /**
     * Getter of user_id field
     * 
     * @return user_id
     */
    public int getId() {

	return id;
    }

    /**
     * Checking is it valid instance of User
     * 
     * @return true/false
     */
    public boolean isValid() {

	if (id != 0)
	    return true;

	return false;
    }

    /**
     * Getter of connection private field
     * 
     * @return connection
     */
    public Connection getConnection() {

	return con;
    }

    /**
     * Add money to the user
     * 
     * @param amount
     *            amount of money to add
     * @return <code>boolean</code> indicator if operation was succeed
     */
    public boolean addMoney(double amount) {

	return DataBase.addMoney(this, amount);
    }

    /**
     * Getting money amount from database
     * 
     * @return amount of money user have
     */
    public double getMoney() {

	return DataBase.getMoney(this);
    }

    /**
     * Paying for products
     * 
     * @param amount
     *            amount of money to pay
     * @return <code>boolean</code> indicator if operation was succeed
     */
    public boolean pay(double amount) {

	if (this.getMoney() < amount)
	    return false;

	return DataBase.pay(this, amount);
    }
}
