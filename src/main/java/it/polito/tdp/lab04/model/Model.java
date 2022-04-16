package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	public Model() {
		this.studenteDAO = new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}
	
	public List<Corso> corsiFromStudente(Studente studente){
		return studenteDAO.getCorsiFromStudente(studente);
	}
	
	public boolean isStudenteIscrittoACorso(Studente studente, Corso corso) {
		return studenteDAO.isStudenteIscrittoACorso(studente, corso);
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> studentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}

}
