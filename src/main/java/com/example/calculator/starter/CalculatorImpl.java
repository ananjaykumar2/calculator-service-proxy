package com.example.calculator.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CalculatorImpl implements Calculator{

  @Override
  public void add(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler) {
    Double result = num1 + num2;
    System.out.println("add from CalculationService class is called");
    resultHandler.handle(Future.succeededFuture(result));
  }

  @Override
  public  void subtract(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler) {
    Double result = num1 - num2;
    System.out.println("subtract from CalculationService class is called");
    resultHandler.handle(Future.succeededFuture(result));
  }

  @Override
  public  void multiply(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler) {
    Double result = num1 * num2;
    System.out.println("multiply from CalculationService class is called");
//    JsonObject json_result = new JsonObject();
//    json_result.put("Multiply_result ", result);
    resultHandler.handle(Future.succeededFuture(result));
  }

  @Override
  public  void divide(Double num1, Double num2, Handler<AsyncResult<Double>> resultHandler) {
    Double result = num1 / num2;
    System.out.println("Divide from CalculationService class is called");


    resultHandler.handle(Future.succeededFuture(result));
  }

  @Override
  public void close()
  {

  }
}
