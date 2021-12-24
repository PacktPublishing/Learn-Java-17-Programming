package com.packt.learnjava.ch12_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class BlendEffect extends Application {
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

    // Currently, the uncommented start() method demonstrates
    // all the modes of the Blend effect.
    // So, run the application as-is if you would like to see the demo.

    //Set effect/mode on the node
    public void start1(Stage primaryStage) {
        try {
            BlendMode bm1 = BlendMode.MULTIPLY;
            BlendMode bm2 = BlendMode.SRC_OVER;
            Node[] node = setEffectOnGroup(bm1, bm2);
            //Node[] node = setModeOnGroup(bm1, bm2);
            //Node[] node = setEffectOnCircle(bm1, bm2);
            //Node[] node = setEffectOnSquare(bm1, bm2);
            //Node[] node = setModeOnCircle(bm1, bm2);
            //Node[] node = setModeOnSquare(bm1, bm2);

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(40);
            grid.setVgap(15);
            grid.setPadding(new Insets(20, 20, 20, 20));

            int i = 0;
            grid.addRow(i++, new Text("Circle top"), new Text("Square top"));
            grid.add(node[0],    0, i++, 2, 1);
            GridPane.setHalignment(node[0], HPos.CENTER);
            grid.addRow(i++, node[1], node[2]);
            grid.add(node[3],    0, i++, 2, 1);
            GridPane.setHalignment(node[3], HPos.CENTER);
            grid.addRow(i++, node[4], node[5]);
            Text txt = new Text("Circle opacity - 0.5\nSquare opacity - 1.0");
            grid.add(txt,    0, i++, 2, 1);
            GridPane.setHalignment(txt, HPos.CENTER);

            Scene scene = new Scene(grid, 350, 350);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX blend effect");
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // Set mode on the pane
    public void start2(Stage primaryStage) {
        try {
            BlendMode bm = BlendMode.MULTIPLY;
            //BlendMode bm = BlendMode.SRC_OVER;

            Text txt1 = new Text(bm.name());
            txt1.setX(100);
            txt1.setY(35);

            Text txt2 = new Text("Circle top");
            txt2.setX(70);
            txt2.setY(55);

            Text txt3 = new Text("Square top");
            txt3.setX(160);
            txt3.setY(55);

            Text txt4 = new Text("Circle opacity - 0.5\nSquare opacity - 1.0");
            txt4.setX(70);
            txt4.setY(185);

            Circle c1 = createCircle(80, 95);
            Rectangle s1 = createSquare(80, 95);

            Circle c2 = createCircle(180, 95);
            Rectangle s2 = createSquare(180, 95);

            Pane pane = new Pane();
            pane.setPadding(new Insets(20, 20, 20, 20));
            pane.getChildren().addAll(txt1,txt2,txt3,s1,c1,c2,s2,txt4);
            pane.setBlendMode(bm);

            Scene scene = new Scene(pane, 300, 250);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX blend effect");
            primaryStage.onCloseRequestProperty()
                    .setValue(e -> System.out.println("Bye! See you later!"));
            primaryStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // ReactiveSystemDemo all modes
    public void start(Stage primaryStage) {
        try {
            Text txt1 = new Text("Circle is top input");
            Text txt2 = new Text();
            Text txt3 = new Text();
            Text txt4 = new Text();
            Text txt5 = new Text("Circle opacity - 0.5\nSquare opacity - 1.0");

            Circle c1 = createCircle();
            Rectangle s1 = createSquare();
            Group g1 = new Group(s1, c1);

            Circle c2 = createCircle();
            Rectangle s2 = createSquare();
            Group g2 = new Group(s2, c2);

            AllBlendEffectsThread bet = new AllBlendEffectsThread(txt2, txt3, txt4, g1, g2);

            Button btnP = new Button("Pause");
            btnP.setOnAction(e1 -> bet.pause());
            btnP.setStyle("-fx-background-color: lightpink;");

            Button btnC = new Button("Continue");
            btnC.setOnAction(e2 -> bet.cont());
            btnC.setStyle("-fx-background-color: lightgreen;");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(40);
            grid.setVgap(15);
            grid.setPadding(new Insets(0, 20, 20, 20));

            int i = 0;
            grid.add(txt1,    0, i++, 2, 1);
            GridPane.setHalignment(txt1, HPos.CENTER);
            grid.add(txt2,    0, i++, 2, 1);
            GridPane.setHalignment(txt2, HPos.CENTER);
            grid.addRow(i++, txt3, txt4);
            GridPane.setHalignment(txt3, HPos.CENTER);
            GridPane.setHalignment(txt4, HPos.CENTER);
            grid.addRow(i++, g1, g2);
            GridPane.setHalignment(g1, HPos.CENTER);
            GridPane.setHalignment(g2, HPos.CENTER);
            grid.add(txt5,    0, i++, 2, 1);
            GridPane.setHalignment(txt5, HPos.CENTER);
            HBox hb = new HBox(btnP, btnC);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(35);
            grid.add(hb,    0, i++, 2, 1);
            GridPane.setHalignment(hb, HPos.CENTER);

            Scene scene = new Scene(grid, 350, 350);
            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX blend effect");
            primaryStage.onCloseRequestProperty()
                    .setValue(e3 -> System.out.println("Bye! See you later!"));
            primaryStage.show();

            bet.start();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static class AllBlendEffectsThread extends Thread {
        private String[] effects = { "ADD,DIFFERENCE", "COLOR_BURN,COLOR_DODGE", "LIGHTEN,DARKEN",
                "EXCLUSION,OVERLAY", "SCREEN,OVERLAY", "SOFT_LIGHT,HARD_LIGHT", "BLUE,GREEN",
                "BLUE,RED", "MULTIPLY,SRC_OVER", "SRC_ATOP,SRC_OVER" };
        private boolean pause;
        private Text txt1;
        private Text txt2;
        private Text txt3;
        private Group g1;
        private Group g2;

        AllBlendEffectsThread(Text txt1, Text txt2, Text txt3, Group g1, Group g2){
            setDaemon(true);
            this.txt1 = txt1;
            this.txt2 = txt2;
            this.txt3 = txt3;
            this.g1 = g1;
            this.g2 = g2;
        }
        public void pause(){
            this.pause = true;
        }
        public void cont(){
            this.pause = false;
        }

        @Override
        public void run(){
            try {
                for(String effect: effects){
                    for(int i = 0; i < 11; i++){
                        double opacity = Math.round(i * 0.1 * 10.0) / 10.0;
                        System.out.println(effect + " = " + opacity);
                        String[] e = effect.split(",");
                        txt1.setText("The blend mode opacity: " + opacity);
                        txt2.setText(e[0]);
                        txt3.setText(e[1]);

                        Blend b1 = new Blend();
                        BlendMode bm1 = Enum.valueOf(BlendMode.class, e[0]);
                        b1.setMode(bm1);
                        b1.setOpacity(opacity);
                        g1.setEffect(b1);

                        Blend b2 = new Blend();
                        BlendMode bm2 = Enum.valueOf(BlendMode.class, e[1]);
                        b2.setMode(bm2);
                        b2.setOpacity(opacity);
                        g2.setEffect(b2);

                        TimeUnit.SECONDS.sleep(1);
                        if(pause){
                            while(true){
                                TimeUnit.SECONDS.sleep(1);
                                if(!pause){
                                    break;
                                }
                            }
                        }
                    }
                }
                Platform.exit();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    static Node[] setEffectOnGroup(BlendMode bm1, BlendMode bm2){
        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Blend blnd1 = new Blend();
        blnd1.setMode(bm1);

        Node c1 = createCircle();
        Node s1 = createSquare();
        Node g1 = new Group(s1, c1);
        g1.setEffect(blnd1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        Node g2 = new Group(c2, s2);
        g2.setEffect(blnd1);

        Blend blnd2 = new Blend();
        blnd2.setMode(bm2);

        Node c3 = createCircle();
        Node s3 = createSquare();
        Node g3 = new Group(s3, c3);
        g3.setEffect(blnd2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        Node g4 = new Group(c4, s4);
        g4.setEffect(blnd2);

        Node[] arr = {txt1, g1, g2, txt2, g3, g4 };
        return arr;
    }

    static Node[] setModeOnGroup(BlendMode bm1, BlendMode bm2){
        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Node c1 = createCircle();
        Node s1 = createSquare();
        Node g1 = new Group(s1, c1);
        g1.setBlendMode(bm1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        Node g2 = new Group(c2, s2);
        g2.setBlendMode(bm1);

        Node c3 = createCircle();
        Node s3 = createSquare();
        Node g3 = new Group(s3, c3);
        g3.setBlendMode(bm2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        Node g4 = new Group(c4, s4);
        g4.setBlendMode(bm2);

        Node[] arr = {txt1, g1, g2, txt2, g3, g4 };
        return arr;
    }

    static Node[] setEffectOnCircle(BlendMode bm1, BlendMode bm2){
        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Blend blnd1 = new Blend();
        blnd1.setMode(bm1);

        Node c1 = createCircle();
        Node s1 = createSquare();
        c1.setEffect(blnd1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        c2.setEffect(blnd1);

        Blend blnd2 = new Blend();
        blnd2.setMode(bm2);

        Node c3 = createCircle();
        Node s3 = createSquare();
        c3.setEffect(blnd2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        c4.setEffect(blnd2);

        Node[] arr = {txt1, new Group(s1, c1), new Group(c2, s2), txt2, new Group(s3, c3), new Group(c4, s4) };
        return arr;
    }

    static Node[] setEffectOnSquare(BlendMode bm1, BlendMode bm2){

        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Blend blnd1 = new Blend();
        blnd1.setMode(bm1);

        Node c1 = createCircle();
        Node s1 = createSquare();
        s1.setEffect(blnd1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        s2.setEffect(blnd1);

        Blend blnd2 = new Blend();
        blnd2.setMode(bm2);

        Node c3 = createCircle();
        Node s3 = createSquare();
        s3.setEffect(blnd2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        s4.setEffect(blnd2);

        Node[] arr = {txt1, new Group(s1, c1), new Group(c2, s2), txt2, new Group(s3, c3), new Group(c4, s4) };
        return arr;
    }

    static Node[] setModeOnCircle(BlendMode bm1, BlendMode bm2){
        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Node c1 = createCircle();
        Node s1 = createSquare();
        c1.setBlendMode(bm1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        c2.setBlendMode(bm1);

        Node c3 = createCircle();
        Node s3 = createSquare();
        c3.setBlendMode(bm2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        c4.setBlendMode(bm2);

        Node[] arr = {txt1, new Group(s1, c1), new Group(c2, s2), txt2, new Group(s3, c3), new Group(c4, s4) };
        return arr;
    }

    static Node[] setModeOnSquare(BlendMode bm1, BlendMode bm2){
        Node txt1 = new Text(bm1.name());
        Node txt2 = new Text(bm2.name());

        Node c1 = createCircle();
        Node s1 = createSquare();
        s1.setBlendMode(bm1);

        Node c2 = createCircle();
        Node s2 = createSquare();
        s2.setBlendMode(bm1);

        Node c3 = createCircle();
        Node s3 = createSquare();
        s3.setBlendMode(bm2);

        Node c4 = createCircle();
        Node s4 = createSquare();
        s4.setBlendMode(bm2);

        Node[] arr = {txt1, new Group(s1, c1), new Group(c2, s2), txt2, new Group(s3, c3), new Group(c4, s4) };
        return arr;
    }

    private static Circle createCircle(){
        Circle c = new Circle();
        c.setFill(Color.rgb(255, 0, 0, 0.5));
        c.setRadius(25);
        return c;
    }

    private static Rectangle createSquare(){
        Rectangle r = new Rectangle();
        r.setFill(Color.rgb(0, 0, 255, 1.0));
        r.setWidth(50);
        r.setHeight(50);
        return r;
    }

    private static Circle createCircle(int x, int y){
        Circle c = createCircle();
        c.setCenterX(x);
        c.setCenterY(y);
        return c;
    }

    private static Rectangle createSquare(int x, int y){
        Rectangle r = createSquare();
        r.setX(x);
        r.setY(y);
        return r;
    }


}
