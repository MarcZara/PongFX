/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PongFX extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    PongPane pongPane = new PongPane();
 
    pongPane.setOnKeyPressed((KeyEvent e) -> {
        if (null != e.getCode()) switch (e.getCode()) {
            case UP:
                pongPane.movePlayerUp();
                break;
            case DOWN:
                pongPane.movePlayerDown();
                break;
            case SPACE:
                pongPane.pause();
                break;
            default:
                break;
        }
    });

    // Create a scene and place it in the stage
    Scene gameScene = new Scene(pongPane, 700, 350);
    primaryStage.setTitle("PongFX"); // Set the stage title
    primaryStage.setScene(gameScene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    // Must request focus after the primary stage is displayed
    pongPane.requestFocus();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
          launch(args);
  }
}

  