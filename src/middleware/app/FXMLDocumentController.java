/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class FXMLDocumentController {
    String imageLocation = "";
    String ocrResults = "";

    @FXML
    private Button selectImageBtn;
    
    @FXML
    private Button closeAppBtn;
    
    @FXML
    private Button editResultsBtn;
    
    @FXML
    private Button beginDecryptBtn;
    
    @FXML
    private TextArea ocrTextResult;
    
    public void getSelectedImage (ActionEvent event){
        ocrTextResult.setText("");
        File selectedFile = null;
        FileChooser fc = new FileChooser();
        
        fc.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.bmp"));
        selectedFile = fc.showOpenDialog(null);
        imageLocation = selectedFile.getAbsolutePath();
        
        if (selectedFile != null){
            ocrResults = getOCR(imageLocation);            
        }
        else {
            
        }
        
    }
    
    public String getOCR(String location){
        
        Tesseract tesseract = new Tesseract();
        String result = "";
            try{ 
                // the path of your tess data folder 
                // inside the extracted file  
                tesseract.setDatapath("languages\\tessdata");
                
                // path of your image file 
               result = tesseract.doOCR(new File(location));
               ocrTextResult.setText(result);
                //ocrTextResult.setText(tesseract.doOCR(new File(location)));              
            }
        
            catch (TesseractException e) { 
                e.printStackTrace(); 
            } 
        return result;   
    }
    
    public void closeApplication (ActionEvent event){
       
    }
    
    public void beginDecryption (ActionEvent event){
        
    }
    
    public void editOCRResults (ActionEvent event) throws IOException{
        FXMLLoader newLoader = new FXMLLoader();
        newLoader.setLocation(getClass().getResource("EditOCRResult.fxml"));
        Parent ocrView = newLoader.load();
        
        Scene ocrEditView = new Scene(ocrView);
        
        //access EditOCRResult to call methods
        EditOCRResultController editController = newLoader.getController();
        editController.setOCRImage(imageLocation);
        editController.setOCRResult(ocrResults);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(ocrEditView);
        window.show();
        
        
    }
    
}
