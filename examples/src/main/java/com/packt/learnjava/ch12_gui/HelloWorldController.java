package com.packt.learnjava.ch12_gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

public class HelloWorldController {
    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfAge;

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
        String user = "Hello, " + fn + " " + ln + ", age " + a + "!";
        //System.out.println("\nHello, " + fn + " " + ln + ", age " + a + "!");
        //Platform.exit();

        goToPage2(user);
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void goToPage2(String user)
    {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:src/main/resources/helloWorld2.fxml"));
            Scene scene = loader.load();

            HelloWorldController2 c = loader.getController();
            c.textUser.setText(user);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Simple form example. Page 2.");
            primaryStage.setScene(scene);
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> {
                        System.out.println("\nBye! See you later!");
                        Platform.exit();
                    });
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
