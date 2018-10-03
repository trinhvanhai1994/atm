package com.homedirect.processor.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.homedirect.constant.ErrorCode;
import com.homedirect.entity.Account;
import com.homedirect.entity.QTransaction;
import com.homedirect.entity.Transaction;
import com.homedirect.exception.ATMException;
import com.homedirect.processor.TransactionProcessor;
import com.homedirect.request.DepositRequest;
import com.homedirect.request.SearchTransactionRequest;
import com.homedirect.request.TransferRequest;
import com.homedirect.request.WithdrawRequest;
import com.homedirect.response.TransactionResponse;
import com.homedirect.service.AccountService;
import com.homedirect.service.TransactionService;
import com.homedirect.transformer.TransactionTransformer;
import com.homedirect.validator.ATMInputValidator;
import com.querydsl.core.BooleanBuilder;

//them findTransactionByAccountId

@Service
public class TransactionProcessorImpl implements TransactionProcessor {

	private @Autowired TransactionService service;
	private @Autowired TransactionTransformer transformer;
	private @Autowired AccountService accountService;
	private @Autowired ATMInputValidator validatorInputATM;

	@Override
	public TransactionResponse deposit(DepositRequest depositRequest) throws ATMException {
		Account account = accountService.findById(depositRequest.getId());
		Double amount = depositRequest.getAmount();
		ATMInputValidator.validatorDeposit(amount);
		Transaction transaction = service.deposit(account, amount);
		return transformer.toResponse(transaction);
	}

	@Override
	public TransactionResponse withdrawal(WithdrawRequest withdrawRequest) throws ATMException {
		Double amount = withdrawRequest.getAmount();
		Account account = accountService.findById(withdrawRequest.getId());
		ATMInputValidator.validatorWithdraw(amount, account.getAmount());
		if (!BCrypt.checkpw(withdrawRequest.getPassword(), account.getPassword())) {
			throw new ATMException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASWORD_MES, withdrawRequest.getPassword());
		}

		Transaction transaction = service.withdraw(account, amount);
		return transformer.toResponse(transaction);
	}

	@Override
	public TransactionResponse transfer(TransferRequest request) throws ATMException {
		Account fromAccount = accountService.findById(request.getFromId());
		Account toAccount = validatorInputATM.isValidateInputTransfer(request.getToAccountNumber());
		validatorInputATM.checkTransfer(toAccount.getId(), request.getFromId());
		
		if (!BCrypt.checkpw(request.getPassword(), fromAccount.getPassword())) {
			throw new ATMException(ErrorCode.INVALID_PASSWORD, ErrorCode.INVALID_PASWORD_MES, request.getPassword());
		}

		accountService.save(fromAccount);
		accountService.save(toAccount);
		Transaction transaction = service.transfer(fromAccount, toAccount, request.getAmount(), request.getContent());
		return transformer.toResponse(transaction);
	}

	@Override
	public Page<Transaction> search(SearchTransactionRequest request) throws ATMException {
		return service.search(request.getAccountId(), request.getFromDate(), request.getToDate(),
				request.getType(), request.getPageNo(), request.getPageSize());
	}
	
	public List<Transaction> findTransactionByAccountId(int accountId) throws ATMException {
		Account account = accountService.findById(accountId);
		String accountNumber = account.getAccountNumber();
		QTransaction transaction = QTransaction.transaction;
		BooleanBuilder where = new BooleanBuilder();

		if (accountNumber != null) {
			where.and(transaction.fromAccount.eq(accountNumber));
		}
		return service.findTransactionByAccountNumber(accountNumber);
	}
	
	public List<Transaction> findAll() {
		return service.findAll();
	}
}
