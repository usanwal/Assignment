package com.custom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custom.model.Instrument;

import antlr.collections.List;

@Repository
public interface InstrumentDaoService extends JpaRepository<Instrument,String > {

	public int count(String cardNumber);
	
	public Instrument findByCardNumberActive(String cardNumber);
	
}