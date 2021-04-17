/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import javafx.scene.image.Image ;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class EditOCRResultController implements Initializable {
    
    Image image;
    @FXML
    private TextArea ocrTextArea;
     
    @FXML
    private ImageView ocrImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    
    public void setOCRImage (String imageLocation) throws MalformedURLException{
        //URL imageSource = new URL("File:images//" + imageName);
        //String location = ("images//" +imageName);
        image = new Image("File:" + imageLocation); 
        ocrImage.setImage(image);
    }
    
    public void setOCRResult (String ocrResult){
        ocrTextArea.setText(ocrResult);
    }
    
    public String getNewOCRText(){
        String text = ocrTextArea.getText();
        return text;
    }
}
