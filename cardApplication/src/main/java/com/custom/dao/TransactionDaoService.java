package com.custom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.model.Transaction;

public interface TransactionDaoService extends JpaRepository<Transaction, Integer> {

}
