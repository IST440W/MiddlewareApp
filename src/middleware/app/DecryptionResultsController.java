/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.BufferedReader;
import middleware.app.MiddlewareApp;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.net.www.http.HttpClient;

/**
 * FXML Controller class
 *
 * @author jr110, Kelley Rafferty, Kurtis Miles
 */
public class DecryptionResultsController implements Initializable {

    @FXML
    private Button closeAppBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button newDecryptBtn;
    
    @FXML
    private TextArea decryptionDisplay1;
    
    @FXML
    private TextArea decryptionDisplay2;
    
    @FXML
    private TextArea decryptionDisplay3;

    private static MiddlewareApp mainInstance;

    public DecryptionResultsController() {
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
    public void logoutBtnPressed(ActionEvent event) throws IOException, Exception {
        //getMainInstance().switchScenes("LoginPage.fxml");

        Parent fileViewParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();
    }

    // closes application
    public void closeApplication(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) closeAppBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    // changes scene to OCRResultView
    public void changeToOCRView(ActionEvent event) throws IOException, Exception {
        //getMainInstance().switchScenes("OCRResultView.fxml");

        FXMLLoader newLoader = new FXMLLoader();
        newLoader.setLocation(getClass().getResource("OCRResultView.fxml"));
        Parent ocrView = newLoader.load();

        Scene ocrEditView = new Scene(ocrView);

        //access OCRResultViewController to call methods
        OCRResultViewController editController = newLoader.getController();
        editController.getOCR();
        editController.setOCRImage();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ocrEditView);
        window.show();
    }

    // starts new decryption process and changes to FileView for new image selection
    public void startNewDecryption(ActionEvent event) throws IOException, Exception {
        //getMainInstance().switchScenes("FXMLFileView.fxml");
        Parent fileViewParent = FXMLLoader.load(getClass().getResource("FXMLFileView.fxml"));
        Scene fileView = new Scene(fileViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fileView);
        window.show();
    }

    //To send cipher decryption request to Cipher.tools for decryption.
    public void runCiphertext() {
        
        //Variables.
        int key = 8;
        String cipherType = "caesar";
        String ocrString = mainInstance.getOcrResult();
        ocrString.replace("\n", "").replace("\r", "");
        String cipherQuery;
         
        try {
            
            //Create connection to send query.
            URL endpoint = new URL("https://cipher.tools");
            String endpointString = endpoint.toString();
            cipherQuery = "/api/v1/decode" + "?" + "cipher=" + cipherType + "&key=" + key + "&ciphertext=" + ocrString;
            String urlString = endpoint + cipherQuery;
            URL myURL = new URL(urlString); 
            URLConnection myURLConnection = myURL.openConnection();
            //System.out.println(urlString);
            myURLConnection.connect();
            //System.out.println("Connecting....");
            
            
            //Read response.
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine;
            String decryptResult = "";
            
            while ((inputLine = in.readLine()) != null) 
            
            //Display decrypted result.    
            decryptResult = inputLine.replace("{", "");
            decryptResult = decryptResult.replace("\"", "");
            decryptResult = decryptResult.replace("plaintext:", "");
            decryptResult = decryptResult.replace("}", "");
            decryptionDisplay1.setText(decryptResult);
            System.out.println(decryptResult);
            
            
            in.close();
        } 
        
        catch (MalformedURLException e) { 

        } 
        catch (IOException e) {   

        }
        
    }
    
    public void runLanguageFrench() {
        
        String tempOCRString = mainInstance.getOcrResult().replaceAll("\n", " ");
        
        try {
            sendPost(tempOCRString, "fr" , "https://libretranslate.com/translate");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void runLanguageSpanish() {
        
        String tempOCRString = mainInstance.getOcrResult().replaceAll("\n", " ");
        
        try {
            sendPost(tempOCRString, "es", "https://libretranslate.com/translate");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    private void sendPost(String sendMessage, String sourceLanguage, String destinationURL) throws Exception {

        Map<String, String> arguments = new HashMap<>();
        arguments.put("q", sendMessage);
        arguments.put("source", sourceLanguage);
        arguments.put("target", "en");// This is a fake password obviously
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        byte[] output = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = output.length;

        // Create new URL with local server address
        URL url = new URL("https://libretranslate.com/translate");
        
        // Create new HttpURLConnection and send POST
        try {
            
            URLConnection con = url.openConnection();
            HttpURLConnection connection = (HttpURLConnection) con;
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(length);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            connection.connect();
            try ( OutputStream os = connection.getOutputStream()) {
                os.write(output);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Post Response Message: " + responseCode);
            if (responseCode == 200) {
                String response = getResponse(connection);
                if (sourceLanguage == "fr")
                {
                    decryptionDisplay2.setText(response.toString());
                }
                else {decryptionDisplay3.setText(response.toString());}
                System.out.println("Post Response Message: " + response.toString());
            } else {
                if(sourceLanguage == "fr"){decryptionDisplay2.setText("Bad Response Code: " + responseCode);}
                else {decryptionDisplay3.setText("Bad Response Code: " + responseCode);}
                System.out.println("Bad Response Code: " + responseCode);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private String getResponse(java.net.HttpURLConnection connection) {
        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
                response.append("\n");
            }
            br.close();
            return response.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
    /*
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
