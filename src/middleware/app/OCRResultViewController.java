/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image ;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class OCRResultViewController implements Initializable {
    
    private String imageLocation;
    private String imageName;
    private String ocrResult;
    private Image image;
    private static MiddlewareApp mainInstance;
    
    @FXML
    private TextArea ocrTextArea;
     
    @FXML
    private ImageView ocrImage;
    
    @FXML
    private Button ocrSaveChangesBtn;
    
    @FXML
    private Button beginDecryptBtn;
    
    @FXML
    private Button ocrBackBtn;
    
    @FXML
    private Button closeAppBtn;
    
    @FXML
    private Button ocrLogoutBtn;
    
    public OCRResultViewController(){
        mainInstance = MiddlewareApp.getMainInstance();
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try{
//            getOCR();
//            setOCRImage();
//        }
//        
//        catch (MalformedURLException ex) {
//            Logger.getLogger(OCRResultViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
       
    }
    
    
    //run Google Tesseract and sets String ocrResult with text returned  
    public void getOCR(){
        
        Tesseract tesseract = new Tesseract();
            try{ 
                // the path of tess data folder 
                // inside the root folder  
                tesseract.setDatapath("languages\\tessdata");
                
               // path of your image file
               getMainInstance().setOcrResult(tesseract.doOCR(new File(getMainInstance().getCurrFileLoc())));
               //setOcrResult(tesseract.doOCR(new File(getImageLocation())));
               ocrTextArea.setText(getMainInstance().getOcrResult());
               //System.out.println(getOcrResult());
                            
            }
        
            catch (TesseractException e) { 
                e.printStackTrace(); 
            } 
        //return result;   
    }
    
    //updates ocrResult String with user changes to Tesseract result
    public void editOCRResults(){
        getMainInstance().setOcrResult(ocrTextArea.getText());
        System.out.println(getMainInstance().getOcrResult());
    }
    
    //sets the preview image of file selected
    public void setOCRImage() throws MalformedURLException{
        
        setImage(new Image("File:" + getMainInstance().getCurrFileLoc())); 
        ocrImage.setImage(getImage());
    }
    
    // changes scene to FileView
    public void changeToFileView(ActionEvent event) throws IOException, Exception{
     
        Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();    
    }
    
    //  Logs user out and returns to LoginPage
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
        // locates stage to close
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        // do what you have to do
        stage.close();   
    }
    
    // sends ocrResult String to decryption controller and changes scene
    public void beginDecryption(ActionEvent event) throws IOException, Exception{
        //getMainInstance().switchScenes("DecryptionResults.fxml");
        
        Parent fileViewParent = FXMLLoader.load(getClass().getResource("DecryptionResults.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();
        
    }
    
    //sets ocrTextArea
    public void setOCRResult (String ocrResult){
        ocrTextArea.setText(ocrResult);
    }
    
    // returns text from ocrTextArea
    public String getNewOCRText(){
        String text = ocrTextArea.getText();
        return text;
    }

    /**
     * @return the imageLocation
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * @param imageLocation the imageLocation to set
     */
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    /**
     * @return the ocrResult
     */
    public String getOcrResult() {
        return ocrResult;
    }

    /**
     * @param ocrResult the ocrResult to set
     */
    public void setOcrResult(String ocrResult) {
        this.ocrResult = ocrResult;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
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
