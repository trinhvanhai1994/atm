package com.homedirect.transformer;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.entity.Account;
import com.homedirect.entity.Page;
import com.homedirect.request.AccountRequest;
import com.homedirect.response.AccountResponse;
import com.homedirect.service.AccountService;

@Component
public class AccountTransformer {

	private @Autowired AccountService service;

	public Account toAccount(AccountRequest request) throws NoSuchAlgorithmException {
		Account newAccount = new Account();
		newAccount.setId(request.getId());
		newAccount.setAccountNumber(service.generateAccountNumber());
		newAccount.setUsername(request.getUsername());
		newAccount.setPassword(request.getPassword());
		newAccount.setAmount(Account.Constant.DEFAULT_AMOUNT);
		return newAccount;
	}

	public AccountResponse toResponse(Account account) {
		AccountResponse response = new AccountResponse();
		response.setId(account.getId());
		response.setAccountNumber(account.getAccountNumber());
		response.setUsername(account.getUsername());
		response.setAmount(account.getAmount());
		return response;
	}
	
	public List<AccountResponse> toResponseList(List<Account> accounts) {
		return accounts.stream().map(this::toResponse).collect(Collectors.toList());
	}
	
	public Page<AccountResponse> toResponse(Page<Account> page) {
		if (page == null) return new Page<AccountResponse>();
		return new Page<>(page.getPageNo(), page.getPageSize(), page.getTotalElements(), toResponseList(page.getContent()));
	}
}
