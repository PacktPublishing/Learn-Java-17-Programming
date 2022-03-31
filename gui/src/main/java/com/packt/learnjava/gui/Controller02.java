package com.packt.learnjava.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Controller02 {
    @FXML
    public Text textHello;

    public static void goToPage2(User user) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String file = classLoader.getResource("fxml" + File.separator + "page02.fxml").getFile();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new URL("file:" + file));
            Scene scene = loader.load();

            Controller02 c = loader.getController();
            String hello = "Hello, " + user.getFirstName() + " " + user.getLastName() + ", age " + user.getAge() + "!";
            c.textHello.setText(hello);

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Second page of GUI App");
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
