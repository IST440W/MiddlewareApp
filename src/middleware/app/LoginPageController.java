/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.IOException;
import middleware.app.MiddlewareApp;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jr110
 */
public class LoginPageController implements Initializable {
   
    @FXML
    private Button closeAppBtn;
    
    @FXML
    private Button loginAppBtn;
    
    @FXML
    private TextField usernameTextField;
    
    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private Label loginErrorLabel;
    
    private static MiddlewareApp mainInstance;
    
    public LoginPageController(){
        mainInstance = MiddlewareApp.getMainInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    }

    @FXML
    public void authenticate(ActionEvent event) throws IOException {
        getMainInstance().setUsername(usernameTextField.getText());
        getMainInstance().setPassword(passwordTextField.getText());
        
        if (getMainInstance().getUsername().equals("testing") && getMainInstance().getUsername().equals("testing"))
        {
            Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
            Scene fileView = new Scene(fileViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(fileView);
            window.show();
            loginErrorLabel.setOpacity(0);
        }
        
        loginErrorLabel.setOpacity(1);
    }
    
    public void loginConfirmed(ActionEvent event) throws IOException
    {
       
    }
    
    @FXML
    public void closeApplication (ActionEvent event){
        // get a handle to the stage
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        // do what you have to do
        stage.close();   
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
