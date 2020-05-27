package com.custom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id
	@Column(name = "TRANSACTION_ID")
	private int transactionId;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	private double amount;
	
	private String status;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber2) {
		this.cardNumber = cardNumber2;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Transaction() {
		super();
	}

}
