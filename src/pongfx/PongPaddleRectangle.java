
package pongfx;

import javafx.scene.shape.*;
import javafx.beans.property.*;

// @author Marc Zara
public class PongPaddleRectangle {

    private IntegerProperty topLeftX = new SimpleIntegerProperty();
    private IntegerProperty topLeftY = new SimpleIntegerProperty();
    private IntegerProperty bottomRightX = new SimpleIntegerProperty();
    private IntegerProperty bottomRightY = new SimpleIntegerProperty();
    private IntegerProperty topRightX = new SimpleIntegerProperty();
    private IntegerProperty bottomLeftY = new SimpleIntegerProperty();
    private IntegerProperty bottomLeftX = new SimpleIntegerProperty();
    private IntegerProperty topRightY = new SimpleIntegerProperty();
    private Line leftSide, rightSide, topSide, bottomSide;

    public PongPaddleRectangle(int x, int y, int width, int height) {

        topLeftX.setValue(x);
        topLeftY.setValue(y);
        bottomLeftX.setValue(topLeftX.getValue());
        bottomLeftY.setValue(topLeftY.getValue() + height);
        bottomRightX.setValue(bottomLeftX.getValue() + width);
        bottomRightY.setValue(bottomLeftY.getValue());
        topRightX.setValue(bottomRightX.getValue());
        topRightY.setValue(topLeftY.getValue());

        leftSide = new Line(topLeftX.getValue(), topLeftY.getValue(),
                bottomLeftX.getValue(), bottomLeftY.getValue());
        bottomSide = new Line(bottomLeftX.getValue() - 1, bottomLeftY.getValue(),
                bottomRightX.getValue() - 1, bottomRightY.getValue());
        rightSide = new Line(bottomRightX.getValue(), bottomRightY.getValue(),
                topRightX.getValue(), topRightY.getValue());
        topSide = new Line(topRightX.getValue() - 1, topRightY.getValue(),
                topLeftX.getValue() - 1, topLeftY.getValue());

        topLeftX.bind(bottomLeftX);
        topLeftY.bind(bottomLeftY);
    }

    public void increaseXY(int x, int y) {
        topLeftX.add(x);
        topLeftY.add(y);
    }
    
    public void increaseX(int x){
        topLeftX.add(x);
    }
    
    public void increaseY(int y){
        topLeftY.add(y);
        bottomLeftY.add(y);
        leftSide.setStartY(topLeftY.getValue());
        leftSide.setEndY(bottomLeftY.getValue());
    }
    
    public void decreaseY(int y){
        topLeftY.subtract(y);
        bottomLeftY.subtract(y);
        leftSide.setStartY(topLeftY.getValue());
        leftSide.setEndY(bottomLeftY.getValue());
    }
    
    public int getY(){
        return topLeftY.getValue();
    }
    
    public int getX(){
        return topLeftX.getValue();
    }

    public Line getLine(String side) {
        switch (side) {
            case "Top":
                return topSide;
            case "Left":
                return leftSide;
            case "Right":
                return rightSide;
            case "Bottom":
                return bottomSide;
        }
        return null;
    }
    public Line[] getRectangle(){
        return new Line[]{leftSide, rightSide, topSide, bottomSide};
    }
}


