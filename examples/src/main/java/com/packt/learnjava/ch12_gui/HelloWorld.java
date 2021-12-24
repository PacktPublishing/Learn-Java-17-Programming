package com.packt.learnjava.ch12_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;

public class HelloWorld extends Application {
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

        If you run it from IDE, add these VM options to Run Configuration.
    */

    // To run examples, remove the number
    // from the name of one of the start() methods below
    // and put this number to the name of the start() method
    // that is currently without number. For example,
    // execute this class as-is, then
    // rename start1() to start() and start() to start1()
    // and execute this class again, so on.

    public void start1(Stage primaryStage) {
        Text txt = new Text("Hello, world!");
        txt.relocate(135, 40);

        Button btn = new Button("Exit");
        btn.relocate(155, 80);
        btn.setOnAction(e -> {
            System.out.println("Bye! See you later!");
            Platform.exit();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(txt, btn);

        primaryStage.setTitle("The primary stage (top-level container)");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("\nBye! See you later!"));
        primaryStage.setScene(new Scene(pane, 350, 150));
        primaryStage.show();
    }

    public void start2(Stage primaryStage) {
        Text txt = new Text("Fill the form and click Submit");
        TextField tfFirstName = new TextField();
        TextField tfLastName = new TextField();
        TextField tfAge = new TextField();
        Button btn = new Button("Submit");
        btn.setOnAction(e -> action(tfFirstName, tfLastName, tfAge));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(5);
        grid.setPadding(new Insets(20, 20, 20, 20));

        int i = 0;
        grid.add(txt,    0, i++, 2, 1);
        GridPane.setHalignment(txt, HPos.CENTER);
        grid.addRow(i++, new Label("First Name"),tfFirstName);
        grid.addRow(i++, new Label("Last Name"), tfLastName);
        grid.addRow(i++, new Label("Age"), tfAge);
        grid.add(btn,    1, i);
        GridPane.setHalignment(btn, HPos.CENTER);
        //grid.setGridLinesVisible(true);

        primaryStage.setTitle("Simple form example");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("\nBye! See you later!"));
        primaryStage.setScene(new Scene(grid, 300, 300));
        primaryStage.show();
    }

    private void action(TextField tfFirstName, TextField tfLastName, TextField tfAge ) {
        String fn = tfFirstName.getText();
        String ln = tfLastName.getText();
        String age = tfAge.getText();
        int a = 42;
        try {
            a = Integer.parseInt(age);
        } catch (Exception ex){}
        fn = fn.isBlank() ? "Nick" : fn;
        ln = ln.isBlank() ? "Samoylov" : ln;
        System.out.println("\nHello, " + fn + " " + ln + ", age " + a + "!");
        Platform.exit();
    }

    public void start3(Stage primaryStage) {
        Text txt = new Text("Test results:");

        PieChart pc = new PieChart();
        pc.getData().add(new PieChart.Data("Succeed", 143));
        pc.getData().add(new PieChart.Data("Failed" ,  12));
        pc.getData().add(new PieChart.Data("Ignored",  18));

        VBox vb = new VBox(txt, pc);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));

        primaryStage.setTitle("A chart example");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("\nBye! See you later!"));
        primaryStage.setScene(new Scene(vb, 300, 300));
        primaryStage.show();
    }

    public void start4(Stage primaryStage) {
        Text txt = new Text("Hello, world!");
        txt.setId("text-hello");
        txt.relocate(115, 40);

        Button btn = new Button("Exit");
        btn.setStyle("-fx-text-fill: white; -fx-background-color: red;");
        btn.relocate(155, 80);
        btn.setOnAction(e -> {
            System.out.println("Bye! See you later!");
            Platform.exit();
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(txt, btn);

        Scene scene = new Scene(pane, 350, 150);
        scene.getStylesheets().add("/mystyle.css");

        primaryStage.setTitle("The primary stage (top-level container)");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("\nBye! See you later!"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start5(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/helloWorld.fxml"));
            Scene scene = loader.load();

            primaryStage.setTitle("Simple form example");
            primaryStage.setScene(scene);
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("\nBye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {
        try {
            Text txt = new Text("What a beautiful image!");

            FileInputStream input = new FileInputStream("src/main/resources/packt.png");
            Image image = new Image(input);
            ImageView iv = new ImageView(image);

            VBox vb = new VBox(txt, iv);
            vb.setSpacing(20);
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10, 10, 10, 10));

            Scene scene = new Scene(vb, 300, 200);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX with embedded image");
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }



}