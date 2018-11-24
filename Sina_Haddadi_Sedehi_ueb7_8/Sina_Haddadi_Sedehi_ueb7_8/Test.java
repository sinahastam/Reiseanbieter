//Sina Haddadi Sedehi
//Matrikelnummer 566440

import transportmittel.*;
import kunden.*;
import reiseanbieter.*;

/**
 * Testklasse
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */
public class Test {

	public static void main(String[] args) {
		
		//Bus erzeugen; dieser Bus faehrt durchschnittlich 80.5 km/h und hat nur 3 Plaetze
		Bus bus = new Bus(80.5, 3);
		
		//Bahn erzeugen; diese Bahn faehrt durchschnittlich 120 km/h und hat 10 Plaetze
		Bahn bahn = new Bahn(120, 10);
		
		//Filiale von TuiReisen erzeugen, die ueber das o.g. bus und bahn verfuegt
		TuiReisen tui1 = new TuiReisen(bus, bahn);
		
		//Privatpersonen als Kunden erzeugen
		Privatperson p1 = new Privatperson("Robert", "Neumann");
		
		//Studenten als Kunden erzeugen
		Student s1 = new Student("Hans", "Maier", "HTW-Berlin");
		Student s2 = new Student("Angela", "Elbig", "HTW-Berlin");
		Student s3 = new Student("Maria", "Hellmann", "HU-Berlin");
		Student[] g1 = {s1, s2, s3};
		
		//Robert Neumann bucht eine Reise mit dem Bus.
		tui1.buchen(p1, Reiseanbieter.BUS);
		
		//Studenten s1, s2 und s3 (planen eine Exkursion und) buchen als Reisegruppe (d.h. nicht einzeln) eine Reise
		//mit dem Bus.
		tui1.buchen(g1, Reiseanbieter.BUS);
		
		//Also buchen die Studenten s1, s2 und s3 eine Reise als Reisegruppe mit EGAL welchem Transportmittel.
		tui1.buchen(g1, Reiseanbieter.EGAL);
		
		//Robert Neumann erfragt die Dauer der Reise – egal mit welchem Transportmittel.
		tui1.dauerErfragen(p1, Reiseanbieter.EGAL);
		
		//Robert Neumann erfragt die Dauer der Busreise.
		tui1.dauerErfragen(p1, Reiseanbieter.BUS);
		
		//Robert Neumann erfragt den Preis der Reise – egal mit welchem Transportmittel.
		tui1.preisErfragen(p1, Reiseanbieter.EGAL);
		
		//Robert Neumann erfragt noch den Preis der Bahnreise.
		tui1.preisErfragen(p1, Reiseanbieter.BAHN);
		
		//Robert Neumann (entscheidet sich um und) storniert seine Busreise.
		tui1.stornieren(p1, Reiseanbieter.BUS);
		
		//Robert Neumann bucht eine Bahnreise.
		tui1.buchen(p1, Reiseanbieter.BAHN);
		
		//(Die Exkursion ist ausgefallen und) Studenten s1, s2 und s3 wollen ihre Busreise als Reisegruppe stornieren.
		tui1.stornieren(g1, Reiseanbieter.BUS);
		
		//Die Studenten (sind sich unsicher, warum die Stornierung nicht geklappt hat und) versuchen, ihre Reise mit egal welchem Transportmittel zu stornieren.
		tui1.stornieren(g1, Reiseanbieter.EGAL);
		
		//Studenten s1, s2 und s3 stornieren als Reisegruppe ihre Bahnreise.
		tui1.stornieren(g1, Reiseanbieter.BAHN);

	}

}
