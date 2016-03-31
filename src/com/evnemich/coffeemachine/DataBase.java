package com.evnemich.coffeemachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.evnemich.coffeemachine.models.Buyable;
import com.evnemich.coffeemachine.models.Drink;
import com.evnemich.coffeemachine.models.Ingredient;
import com.evnemich.coffeemachine.models.User;

public class DataBase {

    private static final String url = "jdbc:mysql://localhost:3306/coffeemachine?useSSL=false";
    private static final String user = "root";
    private static final String password = "458945";

    public static User logIn(String login, String password) throws SQLException, ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT user_id, admin FROM users WHERE login='" + login + "' AND password='" + password + "';";

	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password); // make
											   // pool
											   // XXX
	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	if (!rs.next()) {
	    User user = new User(0);
	    rs.close();
	    stmt.close();
	    con.close();
	    return user;
	}
	User user = new User(rs.getInt(1), con, rs.getBoolean(2));

	rs.close();
	stmt.close();

	return user;
    }

    public static User register(String login, String password) throws SQLException, ClassNotFoundException {

	Connection con;
	Statement stmt;
	String query = "INSERT INTO users (login, password) VALUES ('" + login + "','" + password + "');";

	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password); // REALIZE
	stmt = con.createStatement();
	try {
	    stmt.executeUpdate(query);
	} catch (SQLException se) {
	    System.err.println("USER ALREADY EXISTS");
	    return new User(0);
	}
	stmt.close();
	return logIn(login, password);

    }

    public static void logOut(User user) throws SQLException {
	user.getConnection().close();
    }

    public static void getData() throws SQLException {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT name, drink FROM products ORDER BY time;";

	con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password);

	stmt = con.createStatement();
	rs = stmt.executeQuery(query);
	while (rs.next()) {
	    if (rs.getBoolean(2) == true)
		CoffeeMachine.drinks.add(new Drink(rs.getString(1)));
	    else
		CoffeeMachine.ingredients.add(new Ingredient(rs.getString(1)));
	}

	rs.close();
	stmt.close();
	con.close();
    }

    public static int askAmount(User user, Buyable product) throws SQLException {
	// TODO
	if (user.getId() == 0)
	    return 0;
	int amount = 0;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT amount FROM products WHERE name='" + product.getName() + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    amount = rs.getInt(1);
	    rs.close();
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
	return amount;
    }

    public static void addProduct(User user, Buyable product, int amount) throws SQLException {
	if (!user.admin || user.getId() == 0)
	    return;
	Statement stmt;
	String query = "UPDATE products SET amount=amount+" + amount + " WHERE name='" + product.getName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
    }

    public static void removeProduct(User user, Buyable product, boolean drink) {

	if (!user.admin || user.getId() == 0)
	    return;
	Statement stmt;
	String query = "DELETE FROM products" + " WHERE name='" + product.getName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
    }

    public static void addNewProduct(User user, Buyable product, boolean drink) {

	if (!user.admin || user.getId() == 0)
	    return;
	Statement stmt;
	String query = "INSERT INTO products (name, drink)" + "VALUES ('" + product.getName() + "'," + drink + ");";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
    }

    public static boolean buyProduct(User user, Buyable product, int amount) {
	// TODO
	if (user.getId() == 0)
	    return false;
	Statement stmt;
	String query = "UPDATE products" + " SET amount=amount-" + amount + " WHERE name='" + product.getName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	    return false;
	}
    }

    public static double askPrice(User user, Buyable product) {
	// TODO
	if (user.getId() == 0)
	    return 0;
	double price = 0;
	ResultSet rs;
	Statement stmt;
	String query = "SELECT price FROM products" + " WHERE name='" + product.getName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    price = rs.getDouble(1);
	    rs.close();
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
	return price;
    }

    public static boolean setPrice(User user, Buyable product, double price) {

	if (!user.admin)
	    return false;
	Statement stmt;
	String query = "UPDATE products SET price=" + price + " WHERE name='" + product.getName() + "';";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	    return false;
	}
    }

    public static double getMoney(User user) {
	// TODO
	if (user.getId() == 0)
	    return 0;
	double purse = 0;
	ResultSet rs;
	Statement stmt;
	String query = "SELECT purse FROM users" + " WHERE user_id=" + user.getId() + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    purse = rs.getDouble(1);
	    rs.close();
	    stmt.close();
	} catch (SQLException se) {
	    // So bad
	}
	return purse;
    }

    public static boolean pay(User user, double amount) {
	// TODO
	if (user.getId() == 0)
	    return false;
	Statement stmt;
	String query = "UPDATE users SET purse=purse-" + amount + " WHERE user_id=" + user.getId() + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	}
	return false;
    }

    public static boolean addMoney(User user, double amount) {
	// TODO
	if (user.getId() == 0)
	    return false;
	Statement stmt;
	String query = "UPDATE users SET purse=purse+" + amount + " WHERE user_id=" + user.getId() + ";";
	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;
	} catch (SQLException se) {
	    // So bad
	}
	return false;

    }

}
