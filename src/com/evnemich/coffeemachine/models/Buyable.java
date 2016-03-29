package com.evnemich.coffeemachine.models;

import com.evnemich.coffeemachine.DataBase;
import com.evnemich.coffeemachine.models.users.User;

public class Buyable {

    public double GetPrice(User user) {
	return DataBase.AskPrice(user, this);
    }

    public int GetAmount(User user) {
	return DataBase.AskAmount(user, this);
    }

    public boolean Buy(User user, int amount) {
	if (amount > GetAmount(user))
	    return false;
	DataBase.BuyProduct(user, this, amount);
	return true;
    }
}
