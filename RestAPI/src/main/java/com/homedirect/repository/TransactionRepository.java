package com.homedirect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.homedirect.entity.Transaction;


//them findTransactionByAccountNumber
@Repository
public interface TransactionRepository
		extends JpaRepository<Transaction, Integer>, QuerydslPredicateExecutor<Transaction> {

	@Query(value = "SELECT * FROM transaction p where BINARY p.from_account = ?1", nativeQuery = true)
	List<Transaction> findTransactionByAccountNumber(@Param("accountNumber") String accountNumber);
}
