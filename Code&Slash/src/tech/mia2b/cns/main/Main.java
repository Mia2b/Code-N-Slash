package tech.mia2b.cns.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tech.mia2b.cns.entities.player.FirstPlayer;
import tech.mia2b.cns.world.Control;
import tech.mia2b.cns.world.Entities;
import tech.mia2b.cns.world.Input;

public class Main extends Application {

	Globals globals = JsePlatform.standardGlobals();
	LuaValue chunk = globals.load("print 'hello, world'");

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setMinWidth(640);
		primaryStage.setMinHeight(480);
		primaryStage.setWidth(1280);
		primaryStage.setHeight(720);
		primaryStage.setTitle("Code & Slash");
		Scene codeWindow = codeWindow(primaryStage);
		Scene gameWindow = gameWindow(primaryStage);
		
		Entities.addEntity(new FirstPlayer());
		primaryStage.setScene(gameWindow);
		primaryStage.show();
		
		
	}

	private Scene gameWindow(Stage primaryStage) {
		Group root = new Group();
		Scene gameScene = new Scene(root, Color.color(0.2,0.2, 0.25, 1));
		
		Canvas canvas = new Canvas(gameScene.getWidth(), gameScene.getHeight());

		root.getChildren().add(canvas);

		gameScene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				Control.setViewWidth(newSceneWidth.intValue());
			}
		});

		gameScene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				Control.setViewHeight(newSceneHeight.intValue());
			}
		});

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				Input.addKey(e);
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				Input.removeKey(e);
			}
		});
		
		Control.setGraphicsContext(canvas.getGraphicsContext2D());
		new AnimationTimer() {

			double lastTime = System.nanoTime();
			double nowTime = System.nanoTime();

			public void handle(long currentNanoTime) {

				lastTime = nowTime;
				nowTime = System.nanoTime();
				double deltaTime = (nowTime - lastTime) / 1000000000.0;

				Control.update(deltaTime);
				Control.render();

			}
		}.start();
		
		return gameScene;
	}

	private Scene codeWindow(Stage primaryStage) {

		String filePath = "F:\\res\\Testing.lua";

		Group root = new Group();
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight(), Color.color(0.2,0.2, 0.25, 1));

		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(16));

		Button btnSave = new Button("Save");
		Button btnLoad = new Button("Load");
		Button btnRun = new Button("Run");
		Button btnImport = new Button("Import");

		btnSave.setMaxWidth(Double.MAX_VALUE);
		btnLoad.setMaxWidth(Double.MAX_VALUE);
		btnRun.setMaxWidth(Double.MAX_VALUE);

		VBox vbButtons = new VBox();
		vbButtons.setSpacing(8);
		vbButtons.setPadding(new Insets(16, 0, 0, scene.getWidth() - 64));
		vbButtons.getChildren().addAll(btnSave, btnLoad,btnRun, btnImport);

		final TextArea textBox = new TextArea();
		textBox.setPrefWidth(scene.getWidth() - 96);
		textBox.setPrefHeight(scene.getHeight() - 32);
		GridPane.setHalignment(textBox, HPos.CENTER);
		gridpane.add(textBox, 0, 1);

		btnImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File file = fileChooser.showOpenDialog(primaryStage);
				textBox.setText(Util.readFile(file));
			}
		});
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Util.writeFile(filePath,textBox.getText());
			}
		});

		btnLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textBox.setText(Util.readFile(filePath));

			}
		});

		btnRun.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					chunk = globals.load(textBox.getText());
					chunk.call();
				} catch (Exception e) {

				}
			}
		});

		scene.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth,
					Number newSceneWidth) {
				vbButtons.setPadding(new Insets(16, 0, 0, newSceneWidth.intValue() - 64));
				textBox.setPrefWidth(newSceneWidth.intValue() - 96);
			}
		});

		scene.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight,
					Number newSceneHeight) {
				textBox.setPrefHeight(newSceneHeight.intValue() - 32);
			}
		});

		root.getChildren().addAll(vbButtons, gridpane);
		return scene;
	}
	
	
}