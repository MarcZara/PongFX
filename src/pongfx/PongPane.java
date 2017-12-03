package pongfx;

// @author Marc Zara
import javafx.animation.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.util.Duration;

public class PongPane extends Pane {

    protected final double radius = 20;
    private double x = 350, y = 175;
    private double dx = 1, dy = 1;
    private int playerScore = 0;
    private Boolean collision = false;
    private Circle circle = new Circle(x, y, radius);
    private Rectangle cpu = new Rectangle(60, 175, 40, 100);
    private Rectangle player = new Rectangle(600, 175, 40, 100);

    private Timeline animation;

    private PongPaddleRectangle cpuPaddle = new PongPaddleRectangle(60, 175, 40, 100);
    private PongPaddleRectangle playerPaddle = new PongPaddleRectangle(600, 175, 40, 100);

    protected PongPane() {
       
        circle.setFill(Color.GREEN);

        cpu.setStroke(Color.BLACK);
        cpu.setStrokeWidth(3);
        cpu.setFill(Color.WHEAT);

        player.setStroke(Color.BLACK);
        player.setStrokeWidth(3);
        player.setFill(Color.WHEAT);

        getChildren().add(circle);
        getChildren().add(cpu);
        getChildren().add(player);
        getChildren().addAll(cpuPaddle.getRectangle());
        getChildren().addAll(playerPaddle.getRectangle());

        animation = new Timeline(
                new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setRate(10);
        animation.play();
    }

    protected void play() {
        animation.play();
    }

    protected void pause() {
        if (animation.getStatus() == Animation.Status.valueOf("PAUSED")) {
            play();
        } else {
            animation.pause();
        }
    }

    protected void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.1);
    }

    protected void decreaseSpeed() {
        animation.setRate(
                animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
    }

    protected DoubleProperty rateProperty() {
        return animation.rateProperty();
    }

    protected void moveBall() {

        if (x < radius || x > 700 - radius) {
            dx *= -1; // Change ball move direction
        }
        if (y < radius || y > 350 - radius) {
            dy *= -1; // Change ball move direction
        }

        collisions();

        scoring();

        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    protected void movePlayerUp() {
        if (playerPaddle.getY() > 0 && player.getY() > 0) {
            playerPaddle.moveUp(4);
            player.setY(player.getY() - 4);
        }
    }

    protected void movePlayerDown() {
        if (playerPaddle.getY() < 250 && player.getY() < 250) {
            playerPaddle.moveDown(4);
            player.setY(player.getY() + 4);
        }
    }

    private void scoring() {

        if (circle.getCenterX() > 500 && x < radius || x > 700 - radius) {
            System.out.println("Cpu goal");
            resetBoard();
        }

        if (circle.getCenterX() < 500 && x < radius || x > 700 - radius) {
            System.out.println("Player goal");
            playerScore += 1000;
            resetBoard();
        }
    }

    private void collisions() {
        Shape intersectCpuTop
                = Shape.intersect(circle, cpuPaddle.getLine("Top"));
        Shape intersectCpuLeft
                = Shape.intersect(circle, cpuPaddle.getLine("Left"));
        Shape intersectCpuRight
                = Shape.intersect(circle, cpuPaddle.getLine("Right"));
        Shape intersectCpuBottom
                = Shape.intersect(circle, cpuPaddle.getLine("Bottom"));

        if (intersectCpuTop.getBoundsInLocal().getWidth() != -1) {
            dy *= -1;
        }
        if (intersectCpuLeft.getBoundsInLocal().getWidth() != -1) {
            dx *= -1;
        }
        if (intersectCpuRight.getBoundsInLocal().getWidth() != -1) {
            dx *= -1;
        }
        if (intersectCpuBottom.getBoundsInLocal().getWidth() != -1) {
            dy *= -1;
        }

        Shape intersectPlayerTop
                = Shape.intersect(circle, playerPaddle.getLine("Top"));
        Shape intersectPlayerLeft
                = Shape.intersect(circle, playerPaddle.getLine("Left"));
        Shape intersectPlayerRight
                = Shape.intersect(circle, playerPaddle.getLine("Right"));
        Shape intersectPlayerBottom
                = Shape.intersect(circle, playerPaddle.getLine("Bottom"));

        if (intersectPlayerTop.getBoundsInLocal().getWidth() != -1) {
            playerScore += 100;
            dy *= -1;
        }
        if (intersectPlayerLeft.getBoundsInLocal().getWidth() != -1) {
            playerScore += 100;
            dx *= -1;
        }
        if (intersectPlayerRight.getBoundsInLocal().getWidth() != -1) {
            playerScore += 100;
            dx *= -1;
        }
        if (intersectPlayerBottom.getBoundsInLocal().getWidth() != -1) {
            playerScore += 100;
            dy *= -1;
        }
    }
    
    private void resetBoard(){
           animation.pause();

            x = 350;
            y = 175;

            playerPaddle.setLocation(600, 175, 40, 100);
            player.setY(175);

            cpuPaddle.setLocation(60, 175, 40, 100);
            cpu.setY(175);
            
            try{
            Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("Sleep problem");
            }
            
            animation.play();
    }
    
    protected int getPlayerScore(){
        return playerScore;
    }
}
