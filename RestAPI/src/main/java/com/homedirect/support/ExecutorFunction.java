package com.homedirect.support;

@FunctionalInterface
public interface ExecutorFunction<T, R> {
	R execute(T t);
}
