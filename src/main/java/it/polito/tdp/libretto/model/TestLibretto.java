package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	Libretto lib;  // accessibile a tutti
	
	private void run() {
		
		this.lib= new Libretto(); // creato libretto vuoto
		
		Voto v1= new Voto("Tecniche di Programazione", 30 , LocalDate.of(2020, 06, 27));
		Voto v2= new Voto("Analisi II", 28 , LocalDate.of(2020, 06, 15));
	
		
		lib.add(v1);
		lib.add(v2);
		lib.add(new Voto ("Economia", 25, LocalDate.of(2019, 07, 03)));
		
		//System.out.println(this.lib); sbagliatissimo!! Va definito il metodo toString() dei vari oggetti
		System.out.println(this.lib.toString()); 
		
		System.out.println(this.lib.stampaVotiUguali(25)); 
		
		System.out.println(this.lib.estraiVotiUguali(25)); // chiama in automatico il toString per stamparlo
		
		
		
		
	}
	
	// non lavoro dentro al main, ma creo un oggetto della classe stessa
	// perche' essendo static e' limitato
	public static void main(String[] args) {
		
		TestLibretto test= new TestLibretto(); 
        test.run(); 
		
	}

}
