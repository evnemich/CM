package com.evnemich.coffeemachine.models.users;

import com.evnemich.coffeemachine.DataBase;

public class User {

    private int id;
    private double purse;

    public User(int id) {
	this.id = id;
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
