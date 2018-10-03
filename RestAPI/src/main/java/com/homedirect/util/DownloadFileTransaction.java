package com.homedirect.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homedirect.controller.AbstractController;
import com.homedirect.entity.Transaction;
import com.homedirect.exception.ATMException;
import com.homedirect.processor.impl.TransactionProcessorImpl;
import com.homedirect.response.ATMResponse;

@RestController
@RequestMapping("/excel")
public class DownloadFileTransaction extends AbstractController<TransactionProcessorImpl> {

	@GetMapping(value = "/transactions")
	public ATMResponse<?> write(@RequestParam("accountId") int id) throws ATMException, IOException {
		try {
			WriteFile writeFile = new WriteFile();
			List<Transaction> transactions = processor.findTransactionByAccountId(id);
			writeFile.writeListTransactiontoExcel(transactions);
			return toResponse(transactions);
		} catch (Exception e) {
			return toResponse(e);
		}
	}
}
