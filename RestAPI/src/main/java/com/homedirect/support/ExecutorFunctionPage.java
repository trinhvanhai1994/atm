package com.homedirect.support;

import com.homedirect.entity.Page;

@FunctionalInterface
public interface ExecutorFunctionPage<T, R> {
	Page<R> execute(T t);
}
