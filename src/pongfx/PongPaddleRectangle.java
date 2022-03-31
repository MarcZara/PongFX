
package pongfx;

import javafx.scene.shape.*;

// @author Marc Zara
public class PongPaddleRectangle {

    private int topLeftX, topLeftY, bottomLeftX, bottomLeftY,bottomRightX,
            bottomRightY, topRightX, topRightY;

    private Line leftSide, rightSide, topSide, bottomSide;

    protected PongPaddleRectangle(int x, int y, int width, int height) {

        topLeftX = x;
        topLeftY = y;
        bottomLeftX = topLeftX;
        bottomLeftY = topLeftY + height;
        bottomRightX = bottomLeftX + width;
        bottomRightY = bottomLeftY;
        topRightX = bottomRightX;
        topRightY = topLeftY;

        leftSide = new Line(topLeftX, topLeftY,
                bottomLeftX, bottomLeftY);
        bottomSide = new Line(bottomLeftX , bottomLeftY,
                bottomRightX, bottomRightY);
        rightSide = new Line(bottomRightX, bottomRightY,
                topRightX, topRightY);
        topSide = new Line(topRightX, topRightY,
                topLeftX, topLeftY); 
    }
    
    protected void moveDown(int y){
        
        topLeftY = topLeftY + y;
        topRightY = topRightY + y;
        bottomLeftY = bottomLeftY + y;
        bottomRightY = bottomRightY + y;
        
        leftSide.setStartY(topLeftY);
        leftSide.setEndY(bottomLeftY);
        bottomSide.setStartY(bottomLeftY);
        bottomSide.setEndY(bottomRightY);
        rightSide.setStartY(bottomRightY);
        rightSide.setEndY(topRightY);
        topSide.setStartY(topRightY);
        topSide.setEndY(topLeftY);
    }
    
    protected void moveUp(int y){
        topLeftY = topLeftY - y;
        topRightY = topRightY - y;
        bottomLeftY = bottomLeftY - y;
        bottomRightY = bottomRightY - y;
        
        leftSide.setStartY(topLeftY);
        leftSide.setEndY(bottomLeftY);
        bottomSide.setStartY(bottomLeftY);
        bottomSide.setEndY(bottomRightY);
        rightSide.setStartY(bottomRightY);
        rightSide.setEndY(topRightY);
        topSide.setStartY(topRightY);
        topSide.setEndY(topLeftY);
    }
    
    protected void setLocation(int x, int y, int width, int height){
        topLeftX = x;
        topLeftY = y;
        bottomLeftX = topLeftX;
        bottomLeftY = topLeftY + height;
        bottomRightX = bottomLeftX + width;
        bottomRightY = bottomLeftY;
        topRightX = bottomRightX;
        topRightY = topLeftY;
        
        leftSide.setStartY(topLeftY);
        leftSide.setEndY(bottomLeftY);
        bottomSide.setStartY(bottomLeftY);
        bottomSide.setEndY(bottomRightY);
        rightSide.setStartY(bottomRightY);
        rightSide.setEndY(topRightY);
        topSide.setStartY(topRightY);
        topSide.setEndY(topLeftY);
    }
    
    protected int getY(){
        return topLeftY;
    }
    
    protected int getX(){
        return topLeftX;
    }

    protected Line getLine(String side) {
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
    protected Line[] getRectangle(){
        return new Line[]{leftSide, rightSide, topSide, bottomSide};
    }
}


