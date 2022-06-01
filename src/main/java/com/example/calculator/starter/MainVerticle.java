package com.example.calculator.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ProxyHelper;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  public static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
  //  final Router router = Router.router(vertx);
  public static final String ADD_ADDRESS = "com.add.calculator";
  public static final String SUBTRACT_ADDRESS = "com.subtract.calculator";
  public static final String MULTIPLY_ADDRESS = "com.multiply.calculator";
  public static final String DIVIDE_ADDRESS = "com.divide.calculator";
  Calculator calculation = new CalculatorImpl();

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
//    exception handler
    vertx.exceptionHandler(error -> {
      LOG.error("Unhandled : ", error);
    });
    vertx.deployVerticle(new MainVerticle(), asyncResult ->{
      if(asyncResult.failed()){
        LOG.error("Failed to deploy ", asyncResult.cause());
        return;
      }
      LOG.info("Deployed {} !", MainVerticle.class.getName());
    });

    vertx.deployVerticle(new ResultVertical(), asyncResult ->
    {
      if(asyncResult.failed())
      {
        LOG.error("Failed to deploy ", asyncResult.cause());
        return;
      }
      LOG.info("Deployed {} !", ResultVertical.class.getName());
    });

  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
//    register the service to the eventBus by passing address of service and ServiceInterface class
    new ServiceBinder(vertx)
      .setAddress(ADD_ADDRESS)
      .register(Calculator.class, calculation);

    CalculatorImpl service = new CalculatorImpl();

    ProxyHelper.registerService(Calculator.class, vertx, service, SUBTRACT_ADDRESS);

    new ServiceBinder(vertx)
      .setAddress(MULTIPLY_ADDRESS)
      .register(Calculator.class, service);


    new ServiceBinder(vertx)
      .setAddress(DIVIDE_ADDRESS)
      .register(Calculator.class, service);

//   get the router and attach it to the HTTP server

    startPromise.complete();
  }

}
