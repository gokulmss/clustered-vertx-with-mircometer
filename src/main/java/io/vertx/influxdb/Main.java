package io.vertx.influxdb;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxInfluxDbOptions;
import io.vertx.micrometer.VertxPrometheusOptions;

import io.vertx.verticles.EventbusConsumer;
import io.vertx.verticles.EventbusProducer;
import io.vertx.verticles.SimpleWebServer;
// import org.vertx.java.spi.cluster.impl.hazelcast.HazelcastClusterManagerFactory;

/**
 * @author Joel Takvorian, jtakvori@redhat.com
 */
public class Main {
  static Vertx vertx;
  public static void main(String[] args) {

    System.out.println("With clusteredVertx: " + args[0]);

    // Default InfluxDB options will push metrics to localhost:8086, db "default"
    MicrometerMetricsOptions options = new MicrometerMetricsOptions()
      .setInfluxDbOptions(new VertxInfluxDbOptions().setEnabled(true))
      .setEnabled(true);
    
    VertxOptions vertxOptions = new VertxOptions();
    vertxOptions.setMetricsOptions(options);

    if(args[0].equals("true")){
      System.out.println("Inside with clusteredVertx part..................");
      Vertx.clusteredVertx(vertxOptions, result -> {
        System.out.println("VERTX DEBUG: Clustered Vertx Instance Creation Completed.");
        vertx = result.result();
        vertx.exceptionHandler(err -> {
          System.err.println("Erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr ");
          err.printStackTrace();
        });                    
      });
    } else {
      System.out.println("Inside without clusteredVertx part..................");
      vertx = Vertx.vertx(vertxOptions);  
    }



    vertx.deployVerticle(new SimpleWebServer());
    vertx.deployVerticle(new EventbusConsumer());
    vertx.deployVerticle(new EventbusProducer());
  }
}

