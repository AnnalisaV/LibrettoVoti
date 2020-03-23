package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
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
	// costruttore base da me definito
	/**
	 * Crea libretto nuovo e vuoto
	 */
	public Libretto() {
		super(); 
	}
	
	// mettendo questo, quello di default si elimina quindi devo provvedere a definire 
	// un costruttore base
	/**
	 * CopyConstructor ("shallow" -> copia superficiale, copia l'oggetto corrente ma 
	 * non quel che e' contenuto in esso che rimane condiviso in quanto non lo devo modificare)
	 * [esiste anche la versione "deep" che fa copia profonda ovvero clona tutto, in
	 * questo caso con un ciclo for per scandire e clonare ogni singolo voto]
	 * @param libretto da copiare
	 */
	public Libretto(Libretto l) {
		super(); 
		this.voti.addAll(l.voti); 
	}
	
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
	
	/**
	 * Restituisce un NUOVO Libretto, migliorando i voti di quello attuale 
	 * @return il nuovo libretto migliorato
	 */
	public Libretto creaLibrettoMigliorato() {
		
		Libretto nuovo= new Libretto(); 
		for(Voto v : this.voti) {
			// Così sto solo 'copiando' ma e' sempre lui con un nome diverso
			//Voto nv= v; // voto nuovo da migliorare, copiato prima da quello del lib oroginale
			
			// per farne uno uguale ma nuovo (richiamando il copyConstructor)
			//Voto nv= new Voto(v); //new Voto(v.getCorso(), v.get...) ok ma scomodo 
			// same
			 Voto nv= v.clone(); 
			
			if (nv.getVoto() >= 24) {
				nv.setVoto(nv.getVoto()+2);
				if (nv.getVoto() >30) // attenzione a non superare il 30
					nv.setVoto(30);
			}
			else if(nv.getVoto() >=18) {
				nv.setVoto(nv.getVoto()+1);
			}
			nuovo.add(nv); 
		}
		return nuovo; 
	}
	
	/**
	 * Riordina i voti presenti nel libretto corrente alfabeticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti);
	}
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ComparatoreVotoPerValutazione());
		//anche this.voti.sort(new ComparatoreVotoPerValutazione()); 
	}
	/**
	 * Elimina dai libretto tutti i voti minori di 24
	 */
	public void cancellaVotiScarsi() {
		//NON POSSO MODIFICARE MENTRE ITERO
		/*for (Voto v : this.voti) {
			if (v.getVoto()<24) {
				this.voti.remove(v); 
			}
		}*/
		List<Voto> daRimuovere= new ArrayList<>();
		for (Voto v : this.voti) {
			if (v.getVoto()<24) {
				daRimuovere.add(v); 
			}
		}
		
		//itero sulla lista da rimuovere che e' diverso perche' rimuovo su un'altra 
		// su cui non sto iterando 
		/*for(Voto v : daRimuovere) {
			this.voti.remove(v); 
		}*/
		// Esiste anche un modo migliore
		this.voti.removeAll(daRimuovere); // operazione insiemistica che pensa lei all'iterazione su daRimuovere
		
		
	}
}
