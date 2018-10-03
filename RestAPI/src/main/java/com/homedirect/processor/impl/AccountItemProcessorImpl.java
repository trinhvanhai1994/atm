package com.homedirect.processor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.homedirect.entity.Account;

public class AccountItemProcessorImpl implements ItemProcessor<Account, Account> {
	   private static final Logger log = LoggerFactory.getLogger(AccountItemProcessorImpl.class);

	   @Override
	   public Account process(final Account account) throws Exception {
		  final Integer id = account.getId();
	      final String accountNumber = account.getAccountNumber().toUpperCase();
	      final String username = account.getUsername().toUpperCase();
	      final String password = account.getPassword().toUpperCase();
	      final Double amount = account.getAmount();
	      final Account transformedAccount = new Account(id, accountNumber, username, password, amount);

	      log.info("Converting (" + account + ") into (" + transformedAccount + ")");
	      return transformedAccount;
	   }
	}