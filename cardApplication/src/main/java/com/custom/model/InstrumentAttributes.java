package com.custom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstrumentAttributes {
	
	@Id
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "AVAILABLE_CREDIT_LIMIT")
	private double availableCreditLimit;
	
	@Column(name = "AVAILABLE_CASH_LIMIT")
	private double availableCashLimit;
	
	@Column(name = "INTEREST_CHARGED")
	private double interestCharged;
	
	@Column(name = "BILL_CYCLE")
	private int billCycle;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public double getAvailableCreditLimit() {
		return availableCreditLimit;
	}

	public void setAvailableCreditLimit(double availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}

	public double getAvailableCashLimit() {
		return availableCashLimit;
	}

	public void setAvailableCashLimit(double availableCashLimit) {
		this.availableCashLimit = availableCashLimit;
	}

	public double getInterestCharged() {
		return interestCharged;
	}

	public void setInterestCharged(double interestCharged) {
		this.interestCharged = interestCharged;
	}

	public int getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(int i) {
		this.billCycle = i;
	}

	public InstrumentAttributes() {
		super();
	}

}
