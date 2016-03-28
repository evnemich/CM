package com.evnemich.coffeemachine.models;

import com.evnemich.coffeemachine.DataBase;

public class Buyable {
    
    public double GetPrice(){
	return DataBase.AskPrice(this);
    }
    
    public int GetAmount(){
	return DataBase.AskAmount(this);
    }
    
    public boolean Buy(int amount){
	if(amount > GetAmount())
	    return false;
	DataBase.BuyProduct(this, amount);
	return true;
    }
}
