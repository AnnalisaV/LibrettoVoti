package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * Classe Voto, contiene le info su un esame superato
 * @author HP
 *
 */
public class Voto {

	private String nomeCorso; 
	private int voto; 
	private LocalDate data; // classe Date e' predefinita in java.util ma ha molti problemi
	                        // preferito LocalDate di java.time disponibile dalla versione java7 in avanti
	
	
	/**
	 * Costruisco un nuovo voto
	 * @param nomeCorso nome del corso superato
	 * @param voto      valore del voto acquisito
	 * @param data      data di superamento dell'esame 
	 */
	public Voto(String nomeCorso, int voto, LocalDate data) {
		super();
		this.nomeCorso = nomeCorso;
		this.voto = voto;
		this.data = data;
	}


	public String getNomeCorso() {
		return nomeCorso;
	}


	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}


	public int getVoto() {
		return voto;
	}


	public void setVoto(int voto) {
		this.voto = voto;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	@Override
	public String toString() {
		//return "Voto [nomeCorso=" + nomeCorso + ", voto=" + voto + ", data=" + data + "]";
		return nomeCorso + " " + voto + " (" + data + ")";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCorso == null) ? 0 : nomeCorso.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (nomeCorso == null) {
			if (other.nomeCorso != null)
				return false;
		} else if (!nomeCorso.equals(other.nomeCorso)) // questa e' la parte chiave 
			return false;
		return true;
	}


	


	
	
	
	
	
}
