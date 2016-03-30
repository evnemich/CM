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

    public static User LogIn(String login, String password) throws SQLException { // MAY
										  // RETURN
										  // ADMIN
										  // XXX
	User user;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query = "select user_id FROM coffeemachine.users WHERE login='" + login + "' AND password='" + password
		+ "';";

	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password); // REALIZE
											   // POOL
											   // OF
											   // CONNECTIONS?
											   // XXX
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	rs.next();
	user = new User(rs.getInt(1), con);

	stmt.close();
	rs.close();

	return user;
    }

    public static User Register(String login, String password) throws SQLException {

	User user;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query = "INSERT INTO users (login, password) VALUES ('" + login + "','" + password + "');";

	// try{
	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password); // REALIZE
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);

	query = "SELECT user_id FROM users WHERE login='" + login + "'";
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	stmt.close();
	user = new User(rs.getInt(1), con);
	rs.close();
	return user;
	// }catch(SQLException se){
	// System.out.println(rs.getMetaData().getColumnCount());
	// RS EXCEPTION?
	// }
	// return new User(0);
    }

    public static void LogOut(User user) throws SQLException {
	user.closeConnection();
	// TODO
    }

    public static int AskAmount(User user, Buyable product) throws SQLException {
	// TODO
	int amount = 0;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT amount FROM products WHERE name='" + product.getClass().getSimpleName() + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    stmt.close();
	    rs.close();
	    amount = rs.getInt(1);
	    rs.close();
	} catch (SQLException se) {
	    // So bad
	}
	return amount;
    }

    public static void AddProduct(User user, Buyable product, int amount) throws SQLException {
	// TODO
	Statement stmt;
	String query = "UPDATE products WHERE name='" + product.getClass().getSimpleName() + "' SET amount=amount+"
		+ amount + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeQuery(query).close();
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
    }

    /*
     * public static boolean AddNewProduct(Buyable product) {
     * 
     * } //
     */

    public static boolean BuyProduct(User user, Buyable product, int amount) {
	// this.getClass().getSimpleName()
	// TODO
	Statement stmt;
	String query = "UPDATE products WHERE name='" + product.getClass().getSimpleName() + "' SET amount=amount-"
		+ amount + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeQuery(query).close();
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	    return false;
	}
    }

    public static double AskPrice(User user, Buyable product) {
	// TODO
	double price = 0;
	ResultSet rs;
	Statement stmt;
	String query = "SELECT price FROM products WHERE name='" + product.getClass().getSimpleName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    stmt.close();
	    price = rs.getDouble(1);
	    rs.close();
	} catch (SQLException se) {
	    // So bad
	}
	return price;
    }

    public static boolean SetPrice(User user, Buyable product, double price) {
	// TODO
	Statement stmt;
	String query = "UPDATE products WHERE name='" + product.getClass().getSimpleName() + "' SET price=" + price
		+ ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeQuery(query).close();
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	    return false;
	}
    }

    public static double GetMoney(User user) {
	// TODO
	double purse = 0;
	ResultSet rs;
	Statement stmt;
	String query = "SELECT purse FROM users WHERE user_id=" + user.getId() + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    stmt.close();
	    purse = rs.getDouble(1);
	    rs.close();
	} catch (SQLException se) {
	    // So bad
	}
	return purse;
    }

    public static boolean Pay(User user, double amount) {
	// TODO
	Statement stmt;
	String query = "UPDATE users WHERE user_id=" + user.getId() + " SET purse=purse-" + amount + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeQuery(query).close();
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	}
	return false;
    }

    public static boolean AddMoney(User user, double amount) {
	// TODO
	Statement stmt;
	String query = "UPDATE users SET purse=purse+" + amount + " WHERE user_id=" + user.getId() + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeQuery(query).close();
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	}
	return false;

    }

}
