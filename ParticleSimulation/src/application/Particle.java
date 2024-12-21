package application;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Particle {
    private Circle particle;  
    
    private double vel;
    // Constructor to initialize the particle (circle)
    public Particle(double centerX, double centerY, double radius, Color color) {
        particle = new Circle(centerX, centerY, radius);
        particle.setFill(color);
        
        vel = 0;
    }

    // Setters
    public void setCenterX(double centerX) {
        particle.setCenterX(centerX);
    }

    public void setCenterY(double centerY) {
        particle.setCenterY(centerY);
    }

    public void setRadius(double radius) {
        particle.setRadius(radius);
    }

    public void setColor(Color color) {
        particle.setFill(color);
    }
    
    // Getters
    public double getCenterX() {
        return particle.getCenterX();
    }

    public double getCenterY() {
        return particle.getCenterY();
    }

    public double getRadius() {
        return particle.getRadius();
    }

    public Color getColor() {
        return (Color) particle.getFill(); // Cast since getFill returns a Paint, and we want to return Color
    }
    
    public Circle getParticle() {
        return particle;
    }

    // Logic
    public void updateGravity() {
    	if (this.getCenterY()> Constants.screenHight) {
    		this.setCenterY(Constants.screenHight);
    		return;
    	}
    	
    	this.vel = Constants.gravity * Constants.timeStep;
    	this.setCenterY(this.getCenterY() + this.vel * Constants.timeStep);
    }
}