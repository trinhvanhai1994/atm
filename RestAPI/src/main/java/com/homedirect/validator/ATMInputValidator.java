package com.homedirect.validator;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.constant.ErrorCode;
import com.homedirect.entity.Account;
import com.homedirect.entity.Transaction;
import com.homedirect.exception.ATMException;
import com.homedirect.service.AccountService;

@Component
public class ATMInputValidator {

	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,15}$";
	private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	private @Autowired ATMStorageValidator validatorStorageATM;
	private @Autowired AccountService accountService;

	public static boolean validateUsername(String username) {
		return isValidUsername(username);
	}

	public static boolean validatePassword(String password) {
		return isValidPassword(password);
	}

	public static boolean validatorDeposit(Double amount) throws ATMException {
		if (amount == null) {
			throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
		}
		if (amount <= 0 || amount % 10000 != 0) {
			throw new ATMException(ErrorCode.INVALID_AMOUNT_DEPOSIT, ErrorCode.INVALID_DEPOSIT_MES);
		}
		return true;
	}

	public static boolean validatorWithdraw(Double amount, Double oldAmount) throws ATMException {
		if (amount == null) {
			throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
		}
		if (amount <= 0 || amount % 10000 != 0 || amount > Transaction.Constant.MAX_AMOUNT_WITHDRAW) {
			throw new ATMException(ErrorCode.INVALID_AMOUNT_WITHDRAW, ErrorCode.INVALID_WITHDRAW_MES);
		}
		if (oldAmount - amount - Transaction.Constant.FEE_TRANSFER < Transaction.Constant.DEFAULT_BALANCE) {
			throw new ATMException(ErrorCode.INVALID_AMOUNT_WITHDRAW, ErrorCode.INVALID_WITHDRAW_MES);
		}
		return true;
	}

	public static boolean isValidUsername(String username) {
		return username.matches(USERNAME_PATTERN);
	}

	public static boolean isValidPassword(String password) {
		return password.matches(PASSWORD_PATTERN);
	}

	public boolean isValidCreateAccount(String username, String password) throws ATMException {
		if (!validateUsername(username)) {
			throw new ATMException(ErrorCode.INVALID_INPUT_USERNAME, ErrorCode.INVALID_INPUT_USERNAME_MES);
		}
		if (!validatePassword(password)) {
			throw new ATMException(ErrorCode.INVALID_INPUT_PASSWORD, ErrorCode.INVALID_INPUT_PASWORD_MES);
		}
		if (username == null || password == null) {
			throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
		}
		if (!validatorStorageATM.checkUserName(username)) {
			throw new ATMException(ErrorCode.USERNAME_EXIST, ErrorCode.USERNAME_EXIST_MES, username);
		}
		return true;
	}

	public static Date getDate() {
		java.util.Date currentime = new Date();
		Timestamp date = new Timestamp(currentime.getTime());
		return date;
	}

	public static String formatAmount(Double amount) {
		DecimalFormat myFormatter = new DecimalFormat("###,###.00");
		String output = myFormatter.format(amount);
		return output;
	}

	public Account isValidateInputTransfer(String toAccountNumber) throws ATMException {
		Account toAccount = accountService.findByAccountNumber(toAccountNumber);
		if (toAccount == null) {
			throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, toAccount);
		}
		return toAccount;
	}

	public boolean checkTransfer(Integer toId, Integer fromId) throws ATMException {
		if (toId == fromId) {
			throw new ATMException(ErrorCode.INVALID_AMOUNT_DEPOSIT, ErrorCode.DUPLICATE_INPUT_MES, toId);
		}

		if (validatorStorageATM.checkId(toId)) {
			throw new ATMException(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND_MES, toId);
		}

		if (toId == null || fromId == null) {
			throw new ATMException(ErrorCode.MISS_DATA, ErrorCode.MISS_DATA_MES);
		}
		return true;
	}

	public String numberFormat(String pattern, double value) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String output = decimalFormat.format(value);
		return output;
	}

	public String setAmount(double amount) {
		String pattern = "###,###,###";
		return numberFormat(pattern, amount);
	}
}
