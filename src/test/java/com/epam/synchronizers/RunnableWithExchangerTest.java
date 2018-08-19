package com.epam.synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RunnableWithExchangerTest {

  private Logger logger = LoggerFactory.getLogger(RunnableWithExchangerTest.class);

  /**
   * It is not a real unit test.
   * It was created just to see what is in the log output.
   */
  @Test
  public void test() {
    final Exchanger<String> exchanger = new Exchanger<>();
    final List<Thread> threadList = new ArrayList<>();
    IntStream.rangeClosed(1,2).forEach(i -> threadList.add(new Thread(new RunnableWithExchanger(exchanger))));
    threadList.forEach(thread -> {
      thread.start();
      try {
        Thread.sleep(5000);
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
