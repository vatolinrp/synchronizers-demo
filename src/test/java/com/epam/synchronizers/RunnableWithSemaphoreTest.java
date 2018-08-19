package com.epam.synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RunnableWithSemaphoreTest {

  private Logger logger = LoggerFactory.getLogger(RunnableWithSemaphoreTest.class);

  /**
   * It is not a real unit test.
   * It was created just to see what is in the log output.
   */
  @Test
  public void test() {
    final Semaphore semaphore = new Semaphore(3,true);
    final List<Thread> threadList = new ArrayList<>();
    IntStream.rangeClosed(1,7).forEach(i -> threadList.add(new Thread(new RunnableWithSemaphore(semaphore))));
    threadList.forEach(thread -> {
      thread.start();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        logger.error(e.getMessage());
      }
    });
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      logger.error(e.getMessage());
    }
  }
}
