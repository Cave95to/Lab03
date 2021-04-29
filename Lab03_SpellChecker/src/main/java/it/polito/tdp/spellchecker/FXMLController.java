package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dizionario;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguages;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtErrori;

    @FXML
    private Label lblNumErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	
    	this.txtErrori.clear();
    	this.txtTesto.clear();
    	this.lblNumErrori.setText(null);
    	this.lblTempo.setText(null);

    }
    
    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	List<String> paroleInserite = new ArrayList<>();
    	List<RichWord> paroleRiccheSbagliate = new ArrayList<>();
    	//List<String> paroleSbagliate = new ArrayList<>();
    	
    	this.txtErrori.clear();
    	this.lblNumErrori.setText(null);
    	this.lblTempo.setText(null);
    	
    	String lingua = this.boxLanguages.getValue();
    	
    	if (lingua == null) {
    		this.txtErrori.setText("Selezionare una lingua");
    		return;
    	}
    	
    	this.dizionario.loadDictionary(lingua);
    	
    	String inserimento = this.txtTesto.getText().toLowerCase();
    	
    	inserimento = inserimento.replaceAll("[.,\\/#?!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	
    	//LO DIVIDO CON GLI SPAZI
    	
    	StringTokenizer st = new StringTokenizer(inserimento, " ");
    	
    	// Controllo su String Tokenizer che non siano inseriti solo spazi ad esempio o sia vuoto
    	// ovvero che abbia elementi
    	
    	if (!st.hasMoreElements()) {
    		this.txtErrori.setText("Inserire un testo da controllare");
    		return;
    	}
    	
    	String parola;
    	
    	while (st.hasMoreElements()) {
    		
    		parola = st.nextToken();
    		paroleInserite.add(parola);
    		
    	}
 
    	paroleRiccheSbagliate = this.dizionario.spellCheckText(paroleInserite);
    	
    	/* NON SERVE SALVARE LE PAROLE SBAGLIATE POSSIAMO STAMPARE SUBITO
    	
    	for (RichWord r : paroleRicche) {
    		if (!r.isCorretta())
    			paroleSbagliate.add(r.getParola());
    	}
    	if (paroleSbagliate.size()!=0) {
    		String s = "";
    		for (String str : paroleSbagliate)
    			s = s + str +"\n";
    		this.txtErrori.setText(s);
    	}
    	if (paroleSbagliate.size()== 1) {
    		this.lblNumErrori.setText("trovato 1 errore");
    		return;
    	}
 */ 	
    	
    	//int numErrori = 0;

		for (RichWord r : paroleRiccheSbagliate) {
			//if (!r.isCorretta()) {
			//	numErrori++;
				this.txtErrori.appendText(r.getParola() + "\n");
			//}
		}
    	
    	this.lblNumErrori.setText("errori trovati: "+ paroleRiccheSbagliate.size());
    }
    
    public void setDictionary(Dictionary d) {
    	this.dizionario = d;
    	this.boxLanguages.getItems().addAll("Italian","English");
    }
    
    @FXML
    void initialize() {
        assert boxLanguages != null : "fx:id=\"boxLanguages\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblNumErrori != null : "fx:id=\"lblNumErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

