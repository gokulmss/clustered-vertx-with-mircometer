# clustered-vertx-with-mircometer

To Run:

    mvn clean package

With clusteredVertx:

    java -cp target/micrometer-metrics-examples-3.5.1-fat.jar io.vertx.influxdb.Main true	

Without clusteredVertx:


    java -cp target/micrometer-metrics-examples-3.5.1-fat.jar io.vertx.influxdb.Main false	
