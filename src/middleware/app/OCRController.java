/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author jr110
 */
public class OCRController  {
    
    private String resultText ="";

    public OCRController()throws FileNotFoundException, IOException, ClassNotFoundException{
    }
    
    public String getOCR(){
        
        Tesseract tesseract = new Tesseract(); 
            try{ 
                // the path of your tess data folder               
                tesseract.setDatapath("languages\\tessdata");
                
                // path of your image file 
                setResultText(tesseract.doOCR(new File("images\\test-ocr.jpg")));              
            }
        
            catch (TesseractException e) { 
                e.printStackTrace(); 
            } 
        return getResultText();
    }
    
    public void createImageData(){
        //code to create image data
    }

    /**
     * @return the resultText
     */
    public String getResultText() {
        return resultText;
    }

    /**
     * @param resultText the resultText to set
     */
    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
