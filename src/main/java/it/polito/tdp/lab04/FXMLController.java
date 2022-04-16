package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private Button btnCercaNome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextArea txtElenco;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    void doCercaCorso(ActionEvent event) {
    	
    	txtElenco.clear();
    	
    	try {
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		Studente studente = model.getStudente(matricola);
    		
    		if(studente == null) {
    			txtMatricola.appendText("Nessun risultato: matricola inesistente");
    			return;
    		}
    		
    		List<Corso> corsi = model.corsiFromStudente(studente);
    		StringBuilder sb = new StringBuilder();
    		
    		for(Corso corso : corsi) {
    			sb.append(String.format("%-8s ", corso.getCodins()));
    			sb.append(String.format("%-4s ", corso.getCrediti()));
    			sb.append(String.format("%-45s ", corso.getNome()));
    			sb.append(String.format("%-4s ", corso.getPd()));
    			sb.append("\n");
    		}
    		
    		txtElenco.appendText(sb.toString());
    	
    	} catch (NumberFormatException e) {
    		txtElenco.setText("Inserire una matricola nel formato corretto");
    	} catch (RuntimeException e) {
    		txtElenco.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    	}
    	 
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtElenco.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	
    	try {
	    	Corso corso = cmbCorsi.getValue();
	    	if(corso == null) {
	    		txtElenco.setText("Selezionare un corso.");
	    		return;
	    	}
	    	
	    	List<Studente> studenti = model.studentiIscrittiAlCorso(corso);
	    	StringBuilder sb = new StringBuilder();
	    	
	    	for(Studente studente : studenti) {
	    		sb.append(String.format("%-10s ", studente.getMatricola()));
	    		sb.append(String.format("%-20s ", studente.getCognome()));
	    		sb.append(String.format("%-20s ", studente.getNome()));
	    		sb.append(String.format("%-10s ", studente.getCds()));
	    		sb.append("\n");
	    	}
	    	
	    	txtElenco.appendText(sb.toString());
    	
    	} catch (RuntimeException e) {
    		txtElenco.setText("ERRORE DI CONNESSIONE AL DATABASE!");
    	}
    	
    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	
    	txtElenco.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try {	
	    	int matricola = Integer.parseInt(txtMatricola.getText());
	    	Studente studente = model.getStudente(matricola);
	    	
	    	if(studente == null) {
	    		txtElenco.setText("Nessun risultato: matricola inesistente");
	    		return;
	    	}
	    	
	    	txtNome.setText(studente.getNome());
	    	txtCognome.setText(studente.getCognome());
	    
    	} catch (NumberFormatException e) {
    		txtElenco.setText("Inserire una matricola nel formato corretto");
	    } catch (RuntimeException e) {
			txtElenco.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtElenco.clear();
    	
    	try {
	    	int matricola = Integer.parseInt(txtMatricola.getText());
	    	Studente studente = model.getStudente(matricola);
	    	
	    	Corso corso = cmbCorsi.getValue();
	    	
	    	if(studente == null) {
	    		txtElenco.appendText("Nessun risultato: matricola inesistente");
	    		return;
	    	}
	    	if(corso == null) {
	    		txtElenco.appendText("Selezionare un corso.");
	    		return;
	    	}
	    	
	    	txtNome.setText(studente.getNome());
	    	txtCognome.setText(studente.getCognome());
	    	
	    	if(model.isStudenteIscrittoACorso(studente, corso)) {
	    		txtElenco.appendText("Studente gia iscritto al corso.");
	    		return;
	    	}
	    	
	    	if(!model.inscriviStudenteACorso(studente, corso)) {
	    		txtElenco.appendText("Errore durante l'iscrizione al corso.");
	    		return;
	    	} else {
	    		txtElenco.appendText("Studente iscritto al corso");
	    	}
	    	
	    } catch (NumberFormatException e) {
			txtElenco.setText("Inserire una matricola nel formato corretto");
	    } catch (RuntimeException e) {
			txtElenco.setText("ERRORE DI CONNESSIONE AL DATABASE!");
		}

    }

    @FXML
    void doReset(ActionEvent event) {
    	cmbCorsi.setPromptText("Corsi");
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtElenco.clear();
    	cmbCorsi.getSelectionModel().clearSelection();

    }

    @FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtElenco != null : "fx:id=\"txtElenco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		setComboItems();
		
	}

	private void setComboItems() {
		// Ottieni tutti i corsi dal model
		corsi = model.getTuttiICorsi();
		
		// Aggiungi tutti i corsi alla ComboBox
		Collections.sort(corsi); // Ordine alfabetico per maggior ordine
		cmbCorsi.getItems().addAll(corsi); // richiama il toString dell'oggetto Corso
		
	}

}
