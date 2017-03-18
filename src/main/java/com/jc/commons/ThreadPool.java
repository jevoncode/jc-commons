package com.jc.commons;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {

	private ThreadPoolExecutor executorService = null;

	private static ThreadPool threadPool = null;

	private ThreadPool(ThreadPoolExecutor executorService) {
		this.executorService = executorService;
	}

	public static ThreadPool init(int threadNum) {
		if (threadPool == null) {

			threadPool = new ThreadPool((ThreadPoolExecutor) Executors.newFixedThreadPool(threadNum));
			return threadPool;
		} else {
			return threadPool;
		}
	}

	public void submit(Runnable task) {
		executorService.submit(task);
	}

	public Future<Integer> submit(Callable<Integer> task) {
		return executorService.submit(task);
	}
}
