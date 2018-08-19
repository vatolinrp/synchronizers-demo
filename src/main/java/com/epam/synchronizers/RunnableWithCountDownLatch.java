package com.epam.synchronizers;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableWithCountDownLatch implements Runnable {

  private Logger logger = LoggerFactory.getLogger(RunnableWithCountDownLatch.class);

  private CountDownLatch countDownLatch;

  public RunnableWithCountDownLatch( CountDownLatch countDownLatch ) {
    this.countDownLatch=countDownLatch;
  }

  @Override
  public void run() {
    logger.info("trying to start thread logic by: {}", Thread.currentThread().getName());
    countDownLatch.countDown();
    logger.info("countdown by: {}", Thread.currentThread().getName());
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
    }
    logger.info("Thread {} is executing the logic....", Thread.currentThread().getName());
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
    }
    logger.info("work is completed by {}", Thread.currentThread().getName());
  }
}
