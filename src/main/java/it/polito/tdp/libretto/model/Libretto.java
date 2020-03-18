package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati
 * @author HP
 *
 */
public class Libretto {

	private List<Voto> voti= new ArrayList<>();

	
	/* Non avendo altro da fare che inizializzare la lista, e' poco conveniente
	 * definire un costruttore. L'inizializzazione e' preferita in dichiarazione allora
	 * public Libretto() {
		super();
		this.voti = new ArrayList<>(); //<Voto> e' giusto ma nell'eventualita' di modificare
		                               // il programmma nel futuro, avro' una riga in meno
		                               // da controllare, un errore in meno ecc
	} 
*/
	
	/**
	 * Aggiunge un nuovo voto al libretto
	 * @param v voto da aggiungere
	 */
	public void add(Voto v) {
		this.voti.add(v); 
		
	}
	
   /* Altra possibilita' (poco conveniente perche' troppo dipendente dalla classe Voto)
    * si preferisce usare l'oggetto e non i dati sparsi
    * public void add(String corso, int voto, LocalDate data) {
		
	}
	*/
	
	/**
	 * Come stampare = visualizzare il libretto
	 */
	public String toString() {
		//delego ai voti stessi 
		//return this.voti.toString(); visualizzati come array tra [] in una sola riga 
		
		String s=""; 
		for (Voto v: this.voti) {
			s+= v.toString()+"\n"; 
		}
		return s; 
	}
	
	/**
	 * Dato un libretto, restituisce una stringa nella quale vi sono solamamente i
	 * voti pari al valore passato come parametro
	 * @param voto valore specificato
	 * @return  stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s=""; 
		for (Voto v: this.voti) {
			if (v.getVoto()== voto) {
			s+= v.toString()+"\n"; 
			}
		}
		return s; 
		
	}
	
	/**
	 * Genera un nuovo Libretto a partire da quello esistente, che conterra' 
	 * esclusivamente i voti con votazione pari a quella specificata 
	 * ( lavoro con oggetti non con stringa)
	 * @param voto votazione specificata 
	 * @return nuovo libretto 'ridotto'
	 */
	public Libretto estraiVotiUguali(int voto) {
		
		Libretto nuovo= new Libretto();
		
		for (Voto v : this.voti) {
			if (v.getVoto()== voto) {
				nuovo.add(v);
			}
		}
		return nuovo; 
	}
}
