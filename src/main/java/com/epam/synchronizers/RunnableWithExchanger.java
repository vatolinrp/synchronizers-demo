package com.epam.synchronizers;

import java.util.concurrent.Exchanger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableWithExchanger implements Runnable {

  private Logger logger = LoggerFactory.getLogger(RunnableWithSemaphore.class);

  private Exchanger<String> exchanger;

  public RunnableWithExchanger( Exchanger exchanger ) {
    this.exchanger=exchanger;
  }

  @Override
  public void run() {
    logger.info("trying to start thread logic by: {}", Thread.currentThread().getName());
    String exchangeReceived = null;
    try {
      exchangeReceived = exchanger.exchange(Thread.currentThread().getName());
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
    }
    logger.info("Thread {} got message from another thread: {}", Thread.currentThread().getName(), exchangeReceived);
  }
}
