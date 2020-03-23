package it.polito.tdp.libretto.model;

import java.util.Comparator;

public class ComparatoreVotoPerValutazione implements Comparator<Voto> {

	@Override
	public int compare(Voto o1, Voto o2) {
		//decrescente (o2 deve venire prima se il suo voto e' piu' alto)
		return o2.getVoto()-o1.getVoto();
	}

}
