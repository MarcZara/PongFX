
package pongfx;

import javafx.animation.*;
import javafx.animation.Transition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;


// @author Marc Zara

public class ScoreBoard extends GridPane {
    
    private Label scoreDisplay;
    private int playerScore;
    
    protected ScoreBoard(){
        scoreDisplay = new Label("SCORE: " + playerScore);
    }
    
    protected void setPlayerScore(int playerScore){
        this.playerScore = playerScore;
    }
}
