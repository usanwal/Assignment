package com.custom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.model.Billing;

public interface BillingDaoService extends JpaRepository<Billing, Integer> {

	public Billing findBillByCardNumberStatus(String cardNumber);
	
}
