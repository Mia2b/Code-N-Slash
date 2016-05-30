package tech.mia2b.cns.main;


import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	Globals globals = JsePlatform.standardGlobals();
	LuaValue chunk = globals.load("print 'hello, world'");
	

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Code & Slash");
        Group root = new Group();
        Scene scene = new Scene(root, 640, 360, Color.GREY);
        
        Group root1 = new Group();
        Scene cat = new Scene(root1, 640, 360, Color.GREY);
        
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(16));
        
        
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        Button btnSave = new Button("Save");
        Button btnRun = new Button("Run");
        
        
        
        
        btnSave.setMaxWidth(Double.MAX_VALUE);
        btnRun.setMaxWidth(Double.MAX_VALUE);

        VBox vbButtons = new VBox();
        vbButtons.setSpacing(8);
        vbButtons.setPadding(new Insets(16, 0, 0, scene.getWidth()-64)); 
        vbButtons.getChildren().addAll(btnSave, btnRun);
        
        
        final TextArea textBox = new TextArea();
        textBox.setPrefWidth(scene.getWidth()-96);
        textBox.setPrefHeight(scene.getHeight()-32);
        GridPane.setHalignment(textBox, HPos.CENTER);
        gridpane.add(textBox, 0, 1);
        
        btnRun.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	chunk = globals.load(textBox.getText());
            	try {
            		chunk.call();
				} catch (Exception e) {
				}
            	
            }
        });
        
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	primaryStage.setScene(cat);
            }
        });
        
        root.getChildren().addAll(vbButtons,gridpane);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}