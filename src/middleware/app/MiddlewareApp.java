/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author jr110
 */
public class MiddlewareApp extends Application {
    
    private static MiddlewareApp mainInstance;
    private String username = "";
    private String password = "";
    private String imageName = "";
    private String origFileLoc = "";
    private String currFileLoc = "";
    private String ocrResult = "";
    private String decrypt1 = "";
    private String decrypt2 = "";
    private String lang1 = "";
    private String lang2 = "";
    private Stage pStage;
    private String pageDest;
    
    public MiddlewareApp(){
        mainInstance = this;
        
    }
        
         
        @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
//    public void switchScenes(ActionEvent event) throws Exception {
//         
//       Parent root = FXMLLoader.load(getClass().getResource(fxml_file));
//       getpStage().setScene(new Scene(root, 800, 600));
//       getpStage().show();
//    }
//    

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
            launch(args);
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the origFileLoc
     */
    public String getOrigFileLoc() {
        return origFileLoc;
    }

    /**
     * @param origFileLoc the origFileLoc to set
     */
    public void setOrigFileLoc(String origFileLoc) {
        this.origFileLoc = origFileLoc;
    }

    /**
     * @return the currFileLoc
     */
    public String getCurrFileLoc() {
        return currFileLoc;
    }

    /**
     * @param currFileLoc the currFileLoc to set
     */
    public void setCurrFileLoc(String currFileLoc) {
        this.currFileLoc = currFileLoc;
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
     * @return the decrypt1
     */
    public String getDecrypt1() {
        return decrypt1;
    }

    /**
     * @param decrypt1 the decrypt1 to set
     */
    public void setDecrypt1(String decrypt1) {
        this.decrypt1 = decrypt1;
    }

    /**
     * @return the decrypt2
     */
    public String getDecrypt2() {
        return decrypt2;
    }

    /**
     * @param decrypt2 the decrypt2 to set
     */
    public void setDecrypt2(String decrypt2) {
        this.decrypt2 = decrypt2;
    }

    /**
     * @return the lang1
     */
    public String getLang1() {
        return lang1;
    }

    /**
     * @param lang1 the lang1 to set
     */
    public void setLang1(String lang1) {
        this.lang1 = lang1;
    }

    /**
     * @return the lang2
     */
    public String getLang2() {
        return lang2;
    }

    /**
     * @param lang2 the lang2 to set
     */
    public void setLang2(String lang2) {
        this.lang2 = lang2;
    }

    /**
     * @return the pStage
     */
    public Stage getpStage() {
        return pStage;
    }

    /**
     * @param pStage the pStage to set
     */
    public void setpStage(Stage pStage) {
        this.pStage = pStage;
    }

    /**
     * @return the pageDest
     */
    public String getPageDest() {
        return pageDest;
    }

    /**
     * @param pageDest the pageDest to set
     */
    public void setPageDest(String pageDest) {
        this.pageDest = pageDest;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
        
    }

