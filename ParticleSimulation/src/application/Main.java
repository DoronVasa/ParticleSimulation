package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Main extends Application {
	Random random = new Random();
	
    Particle[] particleArray = new Particle[Constants.particleCount];
    
	@Override
    public void start(Stage primaryStage) {
    	Pane root = new Pane();
    	
    	for (int i = 0; i < Constants.particleCount; i++) {
    		particleArray[i] = new Particle(random.nextInt(Constants.screenWidth), random.nextInt(101), 5, Color.GREEN);
    		root.getChildren().add(particleArray[i].getParticle());
    	}
    	
    	Scene scene = new Scene(root, Constants.screenWidth, Constants.screenHight);
    	    	
    	// Main update loop
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(Constants.timeStep), e -> {
            	
            	// Apply gravity to particles
            	for (Particle particle : particleArray) {
            		particle.updateGravity();
            	}
            
            	// Collision check for particles
            	for (int i = 0; i < particleArray.length; i++) {
            	    for (int j = i + 1; j < particleArray.length; j++) {
            	        Particle circle1 = particleArray[i];
            	        Particle circle2 = particleArray[j];
            	        
            	        double dx = circle1.getCenterX() - circle2.getCenterX();
            	        double dy = circle1.getCenterY() - circle2.getCenterY();
            	        double distance = Math.sqrt(dx * dx + dy * dy);
            	        double overlap = circle1.getRadius() + circle2.getRadius() - distance;
            	        
            	        if (overlap > 0) {  // There's an overlap
            	        	circle1.setColor(Color.RED);
            	            circle2.setColor(Color.RED);
            	        	
            	            // Normalize the direction vector
            	            double nx = dx / distance;
            	            double ny = dy / distance;

            	            // Calculate the amount to move each circle
            	            double moveDistance = overlap / 2;

            	            // Move the circles apart by the move distance
            	            circle1.setCenterX(circle1.getCenterX() + nx * moveDistance);
            	            circle1.setCenterY(circle1.getCenterY() + ny * moveDistance);

            	            circle2.setCenterX(circle2.getCenterX() - nx * moveDistance);
            	            circle2.setCenterY(circle2.getCenterY() - ny * moveDistance);
            	        }
            	    }
            	}

            })
        );
        
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the animation

        // Set up the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Gravity Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}