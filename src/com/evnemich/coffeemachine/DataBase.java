package com.evnemich.coffeemachine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.evnemich.coffeemachine.models.User;

public class DataBase {

    /**
     * Database connection url
     */
    private static final String url = "jdbc:mysql://localhost:3306/coffeemachine?useSSL=false";
    /**
     * Database username
     */
    private static final String user = "root";
    /**
     * Database password
     */
    private static final String password = "458945";

    /**
     * Log in into coffee-machine and database. Store connection and user_id in
     * User instance
     * 
     * @param login
     *            users login
     * @param password
     *            users password
     * @return User instance (will be invalid if login-password was wrong)
     * @throws ClassNotFoundException
     *             driver registration error
     */
    public static User logIn(String login, String password) throws ClassNotFoundException {

	Class.forName("com.mysql.jdbc.Driver");

	Connection con;
	Statement stmt;
	ResultSet rs;
	User user;
	String query = "SELECT user_id, admin FROM users WHERE login='" + login + "' AND password='" + password + "';";

	try {

	    con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password);
	    stmt = con.createStatement();
	    rs = stmt.executeQuery(query);

	    if (!rs.next()) {
		user = new User();
		rs.close();
		stmt.close();
		con.close();
		return user;
	    }

	    user = new User(rs.getInt(1), con, rs.getBoolean(2));

	    rs.close();
	    stmt.close();

	} catch (SQLException e) {

	    user = new User();
	    System.err.println("ERROR with database connection");

	}

	return user;
    }

    /**
     * Register new user and logging in with new login-password
     * 
     * @param login
     *            users login
     * @param password
     *            users password
     * @return User instance (will be invalid if login already exists)
     * @throws ClassNotFoundException
     */
    public static User register(String login, String password) throws ClassNotFoundException {

	Class.forName("com.mysql.jdbc.Driver");

	Connection con;
	Statement stmt;
	String query = "INSERT INTO users (login, password) VALUES ('" + login + "','" + password + "');";

	try {
	    con = DriverManager.getConnection(DataBase.url, DataBase.user, DataBase.password); // REALIZE
	    stmt = con.createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR user already exists or cant get database connection");
	    return new User();

	}

	return logIn(login, password);
    }

    /**
     * Terminate connection to database
     * 
     * @param user
     *            user instance
     */
    public static void logOut(User user) {

	try {
	    if (user != null && user.isValid() && !user.getConnection().isClosed())
		user.getConnection().close();

	} catch (SQLException e) {

	    System.err.println("ERROR cannot close connection");

	}
    }

    /**
     * Update static variables
     * 
     * @param user
     *            user instance
     */
    public static void getData(User user) {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT name, drink FROM products WHERE amount<>0 ORDER BY time;";
	con = user.getConnection();

	try {
	    stmt = con.createStatement();
	    rs = stmt.executeQuery(query);

	    while (rs.next()) {

		if (rs.getBoolean(2) == true)
		    CoffeeMachine.drinks.add(rs.getString(1));
		else
		    CoffeeMachine.ingredients.add(rs.getString(1));

	    }

	    query = "SELECT name,price FROM products ORDER BY time;";
	    rs = stmt.executeQuery(query);

	    while (rs.next()) {

		CoffeeMachine.products.add(rs.getString(1));
		CoffeeMachine.price.put(rs.getString(1), rs.getDouble(2));

	    }

	    stmt.close();

	} catch (SQLException e) {

	    System.err.println("ERROR cant get select query result");

	}
    }

    /**
     * Asking database about amount available <code>product</code>
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @return amount of available products
     */
    public static int askAmount(User user, String product) {

	if (!user.isValid())
	    return 0;

	int amount = 0;
	Statement stmt;
	ResultSet rs;
	String query = "SELECT amount FROM products WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    amount = rs.getInt(1);
	    rs.close();
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR cant get select query result");

	}

	return amount;
    }

    /**
     * Add existed product to database
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @param amount
     *            amount
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean addProduct(User user, String product, int amount) {

	if (!user.isValid() || !user.admin)
	    return false;

	Statement stmt;
	String query = "UPDATE products SET amount=amount+" + amount + " WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR cant get select query result");

	}

	return true;
    }

    /**
     * Removing existed product from database
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean removeProduct(User user, String product) {

	if (!user.admin || !user.isValid())
	    return false;

	Statement stmt;
	String query = "DELETE FROM products" + " WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR cant run delete query");

	}

	return true;
    }

    /**
     * Add new product to database
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @param drink
     *            boolean indicator <code>true</code> - drink,
     *            <code>false</code> - ingredient
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean addNewProduct(User user, String product, boolean drink) {

	if (!user.admin || !user.isValid())
	    return false;

	Statement stmt;
	String query = "INSERT INTO products (name, drink)" + "VALUES ('" + product + "'," + drink + ");";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR cant run insert query");

	}

	return true;
    }

    /**
     * Remove <code>amount</code> products from database and serve user to pay
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @param amount
     *            amount of product to buy
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean buyProduct(User user, String product, int amount) {

	if (!user.isValid())
	    return false;

	Statement stmt;
	String query = "UPDATE products" + " SET amount=amount-" + amount + " WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;

	} catch (SQLException se) {

	    System.err.println("ERROR cant run insert query");
	    return false;
	}
    }

    /**
     * Asking for price of product
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static double askPrice(User user, String product) {

	if (!user.isValid())
	    return 0;

	double price = 0;
	ResultSet rs;
	Statement stmt;
	String query = "SELECT price FROM products" + " WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    price = rs.getDouble(1);
	    rs.close();
	    stmt.close();

	} catch (SQLException se) {

	    System.err.println("ERROR with select query");

	}

	return price;
    }

    /**
     * Setting price of product
     * 
     * @param user
     *            user instance
     * @param product
     *            product name
     * @param price
     *            new price of <code>product</code>
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean setPrice(User user, String product, double price) {

	if (!user.admin)
	    return false;

	Statement stmt;
	String query = "UPDATE products SET price=" + price + " WHERE name='" + product + "';";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;

	} catch (SQLException se) {

	    System.err.println("ERROR with update query");
	    return false;

	}
    }

    /**
     * Getting amount of money user has
     * 
     * @param user
     *            user instance
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static double getMoney(User user) {

	if (!user.isValid())
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

	    System.err.println("ERROR with select query");

	}

	return purse;
    }

    /**
     * Paying for bought products
     * 
     * @param user
     *            user instance
     * @param amount
     *            amount of money to pay
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean pay(User user, double amount) {

	if (!user.isValid())
	    return false;

	Statement stmt;
	String query = "UPDATE users SET purse=purse-" + amount + " WHERE user_id=" + user.getId() + ";";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;

	} catch (SQLException se) {

	    System.err.println("ERROR with update query");

	}

	return false;
    }

    /**
     * Adding money to the users balance
     * 
     * @param user
     *            user instance
     * @param amount
     *            amount of money to add
     * @return <code>boolean</code> indicator of successfull completion
     */
    public static boolean addMoney(User user, double amount) {

	if (!user.isValid())
	    return false;

	Statement stmt;
	String query = "UPDATE users SET purse=purse+" + amount + " WHERE user_id=" + user.getId() + ";";

	try {
	    stmt = user.getConnection().createStatement();
	    stmt.executeUpdate(query);
	    stmt.close();
	    return true;

	} catch (SQLException se) {

	    System.err.println("ERROR with update query");

	}

	return false;
    }
}
