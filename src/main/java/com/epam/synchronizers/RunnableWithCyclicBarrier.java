package com.epam.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableWithCyclicBarrier implements Runnable {

  private Logger logger = LoggerFactory.getLogger(RunnableWithSemaphore.class);

  private CyclicBarrier cyclicBarrier;

  public RunnableWithCyclicBarrier( CyclicBarrier cyclicBarrier ) {
    this.cyclicBarrier=cyclicBarrier;
  }

  @Override
  public void run() {
    logger.info("trying to start thread logic by: {}", Thread.currentThread().getName());
    try {
      cyclicBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
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
