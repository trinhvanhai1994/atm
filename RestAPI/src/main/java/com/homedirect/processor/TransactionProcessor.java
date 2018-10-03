package com.homedirect.processor;

import java.util.List;

import org.springframework.data.domain.Page;

import com.homedirect.entity.Transaction;
import com.homedirect.exception.ATMException;
import com.homedirect.request.DepositRequest;
import com.homedirect.request.SearchTransactionRequest;
import com.homedirect.request.TransferRequest;
import com.homedirect.request.WithdrawRequest;
import com.homedirect.response.TransactionResponse;

public interface TransactionProcessor {

	TransactionResponse deposit(DepositRequest depositRequest) throws ATMException;

	TransactionResponse withdrawal(WithdrawRequest withdrawRequest) throws ATMException;

	TransactionResponse transfer(TransferRequest transferRequest) throws ATMException;

	Page<Transaction> search(SearchTransactionRequest request) throws ATMException;

	List<Transaction> findTransactionByAccountId(int accountId) throws ATMException;
}
