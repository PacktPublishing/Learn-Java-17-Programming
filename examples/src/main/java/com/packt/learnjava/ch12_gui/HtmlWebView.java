package com.packt.learnjava.ch12_gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class HtmlWebView extends Application {
    public static void main(String... args) {
        launch(args);
    }
    @Override
    public void stop(){
        System.out.println("Doing what has to be done before closing");
    }

    /*
     Before running any of the following start() methods, make sure to do these two steps:

      1) Download JavaFX SDK for your OS (from https://gluonhq.com/products/javafx/)
      and unzip it in any directory.

      2) Assuming you have unzipped JavaFX SDK into the folder /path/JavaFX/,
       add the following options to the Java command that launches this class:

        --module-path /path/JavaFX/lib
        --add-modules=javafx.controls,javafx.fxml
        --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.scene=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.util=ALL-UNNAMED
        --add-exports javafx.base/com.sun.javafx=ALL-UNNAMED
        --add-exports javafx.base/com.sun.javafx.logging=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.prism=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.glass.ui=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.geom.transform=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.tk=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.glass.utils=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.font=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED
        --add-exports javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.scene.input=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.geom=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.prism.paint=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.scenario.effect=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.text=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.iio=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.scenario.effect.impl.prism=ALL-UNNAMED
        --add-exports javafx.graphics/com.sun.javafx.scene.text=ALL-UNNAMED

       If you run it from IntelliJ, add these VM options to Run Configuration.
    */

    // To run examples, remove the number
    // from the name of one of the start() methods below
    // and put this number to the name of the start() method
    // that is currently without number. For example,
    // execute this class as-is, then
    // rename start8() to start() and start() to start8()
    // and execute this class again, so on.

    public void start1(Stage primaryStage) {
        try {
            WebView wv = new WebView();
            WebEngine we = wv.getEngine();
            String html = "<html><center><h2>Hello, world!</h2></center></html>";
            we.loadContent(html, "text/html");

            Scene scene = new Scene(wv, 200, 60);

            primaryStage.setTitle("My HTML page");
            primaryStage.setScene(scene);
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start2(Stage primaryStage) {
        try {
            Text txt = new Text("Below is the embedded HTML:");

            WebView wv = new WebView();
            WebEngine we = wv.getEngine();
            String html = "<html><center><h2>Hello, world!</h2></center></html>";
            we.loadContent(html, "text/html");

            VBox vb = new VBox(txt, wv);
            vb.setSpacing(10);
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10, 10, 10, 10));

            Scene scene = new Scene(vb, 300, 120);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX with embedded HTML");
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start3(Stage primaryStage) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String file = classLoader.getResource("form.html").getFile();

            Text txt = new Text("Fill the form and click Submit");

            WebView wv = new WebView();
            WebEngine we = wv.getEngine();
            File f = new File(file);
            we.load(f.toURI().toString());

            VBox vb = new VBox(txt, wv);
            vb.setSpacing(10);
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10, 10, 10, 10));

            Scene scene = new Scene(vb, 300, 200);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX with embedded HTML");
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start4(Stage primaryStage) {

        Text txt = new Text("Enjoy searching the Web!");

        WebView wv = new WebView();
        WebEngine we = wv.getEngine();
        we.load("http://www.google.com");
        //wv.setZoom(1.5);

/*
        WebHistory history = we.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        for(WebHistory.Entry entry: entries){
            String url = entry.getUrl();
            String title = entry.getTitle();
            Date date = entry.getLastVisitedDate();
        }
*/

        VBox vb = new VBox(txt, wv);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-font-size: 20px;-fx-background-color: lightblue;");
        vb.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vb,750,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with the window to another server");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("Bye! See you later!"));
        primaryStage.show();
    }

    public void start(Stage primaryStage) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String file = classLoader.getResource("jb.mp3").getFile();

        Text txt1 = new Text("What beautiful music!");
        Text txt2 = new Text("If you don't hear music, turn up the volume.");

        File f = new File(file);
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);

        VBox vb = new VBox(txt1, txt2, mv);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vb, 350, 100);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with embedded media player");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("Bye! See you later!"));
        primaryStage.show();

        mp.play();
    }

    public void start6(Stage primaryStage) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String file = classLoader.getResource("sea.mp4").getFile();

        Text txt = new Text("What a beautiful movie!");

        File f = new File(file);
        Media m = new Media(f.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        MediaView mv = new MediaView(mp);
        //mv.autosize();
        //mv.preserveRatioProperty();
        //mv.setFitHeight();
        //mv.setFitWidth();
        //mv.fitWidthProperty();
        //mv.fitHeightProperty()

        VBox vb = new VBox(txt, mv);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vb, 650, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with embedded media player");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("Bye! See you later!"));
        primaryStage.show();

        mp.play();
    }

    public void start7(Stage primaryStage) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String file = classLoader.getResource("jb.mp3").getFile();

        Text txt1 = new Text("What a beautiful movie and sound!");
        Text txt2 = new Text("If you don't hear music, turn up the volume.");

        File fs = new File(file);
        Media ms = new Media(fs.toURI().toString());
        MediaPlayer mps = new MediaPlayer(ms);
        MediaView mvs = new MediaView(mps);

        String file2 = classLoader.getResource("sea.mp4").getFile();
        File fv = new File(file2);
        Media mv = new Media(fv.toURI().toString());
        MediaPlayer mpv = new MediaPlayer(mv);
        MediaView mvv = new MediaView(mpv);

        VBox vb = new VBox(txt1, txt2, mvs, mvv);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(vb, 650, 500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with embedded media player");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("Bye! See you later!"));
        primaryStage.show();

        mpv.play();
        mps.play();
    }
}
