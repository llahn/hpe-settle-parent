package com.hpe.settle.demo.controller;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.settle.demo.async.AsyncTask;

@RestController
public class AsyncController {
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AsyncTask task;

	@RequestMapping("/async")
	public void async() throws Exception {
		long start = System.currentTimeMillis();

		Future<String> task1 = task.doTaskOne();
		Future<String> task2 = task.doTaskTwo();
		Future<String> task3 = task.doTaskThree();

		//logger.debug(task.doTaskOne().get());
		
		while (true) {
			if (task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();

		logger.debug("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
