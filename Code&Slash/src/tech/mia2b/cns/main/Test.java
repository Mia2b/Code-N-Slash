package tech.mia2b.cns.main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
     
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Image earth = new Image( "/textures/earth.png");;
     
        final long startNanoTime = System.nanoTime();
     
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	gc.clearRect(0, 0, theScene.getWidth(), theScene.getHeight());
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
     
                double x = 1 * Math.cos(t);
                double y = 1 * Math.sin(t);
     
                // background image clears canvas
                for(int i = 0; i < 100 ; i++){
                	ImageView imageView = new ImageView();
                	imageView.setImage(earth);
                	imageView.setPreserveRatio(true);
                	imageView.setFitHeight(500);
                	imageView.setFitWidth(500);
                	gc.drawImage( imageView.getImage(), x*i, y*i );
                }
                
                
            }
        }.start();
     
        theStage.show();
    }
}