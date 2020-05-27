package com.custom.service;

public interface CardApplicationService {
	
	String registerForCard(long mobileNumber , String name , String email );
	
	void transact(String cardNumber, int amount);
	
	void withdraw(String cardNumber, int amount);
	
	void generateCreditCardStatement(String cardNumber);
	
	void pay(String cardNumber, double amount);
	
	void closeCard(String cardNumber);
	

}
