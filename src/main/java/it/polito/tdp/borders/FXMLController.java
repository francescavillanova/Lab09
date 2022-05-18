
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.control.ComboBox;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> cmbStati;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
        txtResult.clear();
        
        try {
    		int anno = Integer.parseInt(txtAnno.getText()); 
    		if(anno<1816 && anno>2016) {
    			txtResult.setText("Inserire un anno compreso tra 1816 e 2016");
    			return;
    		} else {
    			model.createGraph(anno);
            	
    			for(Country c: model.stampaStati().keySet()) {
    				txtResult.appendText(c.toString()+" Numero confini: "+ model.stampaStati().get(c)+"\n");
    			}
    			
    			txtResult.appendText("Numero componenti connesse: "+model.getComponenteConnessa());

    		}
        	
    		
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire un numero intero che indica l'anno");
    		
    	}
    	
    	
    }
    
    
    @FXML
    void doStatiRaggiungibili(ActionEvent event) {
    	 txtResult.clear();
    	 Set<Country> confini;
    	 
    	 Country stato=cmbStati.getValue();
    	 confini=model.getConfini(stato);
    	 
    	 
    	 txtResult.appendText("Paesi raggiungibili a partire da "+stato+":\n");
    	 for(Country c: confini) {
    		 txtResult.appendText(c.toStringDue());
    	 }
  
    	 
    	 

    }

    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	cmbStati.getItems().addAll(model.getMappaPaesi().values());
    }
}
