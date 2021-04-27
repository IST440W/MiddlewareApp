/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import java.net.MalformedURLException;
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

    public OCRResultViewController() {
        mainInstance = MiddlewareApp.getMainInstance();

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //run Google Tesseract and sets String ocrResult with text returned  
    public void getOCR() {

        Tesseract tesseract = new Tesseract();
        try {
            // the path of tess data folder 
            // inside the root folder  
            tesseract.setDatapath("languages\\tessdata");

            // path of your image file
            getMainInstance().setOcrResult(tesseract.doOCR(new File(getMainInstance().getCurrFileLoc())));
            //setOcrResult(tesseract.doOCR(new File(getImageLocation())));
            ocrTextArea.setText(getMainInstance().getOcrResult());
            //System.out.println(getOcrResult());

        } catch (TesseractException e) {
            ocrTextArea.setText("Unable to get OCR results at this time.");
        }
        //return result;   
    }

    //updates ocrResult String with user changes to Tesseract result
    public void editOCRResults() {
        
        //Opens user confirmation Box and returns boolean response
        boolean result = ConfirmationBox.displayConfirmBox("Are you sure you want to update the result?");
        
        if (result == true){
            getMainInstance().setOcrResult(ocrTextArea.getText());
            System.out.println(getMainInstance().getOcrResult());
        }
        else{}
    }

    //sets the preview image of file selected
    public void setOCRImage() throws MalformedURLException {

        setImage(new Image("File:" + getMainInstance().getCurrFileLoc()));
        ocrImage.setImage(getImage());
    }

    // changes scene to FileView
    public void changeToFileView(ActionEvent event) throws IOException, Exception {

        Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();
    }

    //  Logs user out and returns to LoginPage
    public void logoutBtnPressed(ActionEvent event) throws IOException, Exception {
        
        //Opens user confirmation Box and returns boolean response
        boolean result = ConfirmationBox.displayConfirmBox("Are you sure you want to log out?");
        
        if (result == true){
            Parent fileViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene fileView = new Scene(fileViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(fileView);
            window.show();
        }
        else{}
    }

    // closes application
    public void closeApplication(ActionEvent event) {
        
        //Opens user confirmation Box and returns boolean response
        boolean result = ConfirmationBox.displayConfirmBox("Are you sure you want to close the program?");
        
        if (result == true){
            // locates stage to close
            Stage stage = (Stage) closeAppBtn.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
        else{}
    }

    // sends ocrResult String to decryption controller and changes scene
    public void beginDecryption(ActionEvent event) throws IOException, Exception {
        
        FXMLLoader newLoader = new FXMLLoader();
        newLoader.setLocation(getClass().getResource("DecryptionResults.fxml"));
        Parent decryptionView = newLoader.load();

        Scene decryptionEditView = new Scene(decryptionView);

        //access DecryptionResultsController to call methods
        DecryptionResultsController editController = newLoader.getController();
        //editController.runCiphertext();
        editController.runLanguageFrench();
        //editController.runLanguageSpanish();
        

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(decryptionEditView);
        window.show();

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
