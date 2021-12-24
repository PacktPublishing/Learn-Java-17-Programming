package com.packt.learnjava.ch12_gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.FloatMap;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.PerspectiveTransform;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.SepiaTone;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

public class OtherEffects extends Application {
    public static void main(String... args) {
        launch(args);
    }
    @Override
    public void stop(){
        System.out.println("Doing what has to be done before closing");
    }

    public void start(Stage primaryStage) {
        try {
            Text txt = new Text();

            FileInputStream inputP = new FileInputStream("src/main/resources/packt.png");
            Image imageP = new Image(inputP);
            ImageView ivP = new ImageView(imageP);

            FileInputStream inputM = new FileInputStream("src/main/resources/mount.jpeg");
            Image imageM = new Image(inputM);
            ImageView ivM = new ImageView(imageM);
            ivM.setPreserveRatio(true);
            ivM.setFitWidth(300);

            EffectsThread et = new EffectsThread(txt, ivM, ivP);

            Button btnP = new Button("Pause");
            btnP.setOnAction(e1 -> et.pause());
            btnP.setStyle("-fx-background-color: lightpink;");

            Button btnC = new Button("Continue");
            btnC.setOnAction(e2 -> et.cont());
            btnC.setStyle("-fx-background-color: lightgreen;");

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setVgap(25);
            grid.setPadding(new Insets(10, 10, 10, 10));

            int i = 0;
            grid.add(txt,    0, i++, 2, 1);
            GridPane.setHalignment(txt, HPos.CENTER);
            grid.add(ivP,    0, i++, 2, 1);
            GridPane.setHalignment(ivP, HPos.CENTER);
            grid.add(ivM,    0, i++, 2, 1);
            GridPane.setHalignment(ivM, HPos.CENTER);
            grid.addRow(i++, new Text());
            HBox hb = new HBox(btnP, btnC);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(25);
            grid.add(hb,    0, i++, 2, 1);
            GridPane.setHalignment(hb, HPos.CENTER);

            Scene scene = new Scene(grid, 450, 500);

            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX effect demo");
            primaryStage.onCloseRequestProperty()
                    .setValue(e3 -> System.out.println("Bye! See you later!"));
            primaryStage.show();

            et.start();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static Effect createEffect(String effect, double d, Text txt){
        switch(effect){
            case "Bloom":
                System.out.println(effect + ".threshold: " + d);
                txt.setText(effect + ".threshold: " + d);
                Bloom b = new Bloom();
                b.setThreshold(d);
                return b;
            case "BoxBlur":
                int i = (int) d * 10;
                int it = i / 3;
                System.out.println(effect + ".iterations: " + it);
                txt.setText(effect + ".iterations: " + it);
                BoxBlur bb = new BoxBlur();
                bb.setIterations(i);
                return bb;
            case "ColorAdjust.contrast":
                double c = Math.round((-1.0 + d * 2) * 10.0) / 10.0;
                System.out.println(effect + ": " + c);
                txt.setText(effect + ": " + c);
                ColorAdjust ca = new ColorAdjust();
                ca.setContrast(c);
                return ca;
            case "ColorAdjust.hue":
                double h = Math.round((-1.0 + d * 2) * 10.0) / 10.0;
                System.out.println(effect + ": " + h);
                txt.setText(effect + ": " + h);
                ColorAdjust ca1 = new ColorAdjust();
                ca1.setHue(h);
                return ca1;
            case "ColorAdjust.brightness":
                double br = Math.round((-1.0 + d * 2)*10.0)/10.0;
                System.out.println(effect + ": " + br);
                txt.setText(effect + ": " + br);
                ColorAdjust ca2 = new ColorAdjust();
                ca2.setBrightness(br);
                return ca2;
            case "ColorAdjust.saturation":
                double st = Math.round((-1.0 + d * 2) * 10.0) / 10.0;
                System.out.println(effect + ": " + st);
                txt.setText(effect + ": " + st);
                ColorAdjust ca3 = new ColorAdjust();
                ca3.setSaturation(st);
                return ca3;
            case "DisplacementMap":
                int w = (int)Math.round(4096 * d);
                int h1 = (int)Math.round(4096 * d);
                System.out.println(effect + ": width: " + w + ", height: " + h1);
                txt.setText(effect + ": " + ": width: " + w + ", height: " + h1);
                DisplacementMap dm = new DisplacementMap();
                FloatMap floatMap = new FloatMap();
                floatMap.setWidth(w);
                floatMap.setHeight(h1);
                for (int k = 0; k < w; k++) {
                    double v = (Math.sin(k / 20.0 * Math.PI) - 0.5) / 40.0;
                    for (int j = 0; j < h1; j++) {
                        floatMap.setSamples(k, j, 0.0f, (float) v);
                    }
                }
                dm.setMapData(floatMap);
                return dm;
            case "DropShadow.radius":
                double rd = Math.round((127.0 * d) * 10.0) / 10.0;
                System.out.println(effect + ": " + rd);
                txt.setText(effect + ": " + rd);
                DropShadow sh = new DropShadow();
                sh.setRadius(rd);
                return sh;
            case "Glow":
                System.out.println(effect + ".level: " + d);
                txt.setText(effect + ".level: " + d);
                return new Glow(d);
            case "InnerShadow":
                double rad = Math.round(12.1 * d *10.0)/10.0;
                double off = Math.round(15.0 * d *10.0)/10.0;
                System.out.println("InnerShadow: radius: " + rad + ", offset: " + off);
                txt.setText("InnerShadow: radius: " + rad + ", offset:" + off);
                InnerShadow is = new InnerShadow();
                is.setColor(Color.web("0x3b596d"));
                is.setOffsetX(off);
                is.setOffsetY(off);
                is.setRadius(rad);
                return is;
            case "Lighting.Distant.azimuth":
                double a = Math.round((-135.0 + 270.0 * d) *10.0)/10.0;
                System.out.println(effect + ": " + a);
                txt.setText(effect + ": " + a);
                Light.Distant light = new Light.Distant();
                light.setAzimuth(a);
                Lighting l = new Lighting();
                l.setLight(light);
                l.setSurfaceScale(5.0);
                return l;
            case "Lighting.Distant.surfaceScale":
                double s = 10.0 * d;
                System.out.println(effect + ": " + s);
                txt.setText(effect + ": " + s);
                Light.Distant light1 = new Light.Distant();
                light1.setAzimuth(-135.0);
                Lighting l1 = new Lighting();
                l1.setLight(light1);
                l1.setSurfaceScale(s);
                return l1;
            case "Lighting.Distant.elevation":
                double e = Math.round(90.0 * d *10.0)/10.0;
                System.out.println(effect + ": " + e);
                txt.setText(effect + ": " + e);
                Light.Distant light2 = new Light.Distant();
                light2.setElevation(e);
                light2.setAzimuth(-135.0);
                Lighting l2 = new Lighting();
                l2.setLight(light2);
                l2.setSurfaceScale(5.0);
                return l2;
            case "Lighting.Spot.specularExponent":
                double sS = Math.round((d * 4)*10.0)/10.0;
                System.out.println(effect + ": " + sS);
                txt.setText(effect + ": " + sS);
                Light.Spot lightSs = new Light.Spot();
                lightSs.setX(150);
                lightSs.setY(100);
                lightSs.setZ(80);
                lightSs.setPointsAtX(0);
                lightSs.setPointsAtY(0);
                lightSs.setPointsAtZ(-50);
                lightSs.setSpecularExponent(sS);
                Lighting lSs = new Lighting();
                lSs.setLight(lightSs);
                lSs.setSurfaceScale(5.0);
                return lSs;
            case "MotionBlur.radius":
                double r = Math.round((63.0 * d)*10.0) / 10.0;
                System.out.println(effect + ": " + r);
                txt.setText(effect + ": " + r);
                MotionBlur mb1 = new MotionBlur();
                mb1.setRadius(r);
                mb1.setAngle(-15);
                return mb1;
            case "PerspectiveTransform":
                System.out.println(effect + ": " + d);
                txt.setText(effect + ": " + d);
                PerspectiveTransform pt =
                        new PerspectiveTransform(0., 1. + 50.*d, 310., 50. - 50.*d,
                                310., 50. + 50.*d + 1., 0., 100. - 50. * d + 2.);
                return pt;
            case "Reflection.fraction":
                System.out.println(effect + ": " + d);
                txt.setText(effect + ": " + d);
                Reflection ref = new Reflection();
                ref.setFraction(d);
                return ref;
            case "ShadowTone.radius":
                double rdS = Math.round((127.0 * d)*10.0)/10.0;
                System.out.println(effect + ": " + rdS);
                txt.setText(effect + ": " + rdS);
                Shadow shd = new Shadow();
                shd.setRadius(rdS);
                return shd;
            case "SepiaTone.level":
                System.out.println(effect + ": " + d);
                txt.setText(effect + ": " + d);
                SepiaTone sep = new SepiaTone();
                sep.setLevel(d);
                return sep;
            default:
                throw new RuntimeException("Effect " + effect + " is not supported");
        }

    }

    private static class EffectsThread extends Thread {
        private String[] effects = {"Bloom", "BoxBlur","ColorAdjust.contrast","ColorAdjust.hue","ColorAdjust.brightness", "ColorAdjust.saturation","DisplacementMap","DropShadow.radius","Glow","InnerShadow","Lighting.Distant.azimuth","Lighting.Distant.surfaceScale","Lighting.Distant.elevation","Lighting.Spot.specularExponent","MotionBlur.radius","PerspectiveTransform","Reflection.fraction","ShadowTone.radius","SepiaTone.level"};

        private boolean pause;
        private ImageView ivM;
        private ImageView ivP;
        private Text txt;

        EffectsThread(Text txt, ImageView ivM, ImageView ivP){
            setDaemon(true);
            this.txt = txt;
            this.ivM = ivM;
            this.ivP = ivP;
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
                        double d = Math.round(i * 0.1 * 10.0) / 10.0;
                        Effect e = createEffect(effect, i, txt);
                        ivM.setEffect(e);
                        ivP.setEffect(e);
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

}
