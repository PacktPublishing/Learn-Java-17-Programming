package com.packt.learnjava.ch16_microservices;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class HttpServerVert extends AbstractVerticle {
    private int port;
    private String name;
    public HttpServerVert(int port) {
        this.port = port;
    }

    public void start(Future<Void> startFuture) {
        this.name = this.getClass().getSimpleName() + "(" + Thread.currentThread().getName() + ", localhost:" + port + ")";
        Router router = Router.router(vertx);
        router.get("/some/path/:name/:address/:anotherParam").handler(this::processGetSomePath);
        router.post("/some/path/send").handler(this::processPostSomePathSend);
        router.post("/some/path/publish").handler(this::processPostSomePathPublish);
        vertx.createHttpServer().requestHandler(router::handle).rxListen(port).subscribe();
        System.out.println(name + " is waiting...");
    }

    private void processGetSomePath(RoutingContext ctx){
        String caller = ctx.pathParam("name");
        String address = ctx.pathParam("address");
        String value = ctx.pathParam("anotherParam");
        System.out.println("\n" + name + ": " + caller + " called.");
        vertx.eventBus().rxRequest(address, caller + " called with value " + value).toObservable()
            .subscribe(reply -> {
                System.out.println(name + ": got message\n    " + reply.body());
                ctx.response().setStatusCode(200).end(reply.body().toString() + "\n");
            }, Throwable::printStackTrace);
    }

    private void processPostSomePathSend(RoutingContext ctx){
        ctx.request().bodyHandler(buffer -> {
            System.out.println("\n" + name + ": got payload\n    " + buffer);
            JsonObject payload = new JsonObject(buffer.toString());
            String caller = payload.getString("name");
            String address = payload.getString("address");
            String value = payload.getString("anotherParam");
            vertx.eventBus().rxRequest(address, caller + " called with value " + value).toObservable()
                .subscribe(reply -> {
                    System.out.println(name + ": got message\n    " + reply.body());
                    ctx.response().setStatusCode(200).end(reply.body().toString() + "\n");
                }, Throwable::printStackTrace);
        });
    }

    private void processPostSomePathPublish(RoutingContext ctx){
        ctx.request().bodyHandler(buffer -> {
            System.out.println("\n" + name + ": got payload\n    " + buffer);
            JsonObject payload = new JsonObject(buffer.toString());
            String caller = payload.getString("name");
            String address = payload.getString("address");
            String value = payload.getString("anotherParam");
            vertx.eventBus().publish(address, caller + " called with value " + value);
            ctx.response().setStatusCode(202).end("The message was published to address " + address + ".\n");
        });
    }

    private void processGetSomePath1(RoutingContext ctx){
        ctx.response().setStatusCode(200).end("Got into processGetSomePath using " + ctx.normalisedPath() + "\n");
    }

    private void processPostSomePathSend1(RoutingContext ctx){
        ctx.response().setStatusCode(200).end("Got into processPostSomePathSend using " + ctx.normalisedPath() + "\n");
    }

    private void processPostSomePathPublish1(RoutingContext ctx){
        ctx.response().setStatusCode(200).end("Got into processPostSomePathPublish using " + ctx.normalisedPath() + "\n");
    }

    public void start1(Future<Void> startFuture) {
        String name = this.getClass().getSimpleName() + "(" + Thread.currentThread().getName() + ", localhost:" + port + ")";

        HttpServer server = vertx.createHttpServer();

        server.requestStream().toObservable()
              .subscribe(request -> request.response()
                        .setStatusCode(200)
                        .end("Hello from " + name + "!\n")
               );

        server.rxListen(port).subscribe();

        System.out.println(name + " is waiting...");
    }

}
