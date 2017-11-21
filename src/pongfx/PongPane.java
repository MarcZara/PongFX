package pongfx;

// @author Marc Zara
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class PongPane extends Pane {

    public final double radius = 20;
    private double x = 350, y = 175;
    private double dx = 1, dy = 1;
    private Boolean collision = false;
    private Circle circle = new Circle(x, y, radius);
    private Rectangle cpu = new Rectangle(60, 175, 40, 100);
    private Rectangle player = new Rectangle(600, 175, 40, 100);
    private Timeline animation;

   

    public PongPane() {
        circle.setFill(Color.GREEN); 

        cpu.setStroke(Color.BLACK);
        cpu.setFill(Color.WHEAT);

        player.setStroke(Color.BLACK);
        player.setFill(Color.WHEAT);

        getChildren().add(circle);
        getChildren().add(cpu);
        getChildren().add(player);

    
        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setRate(8);
        animation.play(); 
    }

    public void play() {
        animation.play();
    }

    public void pause() {
        if (animation.getStatus() == Animation.Status.valueOf("PAUSED")) {
            play();
        } else {
            animation.pause();
        }
    }

    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    public DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {
        if (x < radius || x > getWidth() - radius) {
            dx *= -1; // Change ball move direction
        }
        if (y < radius || y > getHeight() - radius) {
            dy *= -1; // Change ball move direction
        }
        
        Shape intersectCPU = Shape.intersect(circle, cpu);
        if (intersectCPU.getBoundsInLocal().getWidth() != -1 &&
                circle.getCenterY() > cpu.getY()){
            dx *= -1;
        }
        else if(intersectCPU.getBoundsInLocal().getHeight() != -1){
            dy *= -1;
        }
        
        Shape intersectPlayer = Shape.intersect(circle, player);        
        if (intersectPlayer.getBoundsInLocal().getWidth() != -1 &&
                circle.getCenterY() > player.getY()){
            dx *= -1;           
        }
        else if(intersectPlayer.getBoundsInLocal().getHeight() != -1){
            dy *= -1;
        }
        
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
    
    protected void movePlayerUp() {
        if (player.getY() > 0) {
            player.setY(player.getY() - 4);
        }
    }
    
    protected void movePlayerDown() {
        if (player.getY() < 250) {
            player.setY(player.getY() + 4);
        }
    }
}
