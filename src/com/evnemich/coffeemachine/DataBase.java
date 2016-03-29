package com.evnemich.coffeemachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.users.User;

public class DataBase {

    private static final String url = "jdbc:mysql://localhost:3306/coffeemachine";
    private static final String user = "root";
    private static final String password = "458945";

    private static Connection con;
    private static Statement stmt;

    public static User LogIn(String login, String password) throws SQLException { // MAY
										  // RETURN
										  // ADMIN
										  // XXX

	String query = "select user_id FROM coffeemachine.users WHERE login=" + login + " AND password=" + password;

	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password);
	stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	rs.last();
	if (rs.getRow() == 1)
	    return new User(rs.getInt(0));

	return new User(0);
    }

    public static User Register(String login, String password) {
	return new User(0);
    }

    public static void LogOut(User user) {

    }

    public static int AskAmount(User user, Buyable product) {
	return 0;
    }

    public static boolean AddProduct(User user, Buyable product, int amount) {
	return true;
    }

    /*
     * public static boolean AddNewProduct(Buyable product) {
     * 
     * } //
     */

    public static boolean BuyProduct(User user, Buyable product, int amount) {
	// this.getClass().getSimpleName()
	return true;
    }

    public static double AskPrice(User user, Buyable product) {
	return 0.;
    }

    public static boolean SetPrice(User user, Buyable product, double price) {
	return true;
    }

    public static double GetMoney(User user) {
	return 0.;
    }

    public static boolean Pay(User user, double amount) {
	return true;
    }

    public static boolean AddMoney(User user, double amount) {
	return true;
    }

}
