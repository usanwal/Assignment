package com.custom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Billing.findBillByCardNumberStatus", query = "SELECT p from Billing p where billingStatus = 'NEW' and cardNumber = ?1")
public class Billing {
	
	@Id
	@GeneratedValue
	@Column(name = "BILL_NUMBER")
	private int billNumber;
	
	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	@Column(name = "TOTAL_DUE_AMOUNT")
	private double totalDueAmount;
	
	@Column(name = "TOTAL_CASH_DUE")
	private double totalCashDue;
	
	@Column(name = "TOTAL_CREDIT_DUE")
	private double totalCreditDue;
	
	@Column(name = "MINIMUM_AMOUNT_DUE")
	private double minimumAmountDue;
	
	@Column(name = "DUE_DATE")
	private Date dueDate;
	
	@Column(name = "BILLING_STATUS")
	private String billingStatus;
	
	private int interest;
	
	@Column(name = "CARRY_FORWARDED_CASH")
	private double carryForwardedCash;
	

	@Column(name = "CARRY_FORWARDED_CREDIT")
	private double carryForwardedCredit;
	
	@Column(name = "CARRY_FORWARDED_CYCLE")
	private int carryForwardedCycle;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "MINIMUM_AMOUNT_PAID_FLAG")
	private CustomFlag minimumAmountPaidFlag;
	
	@Column(name = "AMOUNT_PAID")
	private double amountPaid;
	
	@Column(name = "LATE_CHARGES")
	private double lateCharges;
	
	public double getLateCharges() {
		return lateCharges;
	}

	public void setLateCharges(double lateCharges) {
		this.lateCharges = lateCharges;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber2) {
		this.cardNumber = cardNumber2;
	}

	public double getTotalDueAmount() {
		return totalDueAmount;
	}

	public void setTotalDueAmount(double totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}

	public double getTotalCashDue() {
		return totalCashDue;
	}

	public void setTotalCashDue(double totalCashDue) {
		this.totalCashDue = totalCashDue;
	}

	public double getTotalCreditDue() {
		return totalCreditDue;
	}

	public void setTotalCreditDue(double totalCreditDue) {
		this.totalCreditDue = totalCreditDue;
	}

	public double getMinimumAmountDue() {
		return minimumAmountDue;
	}

	public void setMinimumAmountDue(double minimumAmountDue) {
		this.minimumAmountDue = minimumAmountDue;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public int getCarryForwardedCycle() {
		return carryForwardedCycle;
	}

	public void setCarryForwardedCycle(int carryForwardedCycle) {
		this.carryForwardedCycle = carryForwardedCycle;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public CustomFlag getMinimumAmountPaidFlag() {
		return minimumAmountPaidFlag;
	}

	public void setMinimumAmountPaidFlag(CustomFlag minimumAmountPaidFlag) {
		this.minimumAmountPaidFlag = minimumAmountPaidFlag;
	}

	public double getCarryForwardedCash() {
		return carryForwardedCash;
	}

	public void setCarryForwardedCash(double carryForwardedCash) {
		this.carryForwardedCash = carryForwardedCash;
	}

	public double getCarryForwardedCredit() {
		return carryForwardedCredit;
	}

	public void setCarryForwardedCredit(double carryForwardedCredit) {
		this.carryForwardedCredit = carryForwardedCredit;
	}

	public Billing() {
		super();
	}

}
