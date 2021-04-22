/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import static java.nio.file.StandardCopyOption.*;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class FXMLFileViewController implements Initializable {
    
    @FXML
    private Button selectImageBtn;
    
    @FXML
    private Button closeAppBtn;
    
    @FXML
    private Button logoutBtn;
    
    @FXML
    private Button getResultsBtn;
       
    @FXML
    private Label imageNameLabel;
    
    @FXML
    private Label noImageSelLabel;
    
    @FXML
    private ImageView fileImage;
    
    private static MiddlewareApp mainInstance;
    
    public FXMLFileViewController(){
        mainInstance = MiddlewareApp.getMainInstance();
        
    }
    
    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
    
    //selects a saved image and displays preview
    public void getSelectedImage (ActionEvent event) {
     
        try{
            File selectedFile = null;
            noImageSelLabel.setOpacity(0);
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.bmp"));
            selectedFile = fc.showOpenDialog(null);
            getMainInstance().setOrigFileLoc(selectedFile.getAbsolutePath());
            getMainInstance().setImageName(selectedFile.getName());
            imageNameLabel.setText(getMainInstance().getImageName());
            Image image = new Image("File:" + getMainInstance().getOrigFileLoc()); 
            fileImage.setImage(image);
            
        }
        catch (Exception e){
                noImageSelLabel.setOpacity(1);
               
        }  
    }
    
    //logs user out and returns to LoginPage
    public void logoutBtnPressed(ActionEvent event) throws IOException, Exception{
        
        //Opens user confirmation Box and returns boolean response
        boolean result = ConfirmationBox.displayConfirmBox("Are you sure you want to log out?");
        if (result == true){
            Parent fileViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene fileView = new Scene(fileViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(fileView);
            window.show();
        }
        else{
            
        }
    }
    
    //closes application
    public void closeApplication (ActionEvent event){
        
        //Opens user confirmation Box and returns boolean response
        boolean result = ConfirmationBox.displayConfirmBox("Are you sure you want to close the program?");
        
        if (result == true){
            // get a handle to the stage
            Stage stage = (Stage) closeAppBtn.getScene().getWindow();
            // do what you have to do
            stage.close();
        }
        else{}
    }
    
    //changes scene to OCRResultsView and sets imageName and imageLocation    
    public void getOCRResults (ActionEvent event) throws IOException, Exception{
        
        if(!getMainInstance().getOrigFileLoc().equalsIgnoreCase("")){
            saveToFile();
            noImageSelLabel.setOpacity(0);
            System.out.println(getMainInstance().getCurrFileLoc());
            System.out.println(getMainInstance().getOrigFileLoc());
            
            //getMainInstance().switchScenes("OCRResultView.fxml");
            
            FXMLLoader newLoader = new FXMLLoader();
            newLoader.setLocation(getClass().getResource("OCRResultView.fxml"));
            Parent ocrView = newLoader.load();
        
            Scene ocrEditView = new Scene(ocrView);
        
            //access OCRResultViewController to call methods
            OCRResultViewController editController = newLoader.getController();
            editController.setImageLocation(getMainInstance().getCurrFileLoc());
            editController.setImageName(getMainInstance().getImageName());
            editController.getOCR();
            editController.setOCRImage();
        
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(ocrEditView);
            window.show();       
        }
        else{
            noImageSelLabel.setOpacity(1);
        }
    }
    
    public void saveToFile() throws IOException {
        Path from = Paths.get(getMainInstance().getOrigFileLoc());
        Path to = Paths.get("images\\" + getMainInstance().getImageName());
        
        try{    
            Files.copy(from, to, REPLACE_EXISTING);
            getMainInstance().setCurrFileLoc("images\\" + getMainInstance().getImageName());
        }
        catch(IOException e){
            System.out.println("Unable to save file");
        }
    }
    
//    /**
//     * @return the imageLocation
//     */
//    public String getImageLocation() {
//        return imageLocation;
//    }
//
//    /**
//     * @param imageLocation the imageLocation to set
//     */
//    public void setImageLocation(String imageLocation) {
//        this.imageLocation = imageLocation;
//    }
//
//    /**
//     * @return the imageName
//     */
//    public String getImageName() {
//        return imageName;
//    }
//
//    /**
//     * @param imageName the imageName to set
//     */
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }

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
