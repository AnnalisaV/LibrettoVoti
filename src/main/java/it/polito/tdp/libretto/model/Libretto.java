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
	 * @return {@code true} se ha inserito con successo il voto 
	 *         {@code false} se non ha inserito perche' il voto era in conflitto o duplicato
	 */
	public boolean add(Voto v) {
		if (this.isConflitto(v) || this.isDuplicato(v)) {
			// non inserire il voto
			// scatenare un'eccezione ma a quel punto sarebbe responsabilita' del 
			// chiamante evrificare che il voto sia corretto altrimenti si blocca il programma
			//oppure segnalo con un valore particolare (cambio da void a boolean il ritorno del metodo)
		return false; // non ha avuto successo 
		}
		else {
			// inserire il voto in quanto non in conflitto ne' duplicato
		this.voti.add(v); 
		return true; 
		}
		
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

	/** 
	 * Dato il nome di un corso, ricerca se quell'esame esiste nel libretto, 
	 * in caso affermativo restituisce l'oggetto {@link Voto} corrispondente.
	 * Se il corso non esiste -> (arbitrario) eccezione/ null.
	 * @param nomeCorso nome esame da cercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		//Voto result= null; 
		/*for (Voto v : this.voti) {
			if(v.getNomeCorso().compareTo(nomeCorso)==0) { //nomeCorso.equals(v.getCorso())
				return v; 
			}
		}
		return null;
		*/ // va bene ma esiste un'implementazione migliore
		
		int pos= this.voti.indexOf(new Voto(nomeCorso, 0, null)); // questo voto creato non
		                                                          // sara' inserito nella 
		                                                         // lista ma usato per 
		                                                        // confronto con il vero voto 
		                                                      //presente in lista sulla base dell'equals
	
		// ottenuta la posizione del Voto con quelle caratteristiche 
		if (pos !=-1) {
			return this.voti.get(pos); 
		}
		else return null; 
	
	} 
	/**
	 * Ritorna {@code true} se il corso specificato da {@code v}
	 * esiste nel libretto con la stessa valutazione.
	 * Se non esiste, o la valutazione e' diversa, ritorna {@code false}. 
	 * 
	 * @param v il {@link Voto} da ricercare 
	 * @return l'esistenza di un duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste= this.cercaNomeCorso(v.getNomeCorso());
		
		if (esiste ==null) 
			// non esiste quindi sicuramente non e' duplicato
			return false; 
		
		//esiste allora vado a vedere che voto ha 
		
		/*if (esiste.getVoto()==v.getVoto())
			return true; 
		else return false; */
		
		return (esiste.getVoto()== v.getVoto()); 
		
	}
	
	/**
	 * Determina se esiste un voto con lo stesso nome corso ma valutazione
	 * diversa. 
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		
		Voto esiste= this.cercaNomeCorso(v.getNomeCorso()); 
		if(esiste==null)
			return false; 
		
		return (esiste.getVoto() != v.getVoto());
		
	}
}
