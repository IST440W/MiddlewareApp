/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.IOException;
import middleware.app.MiddlewareApp;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        
        Connection c = null;
        Statement statement = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/accounts",
                    "postgres", "lam144");
            System.out.println("Opened database successfully");

            statement = c.createStatement();

            //------------------------------------
            //Statement used to create USERS table
            //------------------------------------
//         String sqlCommand = "CREATE TABLE USERS" + "(USERNAME TEXT PRIMARY KEY     NOT NULL, "
//                                + " PASSWORD    TEXT    NOT NULL)";
//         statement.executeUpdate(sqlCommand);


            //---------------------------------------------- 
            //Statement used to insert data into USERS table
            //----------------------------------------------
//            String sqlCommand = "INSERT INTO USERS(USERNAME,PASSWORD) " +
//                "VALUES ('AgentMiles', 'testing');";
//            statement.executeUpdate(sqlCommand);


            //----------------------------------------------
            //ResultSet used to SELECT data from USERS table
            //----------------------------------------------
            ResultSet rs = statement.executeQuery("SELECT * FROM USERS;");
            
            System.out.println("Selecting all rows from USERS table");
            
            //Print data from rs ResultSet
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                
                System.out.println(getMainInstance().getUsername() + " " + getMainInstance().getPassword());
                System.out.println(username + " " + password);

                if (getMainInstance().getUsername().equals(username.toString()) && getMainInstance().getPassword().equals(password.toString()))
                {

                    Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
                    Scene fileView = new Scene(fileViewParent);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(fileView);
                    window.show();
                    loginErrorLabel.setOpacity(0);
                    break;
                }
            }

            //close rs, statement, c
            rs.close();
            statement.close();
            c.close();

            System.out.println("Executed successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
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
