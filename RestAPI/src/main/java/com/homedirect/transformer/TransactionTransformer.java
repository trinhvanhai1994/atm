package com.homedirect.transformer;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.homedirect.entity.Transaction;
import com.homedirect.response.TransactionResponse;

@Component
public class TransactionTransformer {

	public TransactionResponse toResponse(Transaction transactionHistory) {
		TransactionResponse response = new TransactionResponse();
		response.setId(transactionHistory.getId());
		response.setFromAccountNumber(transactionHistory.getFromAccount());
		response.setToAccountNumber(transactionHistory.getToAccount());
		response.setTransferAmount(transactionHistory.getTransferAmount());
		response.setTime(new Date());
		response.setStatus(transactionHistory.getStatus());
		response.setContent(transactionHistory.getContent());
		response.setType(transactionHistory.getType());
		return response;
	}

	public List<TransactionResponse> toResponse(List<Transaction> transactionHistories) {
		return transactionHistories.stream().map(this::toResponse).collect(Collectors.toList());
	}
}
