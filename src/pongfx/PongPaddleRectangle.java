
package pongfx;

import javafx.scene.shape.*;
import javafx.beans.property.*;

// @author Marc Zara

public class PongPaddleRectangle {
    private IntegerProperty topLeftX, topLeftY, bottomLeftX, bottomLeftY;
    private IntegerProperty bottomRightX, bottomRightY, topRightX, topRightY;
    private Line leftSide, rightSide, topSide, bottomSide;
    
    public PongPaddleRectangle(int x, int y, int width, int height){
        
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
        bottomSide = new Line(bottomLeftX.getValue(), bottomLeftY.getValue(),
                bottomRightX.getValue(), bottomRightY.getValue());
        rightSide = new Line(bottomRightX.getValue(), bottomRightY.getValue(),
                topRightX.getValue(), topRightY.getValue());
        topSide = new Line(topRightX.getValue(), topRightY.getValue(),
                topLeftX.getValue(), topLeftY.getValue());
        
        topLeftX.bind(bottomLeftX);
        topLeftY.bind(bottomLeftY);
    }
    public void setTopLeftXY (int x, int y){
        topLeftX.add(x);
        topLeftY.add(y);
    }
}

