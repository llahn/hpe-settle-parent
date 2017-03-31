package com.hpe.settle.demo.async;

import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class AsyncTask {
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static Random random = new Random();

	@Async
	public Future<String> doTaskOne() throws Exception {
		logger.debug("开始做任务一");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		logger.debug("完成任务一，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成");
	}

	@Async
	public Future<String> doTaskTwo() throws Exception {
		logger.debug("开始做任务二");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		logger.debug("完成任务二，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务二完成");
	}

	@Async
	public Future<String> doTaskThree() throws Exception {
		logger.debug("开始做任务三");
		long start = System.currentTimeMillis();
		Thread.sleep(random.nextInt(10000));
		long end = System.currentTimeMillis();
		logger.debug("完成任务三，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务三完成");
	}
}
