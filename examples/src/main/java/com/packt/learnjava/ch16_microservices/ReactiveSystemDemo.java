package com.packt.learnjava.ch16_microservices;

import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;

public class ReactiveSystemDemo {
    public static void main(String... args) {
        String address = "One";
        Vertx vertx = Vertx.vertx();
        RxHelper.deployVerticle(vertx, new MessageRcvVert("1", address));
        RxHelper.deployVerticle(vertx, new MessageRcvVert("2", address));
        RxHelper.deployVerticle(vertx, new MessageRcvVert("3", "Two"));
        RxHelper.deployVerticle(vertx, new HttpServerVert(8082));
    }
}
