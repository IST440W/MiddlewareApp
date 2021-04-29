/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jr110
 */
public class ConfirmationBox {
    
    private static boolean result;
    
    public static boolean displayConfirmBox(String boxMessage){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirmation");
        window.setMinWidth(400);
        window.setMinHeight(200);
        Label message = new Label();
        message.setText(boxMessage);
        
        Button yesButton = new Button("Yes");
        yesButton.setMinWidth(80);
        Button noButton = new Button("No");
        noButton.setMinWidth(80);
        
        //set result to true when clicked
        yesButton.setOnAction(e -> {
            setResult(true);
            window.close();
            System.out.println(isResult());
        });
        
        //set result to false when clicked
        noButton.setOnAction(e -> {
            setResult(false);
            window.close();
            System.out.println(isResult());
        });
       
        //create HBox and add buttons for horizontal display
        HBox buttonLayout = new HBox(50);
        buttonLayout.getChildren().addAll(yesButton, noButton);
      
        buttonLayout.setAlignment(Pos.CENTER);
        VBox layout = new VBox(50);
        layout.getChildren().addAll(message,buttonLayout);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return isResult();  
        
    }

    /**
     * @return the result
     */
    public static boolean isResult() {
        return result;
    }

    /**
     * @param aResult the result to set
     */
    public static void setResult(boolean aResult) {
        result = aResult;
    }
    
}
