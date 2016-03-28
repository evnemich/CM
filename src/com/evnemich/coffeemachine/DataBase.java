package com.evnemich.coffeemachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.users.User;

public class DataBase {
    
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String user = "root";
	private static final String password = "458945";

	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;

    public static User LogIn(String login, String password) throws SQLException { // MAY RETURN ADMIN XXX

	
	String query = "select";
	
	con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        
	return new User(0);
    }

    public static User Register(String login, String password) {
	return new User(0);
    }

    public static void LogOut(User user) {

    }

    public static int AskAmount(Buyable product) {
	return 0;
    }

    public static boolean AddProduct(Buyable product, int amount) {
	return true;
    }

    public static boolean BuyProduct(Buyable product, int amount) {
	// this.getClass().getSimpleName()
	return true;
    }

    public static double AskPrice(Buyable product) {
	return 0.;
    }

    public static boolean SetPrice(Buyable product, double price) {
	return true;
    }

    public static double GetMoney(int user) {
	return 0.;
    }

    public static boolean Pay(int user, double amount) {
	return true;
    }

    public static boolean AddMoney(int user, double amount) {
	return true;
    }

}
