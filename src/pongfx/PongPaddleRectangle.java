
package pongfx;

import javafx.scene.shape.*;
import javafx.beans.property.*;

// @author Marc Zara
public class PongPaddleRectangle {

    private IntegerProperty topLeftX = new SimpleIntegerProperty();
    private IntegerProperty topLeftY = new SimpleIntegerProperty();
    private IntegerProperty bottomLeftX = new SimpleIntegerProperty();
    private IntegerProperty bottomLeftY = new SimpleIntegerProperty();
    private IntegerProperty bottomRightX = new SimpleIntegerProperty();
    private IntegerProperty bottomRightY = new SimpleIntegerProperty();
    private IntegerProperty topRightX = new SimpleIntegerProperty();
    private IntegerProperty topRightY = new SimpleIntegerProperty();

    private Line leftSide, rightSide, topSide, bottomSide;

    public PongPaddleRectangle(int x, int y, int width, int height) {

        topLeftX.set(x);
        topLeftY.set(y);
        bottomLeftX.set(topLeftX.get());
        bottomLeftY.set(topLeftY.get() + height);
        bottomRightX.set(bottomLeftX.get() + width);
        bottomRightY.set(bottomLeftY.get());
        topRightX.set(bottomRightX.get());
        topRightY.set(topLeftY.get());

        leftSide = new Line(topLeftX.get(), topLeftY.get(),
                bottomLeftX.get(), bottomLeftY.get());
        bottomSide = new Line(bottomLeftX.get() - 1, bottomLeftY.get(),
                bottomRightX.get() - 1, bottomRightY.get());
        rightSide = new Line(bottomRightX.get(), bottomRightY.get(),
                topRightX.get(), topRightY.get());
        topSide = new Line(topRightX.get() - 1, topRightY.get(),
                topLeftX.get() - 1, topLeftY.get()); 
    }

    public void increaseXY(int x, int y) {
        topLeftX.add(x);
        topLeftY.add(y);
    }
    
    public void increaseX(int x){
        leftSide.setStartX(topLeftX.get() + x);
    }
    
    public void increaseY(int y){
        topLeftY.set(topLeftY.get() + y);
        bottomLeftY.set(bottomLeftY.get() + y);
        leftSide.setStartY(topLeftY.get());
        leftSide.setEndY(bottomLeftY.get());

    }
    
    public void decreaseY(int y){
        topLeftY.set(topLeftY.get() - y);
        bottomLeftY.set(bottomLeftY.get() - y);
        leftSide.setStartY(topLeftY.get());
        leftSide.setEndY(bottomLeftY.get());

    }
    
    public int getY(){
        return topLeftY.get();
    }
    
    public int getX(){
        return topLeftX.get();
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


