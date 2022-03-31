package com.packt.learnjava.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Controller01 {
    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfAge;

    public static void start(Stage primaryStage) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String file = classLoader.getResource("fxml" + File.separator + "page01.fxml").getFile();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:" + file));
            Scene scene = loader.load();

            primaryStage.setTitle("First page of GUI App");
            primaryStage.setScene(scene);
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("\nBye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    protected void submitClicked(ActionEvent e) {
        String fn = tfFirstName.getText();
        String ln = tfLastName.getText();
        String age = tfAge.getText();
        int a = 42;
        try {
            a = Integer.parseInt(age);
        } catch (Exception ex) {
        }
        fn = fn.isBlank() ? "Nick" : fn;
        ln = ln.isBlank() ? "Samoylov" : ln;
        Controller02.goToPage2(new User(a, fn, ln));

        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
