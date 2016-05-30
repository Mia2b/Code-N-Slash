package tech.mia2b.cns.main;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Test extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage theStage) 
    {
    	
    	
        theStage.setTitle( "Timeline Example" );
     
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
     
        
        
        Canvas canvas = new Canvas( 512*2, 512*2 );
        root.getChildren().add( canvas );
        
        ArrayList<String> input = new ArrayList<String>();
      	 
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
 
                    // only add once... prevent duplicates
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });
 
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
     
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image earth = new Image( "/textures/earth.png",100,100,true,true);
     
        
        
        new AnimationTimer()
        {
        	double x = 0;
        	double y = 0;
        	double lastTime = System.nanoTime();
            double nowTime = System.nanoTime();
            public void handle(long currentNanoTime)
            {
            	gc.clearRect(0, 0, theScene.getWidth(), theScene.getHeight());
            	
            	
            	nowTime = System.nanoTime();
                double deltaTime = (nowTime - lastTime) / 1000000000.0; 
                lastTime = nowTime;
                
                if(input.contains("D")){
                	x += 1080*deltaTime;
                }
                if(input.contains("A")){
                	x -= 1080*deltaTime;
                }
                if(input.contains("W")){
                	y -= 1080*deltaTime;
                }
                if(input.contains("S")){
                	y += 1080*deltaTime;
                }
                
                gc.drawImage(earth, x, y);
                
                
                
            }
        }.start();
     
        theStage.show();
    }
}