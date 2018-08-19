package com.epam.synchronizers;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableWithSemaphore implements Runnable {

  private Logger logger = LoggerFactory.getLogger(RunnableWithSemaphore.class);

  private Semaphore semaphore;

  public RunnableWithSemaphore( Semaphore semaphore ) {
    this.semaphore=semaphore;
  }

  @Override
  public void run() {
    logger.info("trying to start thread logic by: {}", Thread.currentThread().getName());
    try {
      semaphore.acquire();
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
    semaphore.release();
  }
}
