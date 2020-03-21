package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	Libretto lib;  // accessibile a tutti
	
	private void run() {
		
		this.lib= new Libretto(); // creato libretto vuoto
		
		//punto1. Inserire alcuni voti
		Voto v1= new Voto("Tecniche di Programazione", 30 , LocalDate.of(2020, 06, 27));
		Voto v2= new Voto("Analisi II", 28 , LocalDate.of(2020, 06, 15));
	
		
		lib.add(v1);
		lib.add(v2);
		if(lib.add(new Voto ("Economia", 25, LocalDate.of(2019, 07, 03)))==false)
			System.out.println("Errore di inserimento di Economia");
		
		//System.out.println(this.lib); sbagliatissimo!! Va definito il metodo toString() dei vari oggetti
		System.out.println(this.lib.toString()); 
		
		//punto2. Stampa tutti i voti uguali a 25
		System.out.println(this.lib.stampaVotiUguali(25)); 
		
		System.out.println(this.lib.estraiVotiUguali(25)); // chiama in automatico il toString per stamparlo
		
		//punto3. Cerca un esame superato, conoscendo il nome del corso
		String nomeCorso="Analisi II"; 
		//int voto=lib.cercaNomeCorso(nomeCorso); non sarebbe conveniente perche' un domani
		                                         // potrei aver bisogno della data o altre info
		Voto votoAnalisiII= lib.cercaNomeCorso(nomeCorso); 
		System.out.println("Il voto di "+votoAnalisiII.getNomeCorso()+" e' "+votoAnalisiII.getVoto()); 
		Voto mancante= lib.cercaNomeCorso("Fisica I"); 
		
		//System.out.println("Il voto di "+mancante.getNomeCorso()+" e' "+mancante.getVoto()); // da' una NUllPointerException
		
		//punto4 e 5. Verifica voti duplicati o voti in conflitto
		Voto economia2= new Voto("Economia", 25, LocalDate.of(2019, 07, 03)); 
		Voto economia3= new Voto("Economia", 21, LocalDate.of(2019, 07, 03));
		
		System.out.println("Economia con 25 e' duplicato: "+lib.isDuplicato(economia2)+
				"/ conflitto: "+lib.isConflitto(economia2)+"\n"); 
		System.out.println("Economia con 21 e' duplicato: "+lib.isDuplicato(economia3)+
				"/ conflitto: "+lib.isConflitto(economia3)+"\n"); 
		
		//punto7. Migliora il libretto 
		Libretto migliorato= lib.creaLibrettoMigliorato(); 
		System.out.println("Miglioramento del libretto\n"); 
		System.out.println(lib); //prima
		System.out.println(migliorato); //dopo 
		
	}
	
	// non lavoro dentro al main, ma creo un oggetto della classe stessa
	// perche' essendo static e' limitato
	public static void main(String[] args) {
		
		TestLibretto test= new TestLibretto(); 
        test.run(); 
		
	}

}
