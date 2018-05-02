package io.vertx.verticles;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

import java.util.Random;

/**
 * @author Joel Takvorian, jtakvori@redhat.com
 */
public final class Greetings {
  private static final String[] GREETINGS = {
          "<b style='color: red; font-size: -webkit-xxx-large;'>This is line one!</b>",
          "<b style='color: green; font-size: -webkit-xxx-large;'>This is line two!</b>",
          "<b style='color: blue; font-size: -webkit-xxx-large;'>This is line three!</b>",
          "<b style='color: gray; font-size: -webkit-xxx-large;'>This is line four!</b>"
  };
  private static final Random RND = new Random();

  private Greetings() {
  }

  static void get(Vertx vertx, Handler<AsyncResult<String>> responseHandler) {
    vertx.executeBlocking(fut -> {
      // Simulate worker pool processing time between 200ms and 2s
      int processingTime = RND.nextInt(1800) + 200;
      try {
        Thread.sleep(processingTime);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      fut.complete(GREETINGS[RND.nextInt(4)]);
    }, responseHandler);
  }
}
