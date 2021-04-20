/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import middleware.app.MiddlewareApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class DecryptionResultsController implements Initializable {

    @FXML
    private Button closeAppBtn;
    
    @FXML
    private Button logoutBtn;
        
    @FXML
    private Button newDecryptBtn;
    
    private static MiddlewareApp mainInstance;
    
    public DecryptionResultsController(){
        mainInstance = MiddlewareApp.getMainInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    // logs user out and changes to LoginPage scene
    public void logoutBtnPressed(ActionEvent event) throws IOException, Exception{
        //getMainInstance().switchScenes("LoginPage.fxml");
        
        Parent fileViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();    
    }
    
    // closes application
    public void closeApplication (ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        // do what you have to do
        stage.close();   
    }
    
    
    
    // changes scene to OCRResultView
    public void changeToOCRView(ActionEvent event) throws IOException, Exception{
        //getMainInstance().switchScenes("OCRResultView.fxml");
    
       FXMLLoader newLoader = new FXMLLoader();
            newLoader.setLocation(getClass().getResource("OCRResultView.fxml"));
            Parent ocrView = newLoader.load();
        
            Scene ocrEditView = new Scene(ocrView);
        
            //access OCRResultViewController to call methods
            OCRResultViewController editController = newLoader.getController();           
            editController.getOCR();
            editController.setOCRImage();
        
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(ocrEditView);
            window.show();       
    }
    
    // starts new decryption process and changes to FileView for new image selection
    public void startNewDecryption(ActionEvent event) throws IOException, Exception{
        //getMainInstance().switchScenes("FXMLFileView.fxml");
        Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();    
    }
    
     /**
     * @return the mainInstance
     */
    public static MiddlewareApp getMainInstance() {
        return mainInstance;
    }

    /**
     * @param mainInstance the mainInstance to set
     */
    public void setMainInstance(MiddlewareApp mainInstance) {
        this.mainInstance = mainInstance;
    }
    
}
