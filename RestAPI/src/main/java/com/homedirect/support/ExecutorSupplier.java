package com.homedirect.support;

@FunctionalInterface
public interface ExecutorSupplier<R> {
	R execute();
}
