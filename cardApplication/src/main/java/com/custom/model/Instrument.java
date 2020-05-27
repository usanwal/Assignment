package com.custom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Instrument.findByCardNumberActive", query = "select p from Instrument p where p.instrumentState ='ACTIVE' and p.cardNumber = ?1 ")
public class Instrument {
	
	@Id
	@Column(name = "INSTRUMENT_ID")
	private long instrumentId;

	@Column(name = "CARD_NUMBER")
	private String cardNumber;
	
	private String name;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name = "INSTRUMENT_STATE")
	private InstrumentState instrumentState;
	
	@Column(name = "PHONE_NUMBER")
	private long  phoneNumber;
	
	@Column(name = "CREDIT_LIMIT")
	private double creditLimit;
	
	@Column(name = "CASH_LIMIT")
	private double cashLimit;
	
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	
	public long getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(long instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public InstrumentState getInstrumentState() {
		return instrumentState;
	}

	public void setInstrumentState(InstrumentState instrumentState) {
		this.instrumentState = instrumentState;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getCashLimit() {
		return cashLimit;
	}

	public void setCashLimit(double cashLimit) {
		this.cashLimit = cashLimit;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public void setEmailId(String emailId) {
		this.emailId= emailId;
	}
	
	public String getEmailId() {
		return emailId;
	}
	

	public Instrument() {
		super();
	}
	
	
}
