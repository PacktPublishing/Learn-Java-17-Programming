package com.packt.learnjava.gui;

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

public class GuiApp extends Application {
    public static void main(String... args) {
        launch(args);
    }
    @Override
    public void stop(){
        System.out.println("Doing what has to be done...");
    }

   /*
     Before running any of the following start() methods, make sure to do these two steps:

      1) Download JavaFX SDK for your OS (from https://gluonhq.com/products/javafx/)
      and unzip it in any directory.

      2) Assuming you have unzipped JavaFX SDK into the folder /path/JavaFX/,
       add the following options to the Java command that launches this class:

        --module-path /path/JavaFX/lib
        --add-modules=javafx.controls,javafx.fxml

        If you run it from IDE IntelliJ, add these VM options to Run Configuration.
    */
    public void start(Stage primaryStage) {
        Controller01.start(primaryStage);
    }
}