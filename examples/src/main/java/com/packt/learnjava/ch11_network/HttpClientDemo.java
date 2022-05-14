package com.packt.learnjava.ch11_network;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class HttpClientDemo {
    public static void main(String[] args) {
        /* Run Server class before uncommenting and running
         * any of the following examples
         */
        get();
        //post();
        //getAsync1();
        //getAsync2();
        //postAsync();
        //postAsyncMultiple();
        //postAsyncMultipleCustomPool();
        //push();
        //webSocket();
    }

    private static void get(){
/*
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)  // default
                .build();
*/
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .GET()   // default
                .build();

        try {
            HttpResponse<String> resp = httpClient.send(req, BodyHandlers.ofString());
            System.out.println("Response: " + resp.statusCode() + " : " + resp.body());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void post(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .POST(BodyPublishers.ofString("Hi there!"))
                .build();

        try {
            HttpResponse<String> resp = httpClient.send(req, BodyHandlers.ofString());
            System.out.println("Response: " + resp.statusCode() + " : " + resp.body());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getAsync1(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .GET()   // default
                .build();

        CompletableFuture<Void> cf =
        httpClient.sendAsync(req, BodyHandlers.ofString())
                .thenAccept(resp -> System.out.println("Response: " +
                                resp.statusCode() + " : " + resp.body()));

        System.out.println("The request was sent asynchronously...");
        try {
            System.out.println("CompletableFuture get: " +
                    cf.get(5, TimeUnit.SECONDS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }

    private static void getAsync2(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .GET()   // default
                .build();

        CompletableFuture<String> cf =
                httpClient.sendAsync(req, BodyHandlers.ofString())
                        .thenApply(resp -> "Server responded: " + resp.body());

        System.out.println("The request was sent asynchronously...");
        try {
            System.out.println("CompletableFuture get: " +
                    cf.get(5, TimeUnit.SECONDS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }

    private static void postAsync(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .POST(BodyPublishers.ofString("Hi there!"))
                .build();

        CompletableFuture<String> cf =
                httpClient.sendAsync(req, BodyHandlers.ofString())
                        .thenApply(resp -> "Server responded: " + resp.body());
        System.out.println("The request was sent asynchronously...");
        try {
            System.out.println("CompletableFuture get: " +
                                     cf.get(5, TimeUnit.SECONDS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }



    private static void postAsyncMultiple(){
        HttpClient httpClient = HttpClient.newHttpClient();

        List<CompletableFuture<String>> cfs = new ArrayList<>();
        List<String> nums = List.of("1", "2", "3");
        for(String num: nums){
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/something"))
                    .POST(BodyPublishers.ofString("Hi! My name is " + num + "."))
                    .build();
            CompletableFuture<String> cf = httpClient
                    .sendAsync(req, BodyHandlers.ofString())
                    .thenApply(rsp -> "Server responded to msg " + num + ": "
                                    + rsp.statusCode() + " : " + rsp.body());
            cfs.add(cf);
        }
        System.out.println("The requests were sent asynchronously...");
        try {
            for(CompletableFuture<String> cf: cfs){
                System.out.println("CompletableFuture get: " + cf.get(5, TimeUnit.SECONDS));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }

    private static void postAsyncMultipleCustomPool(){
        ExecutorService pool = Executors.newFixedThreadPool(2);

        HttpClient httpClient = HttpClient.newBuilder().executor(pool).build();

        List<CompletableFuture<String>> cfs = new ArrayList<>();
        List<String> nums = List.of("1", "2", "3");
        for(String num: nums){
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:3333/something"))
                    .POST(BodyPublishers.ofString("Hi! My name is " + num + "."))
                    .build();
            CompletableFuture<String> cf = httpClient
                    .sendAsync(req, BodyHandlers.ofString())
                    .thenApply(rsp -> "Server responded to msg " + num + ": "
                            + rsp.statusCode() + " : " + rsp.body());

            cfs.add(cf);
        }
        System.out.println("The requests were sent asynchronously...");
        try {
            for(CompletableFuture<String> cf: cfs){
                System.out.println("CompletableFuture get: " + cf.get(5, TimeUnit.SECONDS));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }

    private static void push(){
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:3333/something"))
                .GET()
                .build();

        CompletableFuture cf =
                httpClient.sendAsync(req, BodyHandlers.ofString(),
                        (PushPromiseHandler) HttpClientDemo::applyPushPromise);

        System.out.println("The request was sent asynchronously...");
        try {
            System.out.println("CompletableFuture get: " + cf.get(5, TimeUnit.SECONDS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the client...");
    }

    private static void applyPushPromise(HttpRequest initReq, HttpRequest pushReq,
                                Function<BodyHandler, CompletableFuture<HttpResponse>> acceptor) {
        CompletableFuture<Void> cf = acceptor.apply(BodyHandlers.ofString())
                .thenAccept(resp -> System.out.println("Got pushed response " + resp.uri()));
        try {
            System.out.println("Pushed completableFuture get: " + cf.get(1, TimeUnit.SECONDS));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Exit the applyPushPromise function...");
    }

    private static void webSocket(){
        HttpClient httpClient = HttpClient.newHttpClient();
        WebSocket webSocket = httpClient.newWebSocketBuilder()
                .buildAsync(URI.create("ws://echo.websocket.events"), new WsClient())
                .join();

        System.out.println("The WebSocket was created and ran asynchronously.");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "Normal closure")
                .thenRun(() -> System.out.println("Close is sent."));
    }

    private static class WsClient implements Listener {
        @Override
        public void onOpen(WebSocket webSocket) {
            System.out.println("Connection established.");
            webSocket.sendText("Some message", true);
            Listener.super.onOpen(webSocket);
        }
        @Override
        public CompletionStage onText(WebSocket webSocket, CharSequence data, boolean last) {
            System.out.println("Method onText() got data: " + data);
            if(!webSocket.isOutputClosed()) {
                webSocket.sendText("Another message", true);
            }
            return Listener.super.onText(webSocket, data, last);
        }
        @Override
        public CompletionStage onClose(WebSocket webSocket, int statusCode, String reason) {
            System.out.println("Closed with status " + statusCode + ", reason: " + reason);
            return Listener.super.onClose(webSocket, statusCode, reason);
        }

    }

}
