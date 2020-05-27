package com.custom.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custom.model.InstrumentAttributes;

@Repository
public interface InstrumentAttributeDaoService extends JpaRepository<InstrumentAttributes, String> {

	void save(Optional<InstrumentAttributes> instrumentAttributes);

	Optional<InstrumentAttributes> findById(String cardNumber);

}
