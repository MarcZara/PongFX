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
    
    private PongPaddleRectangle cpuPaddle = new PongPaddleRectangle(60, 175, 40, 100);
    private PongPaddleRectangle playerPaddle = new PongPaddleRectangle(600, 175, 40, 100);
   

    public PongPane() {
        circle.setFill(Color.GREEN); 

        cpu.setStroke(Color.BLACK);
        cpu.setFill(Color.WHEAT);

        player.setStroke(Color.BLACK);
        player.setFill(Color.WHEAT);

        getChildren().add(circle);
        getChildren().add(cpu);
        getChildren().add(player);
        getChildren().addAll(cpuPaddle.getRectangle());
        getChildren().addAll(playerPaddle.getRectangle());

    
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
        
        Shape intersectCpuTop = Shape.intersect(circle, cpuPaddle.getLine("Top"));
        Shape intersectCpuLeft = Shape.intersect(circle, cpuPaddle.getLine("Left"));
        Shape intersectCpuRight= Shape.intersect(circle, cpuPaddle.getLine("Right"));
        Shape intersectCpuBottom = Shape.intersect(circle, cpuPaddle.getLine("Bottom"));
        
        if (intersectCpuTop.getBoundsInLocal().getWidth() != -1){
            dy *= -1;
        }
        if (intersectCpuLeft.getBoundsInLocal().getWidth() != -1){
            dx *= -1;
        }
        if (intersectCpuRight.getBoundsInLocal().getWidth() != -1){
            dx *= -1;
        }
        if (intersectCpuBottom.getBoundsInLocal().getWidth() != -1){
            dy *= -1;
        }

        Shape intersectPlayerTop =
                Shape.intersect(circle, playerPaddle.getLine("Top"));
        Shape intersectPlayerLeft =
                Shape.intersect(circle, playerPaddle.getLine("Left"));
        Shape intersectPlayerRight=
                Shape.intersect(circle, playerPaddle.getLine("Right"));
        Shape intersectPlayerBottom =
                Shape.intersect(circle, playerPaddle.getLine("Bottom"));
        
        if (intersectPlayerTop.getBoundsInLocal().getWidth() != -1){
            dy *= -1;
        }
        if (intersectPlayerLeft.getBoundsInLocal().getWidth() != -1){
            dx *= -1;
        }
        if (intersectPlayerRight.getBoundsInLocal().getWidth() != -1){
            dx *= -1;
        }
        if (intersectPlayerBottom.getBoundsInLocal().getWidth() != -1){
            dy *= -1;
        }
        
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
    
    protected void movePlayerUp() {
        if (playerPaddle.getY() > 0) {
            playerPaddle.decreaseY(4);
        }
    }
    
    protected void movePlayerDown() {
        if (playerPaddle.getY() < 250) {
            playerPaddle.increaseY(4);
        }
    }
}
