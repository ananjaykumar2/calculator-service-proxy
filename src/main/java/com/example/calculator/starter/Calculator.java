package com.example.calculator.starter;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyClose;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@VertxGen
@ProxyGen
public interface Calculator {
  void add(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler);
  void subtract(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler);
  void  multiply(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler);
  void divide(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler);

  // Create is used when you deploy a vertical (ie you deploy the service)
  static Calculator create()
  {
    return new CalculatorImpl();
  }

  // Proxy is used when you use/test the service as server client code

  static Calculator createProxy(Vertx vertx, String address)
  {
    return new CalculatorVertxEBProxy(vertx, address);
//    return createProxy(CalculationServiceInterface.class, vertx,address);
//   return ProxyHelper.createProxy(CalculationServiceInterface.class, vertx, address);
  }

  @ProxyClose
  void close();
}
