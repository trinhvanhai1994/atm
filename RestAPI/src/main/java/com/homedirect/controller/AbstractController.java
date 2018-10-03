package com.homedirect.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.homedirect.constant.ErrorCode;
import com.homedirect.entity.Page;
import com.homedirect.exception.ATMException;
import com.homedirect.response.ATMResponse;
import com.homedirect.support.ExecutorFunction;
import com.homedirect.support.ExecutorFunctionPage;
import com.homedirect.support.ExecutorSupplier;

// thêm log cho Exception
// sửa lại hàm ATMResponse
public abstract class AbstractController<P> {

	final static Logger logger = Logger.getLogger(AccountController.class);
	protected @Autowired P processor;

	protected <O> ATMResponse<O> toResponse(O data) {             //(***)
		ATMResponse<O> response = new ATMResponse<O>();
		if (data instanceof ATMException) {
			ATMException e = (ATMException) data;
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.debug(e);
				logger.error(e.getMessage());
			}
			return response;
		}

		if (data instanceof Exception) {
			Exception e = (Exception) data;
			response.setCode(ErrorCode.UNKNOWN);
			response.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.debug(e);
				logger.error(e.getMessage());
			}
			return response;
		}

		response.setCode(ErrorCode.SUCCESS);
		response.setMessage(ErrorCode.SUCCESS_MES);
		response.setData(data);
		return response;
	}
	
	// Viết các phương thức abstract + try/catch thay cho phương thức  (***)
	// sử dụng các  "@funtionalInterface" ExecutorFunction...
	protected <T, R> ATMResponse<R> apply(T t, ExecutorFunction<T, R> executor) {
		try {
			R data = executor.execute(t);
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			logger.error(e.getMessage());
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e.getCause());
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
	
	protected <T, R> ATMResponse<Page<R>> apply(T t, ExecutorFunctionPage<T, R> executor) {
		try {
			Page<R> data = executor.execute(t);
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			logger.error(e.getMessage());
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e.getCause());
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
	
	protected <R> ATMResponse<R> apply(ExecutorSupplier<R> executor) {
		try {
			R data = executor.execute();
			return new ATMResponse<>(ErrorCode.SUCCESS, ErrorCode.SUCCESS_MES, data);
		} catch (ATMException e) {
			logger.error(e.getMessage());
			return new ATMResponse<>(e.getCode(), e.getMessage(), null);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e.getCause());
			return new ATMResponse<>(ErrorCode.UNKNOWN, e.getMessage(), null);
		}
	}
}
