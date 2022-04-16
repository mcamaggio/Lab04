package it.polito.tdp.lab04.model;

public class Studente {
	
	private int matricola;
	private String nome;
	private String cognome;
	private String cds;
	
	public Studente(int matricola) {
		this.matricola = matricola;
	}
	
	public Studente(int matricola, String nome, String cognome, String cds) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCds() {
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	@Override
	public String toString() {
		return cognome + " " + nome;
	}
	
	
	
	

}
