package reiseanbieter;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

import kunden.*;
import transportmittel.*;

/**
 * Klasse TuiReisen (Reiseanbieter)
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */
public class TuiReisen implements Reiseanbieter {
	
	/**
	 * Reisekosten mit dem Bus bei Tui
	 */
	private double reisekostenBus = 25.0;
	
	/**
	 * Reisekosten mit der Bahn bei Tui
	 */
	private double reisekostenBahn = 40.0;
	
	/**
	 * Bus von Tui
	 */
	private Bus bus;
	
	/**
	 * Bahn von Tui
	 */
	private Bahn bahn;
	
	/**
	 * Default Konstruktor
	 */
	public TuiReisen() {
		System.out.println("Warnung @TuiReisen");
	}
	
	/**
	 * Erstellt eine Tui Filiale mit dem jeweiligen Bus und Bahn
	 * @param bus Konkreter Bus
	 * @param bahn Konkrete Bahn
	 */
	public TuiReisen(Bus bus, Bahn bahn) {
		this.bus = bus;
		this.bahn = bahn;
	}

	@Override
	public boolean buchen(Kunde k, int transport) {
		//wenn bus gewaehlt wurde
		if (transport == Reiseanbieter.BUS) {
			if (bus.getAnzahlPlaetze() >= 1) {
				bus.setPlaetze(bus.getAnzahlPlaetze() - 1);
				//erstellt kopie von personenliste bus
				Privatperson[] temp = new Privatperson[bus.getPersonenliste().length];
				temp = bus.getPersonenliste();
				//geht liste durch und wenn eine stelle frei (null) ist wird diese fuer den Kunden gebucht
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (Privatperson) k;
						//aktualisiert personenliste
						bus.setPersonenliste(temp);
						buchungBestaetigen(k, Reiseanbieter.BUS);
						return true;
					}
				}
			}else if(bus.getAnzahlPlaetze() == 0) {
				buchungAblehnen(k, Reiseanbieter.BUS);		//wenn keine plaetze mehr frei
				return false;
			}
		}
		//wenn bahn gewaehlt wurde
		if (transport == Reiseanbieter.BAHN) {
			if (bahn.getAnzahlPlaetze() >= 1) {
				bahn.setPlaetze(bahn.getAnzahlPlaetze() - 1);
				//erstellt kopie von personenliste bahn
				Privatperson[] temp = new Privatperson[bahn.getPersonenliste().length];
				temp = bahn.getPersonenliste();
				//geht liste durch und wenn eine stelle frei (null) ist wird diese fuer den Kunden gebucht
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (Privatperson) k;
						//aktualisiert personenliste
						bahn.setPersonenliste(temp);
						buchungBestaetigen(k, Reiseanbieter.BAHN);
						return true;
					}
				}
			}else if(bahn.getAnzahlPlaetze() == 0) {		//wenn keine plaetze mehr frei
				buchungAblehnen(k, Reiseanbieter.BAHN);
				return false;
			}
		}
		//wenn egal gewaehlt wurde
		if(transport == Reiseanbieter.EGAL) {
			if(bahn.getAnzahlPlaetze() >= 1) {
				bahn.setPlaetze(bahn.getAnzahlPlaetze() - 1);
				Privatperson[] temp = new Privatperson[bahn.getPersonenliste().length];
				temp = bahn.getPersonenliste();
				for (int i = 0; i < temp.length; i++) {
					if (temp[i] == null) {
						temp[i] = (Privatperson) k;
						bahn.setPersonenliste(temp);
						buchungBestaetigen(k, Reiseanbieter.BAHN);
						return true;
					}
				}
			}else if(bahn.getAnzahlPlaetze() == 0) {		//wenn keine plaetze mehr frei
				if (bus.getAnzahlPlaetze() >= 1) {
					bus.setPlaetze(bus.getAnzahlPlaetze() - 1);
					Privatperson[] temp = new Privatperson[bus.getPersonenliste().length];
					temp = bus.getPersonenliste();
					for (int i = 0; i < temp.length; i++) {
						if (temp[i] == null) {
							temp[i] = (Privatperson) k;
							bus.setPersonenliste(temp);
							buchungBestaetigen(k, Reiseanbieter.BUS);
							return true;
						}
					}
				}else if(bus.getAnzahlPlaetze() == 0) {		//wenn keine plaetze mehr frei
					buchungAblehnen(k, Reiseanbieter.EGAL);
					return false;
				}
			}
		}
		
		k.empfangeNachricht("Reise konnte nicht gebucht werden");
		return false;
	}

	@Override
	public boolean buchen(Kunde[] gruppe, int transport) {
		if(transport == Reiseanbieter.BUS) {
			//wenn fuer ganze gruppe platz ist wird fuer jeden gebucht
			if (bus.getAnzahlPlaetze() >= gruppe.length) {
				for (int i = 0; i < gruppe.length; i++) {
					buchen(gruppe[i], Reiseanbieter.BUS);
				}
				return true;
			}else if(bus.getAnzahlPlaetze() < gruppe.length) {		//wenn fuer ganze gruppe kein platz ist bekommt jeder eine ablehnung
				for (int i = 0; i < gruppe.length; i++) {
					buchungAblehnen(gruppe[i], Reiseanbieter.BUS);
				}
				return false;
			}
		}
		
		if(transport == Reiseanbieter.BAHN) {
			//wenn fuer ganze gruppe platz ist wird fuer jeden gebucht
			if (bahn.getAnzahlPlaetze() >= gruppe.length) {
				for (int i = 0; i < gruppe.length; i++) {
					buchen(gruppe[i], Reiseanbieter.BAHN);
				}
				return true;
			}else if(bahn.getAnzahlPlaetze() < gruppe.length) {		//wenn fuer ganze gruppe kein platz ist bekommt jeder eine ablehnung
				for (int i = 0; i < gruppe.length; i++) {
					buchungAblehnen(gruppe[i], Reiseanbieter.BAHN);
				}
				return false;
			}
		}
		//wenn egal als transportmittel gewaehlt wurde
		if(transport == Reiseanbieter.EGAL) {
			//wenn fuer ganze gruppe platz ist wird fuer jeden gebucht
			if(bahn.getAnzahlPlaetze() >= gruppe.length) {
				for (int i = 0; i < gruppe.length; i++) {
					buchen(gruppe[i], Reiseanbieter.BAHN);
				}
				return true;
			}else if(bahn.getAnzahlPlaetze() < gruppe.length && bus.getAnzahlPlaetze() >= gruppe.length){
				for (int i = 0; i < gruppe.length; i++) {
					buchen(gruppe[i], Reiseanbieter.BUS);
				}
				return true;
			}else if(bus.getAnzahlPlaetze() < gruppe.length) {		//wenn fuer ganze gruppe kein platz ist fuer Bus und Bahn dann bekommt jeder eine ablehnung
				for (int i = 0; i < gruppe.length; i++) {
					buchungAblehnen(gruppe[i], Reiseanbieter.EGAL);
				}
				return false;
			}
		}
		
		//wenn ein unbekanntes transportmittel angegeben wird
		for(int j = 0; j < gruppe.length; j++) {
			gruppe[j].empfangeNachricht("Reise konnte nicht gebucht werden");

		}
		return false;
	}

	@Override
	public void buchungBestaetigen(Kunde k, int transport) {
		//prueft ob kunde der kunde erfolgreich gebucht hat sprich auf der personenliste fuer bus steht
		if(transport == Reiseanbieter.BUS) {
			for(int i = 0; i < bus.getPersonenliste().length; i++) {
				try {
					if(bus.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bus.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						k.empfangeNachricht("Busreise gebucht");
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
		}
		//prueft ob kunde der kunde erfolgreich gebucht hat sprich auf der personenliste fuer bahn steht
		if(transport == Reiseanbieter.BAHN) {
			for(int i = 0; i < bahn.getPersonenliste().length; i++) {
				try {
					if(bahn.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bahn.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						k.empfangeNachricht("Bahnreise gebucht");
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
		}
		//prueft ob kunde der kunde erfolgreich gebucht hat sprich auf der personenliste fuer bus ODER bahn steht
		if(transport == Reiseanbieter.EGAL) {
			for(int i = 0; i < bus.getPersonenliste().length; i++) {
				try {
					if(bus.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bus.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						k.empfangeNachricht("Busreise gebucht");
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
			
			for(int i = 0; i < bahn.getPersonenliste().length; i++) {
				try {
					if(bahn.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bahn.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						k.empfangeNachricht("Bahnreise gebucht");
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
		}
	}

	@Override
	public void buchungAblehnen(Kunde k, int transport) {
		if(transport == Reiseanbieter.BUS) {
			k.empfangeNachricht("Busreise konnte nicht gebucht werden");
		}
		
		if(transport == Reiseanbieter.BAHN) {
			k.empfangeNachricht("Bahnreise konnte nicht gebucht werden");
		}
		
		if(transport == Reiseanbieter.EGAL) {
			k.empfangeNachricht("Reise konnte nicht gebucht werden");
		}
	}

	@Override
	public boolean stornieren(Kunde k, int transport) {
		//loescht(null) buchung vom kunden fuer dieses transportmittel
		if(transport == Reiseanbieter.BUS) {
			for(int i = 0; i < bus.getPersonenliste().length; i++) {
				try {
					if(bus.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bus.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						bus.getPersonenliste()[i] = null;
						//schickt bestaetigung an kunden wenn bestaetigt wurde
						stornoBestaetigen(k, Reiseanbieter.BUS);
						return true;
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
			//wenn nichts storniert werden konnte -> ablehnung an kunde schicken
			stornoAblehnen(k, Reiseanbieter.BUS);
			return false;
		}else if(transport == Reiseanbieter.BAHN) {			//loescht(null) buchung vom kunden fuer dieses transportmittel
			for(int i = 0; i < bahn.getPersonenliste().length; i++) {
				try {
					if(bahn.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bahn.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						bahn.getPersonenliste()[i] = null;
						//schickt bestaetigung an kunden wenn bestaetigt wurde
						stornoBestaetigen(k, Reiseanbieter.BAHN);
						return true;
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
			//wenn nichts storniert werden konnte -> ablehnung an kunde schicken
			stornoAblehnen(k, Reiseanbieter.BAHN);
			return false;
		}else if (transport == Reiseanbieter.EGAL) {
			//wenn egal als transportmittel angegeben -> ablehnung an kunde schicken
			stornoAblehnen(k, Reiseanbieter.EGAL);
		}else {
			//wenn unbekanntes transportmittel angegeben -> ablehnung an kunde schicken
			stornoAblehnen(k, transport);
		}
		
		return false;
	}

	@Override
	public boolean stornieren(Kunde[] gruppe, int transport) {
		if(transport == Reiseanbieter.BUS) {
			for(int i = 0; i < gruppe.length; i++) {
				stornieren(gruppe[i], Reiseanbieter.BUS);
			}
			return true;
		}else if(transport == Reiseanbieter.BAHN) {
			for(int i = 0; i < gruppe.length; i++) {
				stornieren(gruppe[i], Reiseanbieter.BAHN);
			}
			return true;
		}else if(transport == Reiseanbieter.EGAL) {			//wenn kein konkretes transportmittel angegeben wird
			System.out.println("Nicht moeglich! Bitte stornieren Sie die Reise noch einmal, aber unter Angabe des Transportmittels.");
			return false;
		}else {												//wenn kein konkretes transportmittel angegeben wird
			System.out.println("Nicht moeglich! Bitte stornieren Sie die Reise noch einmal, aber unter Angabe des Transportmittels.");
			return false;
		}
	}

	@Override
	public void stornoBestaetigen(Kunde k, int transport) {
		boolean nochgebucht = false;						//wenn false -> kunde steht nicht (mehr) auf personenliste
		if(transport == Reiseanbieter.BUS) {
			for(int i = 0; i < bus.getPersonenliste().length; i++) {
				try {
					if(bus.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bus.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						nochgebucht = true; //wenn Kunde noch auf der liste steht
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
			if (nochgebucht == false) {
				k.empfangeNachricht("Busreise storniert");
			}
		}
		
		if(transport == Reiseanbieter.BAHN) {
			for(int i = 0; i < bahn.getPersonenliste().length; i++) {
				try {
					if(bahn.getPersonenliste()[i].getVorname() == ((Privatperson)k).getVorname() && bahn.getPersonenliste()[i].getNachname() == ((Privatperson)k).getNachname()) {
						nochgebucht = true; //wenn Kunde noch auf der liste steht
					}
				} catch (NullPointerException e) {
					//exception da noch nicht alle plaetzte mit personen belegt wurden
				}
			}
			if (nochgebucht == false) {
				k.empfangeNachricht("Bahnreise storniert");
			}
		}
	}

	@Override
	public void stornoAblehnen(Kunde k, int transport) {
		if(transport == Reiseanbieter.BUS) {
			k.empfangeNachricht("Busreise konnte nicht storniert werden");
		}else if(transport == Reiseanbieter.BAHN) {
			k.empfangeNachricht("Bahnreise konnte nicht storniert werden");
		}else if(transport == Reiseanbieter.EGAL) {
			k.empfangeNachricht("Nicht moeglich! Bitte stornieren Sie die Reise noch einmal, aber unter Angabe des Transportmittels.");
		}else {
			k.empfangeNachricht("Nicht moeglich! Bitte stornieren Sie die Reise noch einmal, aber unter Angabe des Transportmittels.");
		}
		
	}

	@Override
	public double preisErfragen(Kunde k, int transport) {
		if(transport == Reiseanbieter.BUS) {
			k.empfangeNachricht("Busreise kostet " +reisekostenBus +" EUR.");
			return reisekostenBus;
		}else if(transport == Reiseanbieter.BAHN) {
			k.empfangeNachricht("Bahnreise kostet " +reisekostenBahn +" EUR.");
			return reisekostenBahn;
		}else if (transport == Reiseanbieter.EGAL) {
			k.empfangeNachricht("Am guenstigsten ist die Busreise und kostet " +reisekostenBus +" EUR.");
			return reisekostenBus;
		}else {
			k.empfangeNachricht("Transportmittel ist nicht bekannt. Bitte Eingabe ueberpruefen");
			return -1;
		}
	}

	@Override
	public double dauerErfragen(Kunde k, int transport) {
		//reise dauer fuer bus
		double dauerBus = Reiseanbieter.entfernungBus/bus.getGeschwindigkeit();
		//reise dauer fuer bahn
		double dauerBahn = Reiseanbieter.entfernungBahn/bahn.getGeschwindigkeit();
		
		if(transport == Reiseanbieter.BUS) {
			//sendet reisedauer in stunden gerundet auf 2 nachkommastellen
			k.empfangeNachricht("Busreise dauert " +(Math.round(dauerBus *100.0)/100.0) +" Stunden.");
			return dauerBus;
		}else if(transport == Reiseanbieter.BAHN) {
			//sendet reisedauer in stunden gerundet auf 2 nachkommastellen
			k.empfangeNachricht("Bahnreise dauert " +(Math.round(dauerBahn *100.0)/100.0) +" Stunden.");
			return dauerBahn;
		}else if ((transport == Reiseanbieter.EGAL) && (dauerBahn < dauerBus)) {
				//wenn bahnreise kuerzer -> sendet reisedauer in stunden gerundet auf 2 nachkommastellen
				k.empfangeNachricht("Bahnreise ist am schnellsten und dauert " +(Math.round(dauerBahn *100.0)/100.0) +" Stunden.");
				return dauerBahn;
		}else if((transport == Reiseanbieter.EGAL) && (dauerBahn > dauerBus)) {
				//wenn busreise kuerzer -> sendet reisedauer in stunden gerundet auf 2 nachkommastellen
				k.empfangeNachricht("Busreise ist am schnellsten und dauert " +(Math.round(dauerBus *100.0)/100.0) +" Stunden.");
				return dauerBus;
		}else if((transport == Reiseanbieter.EGAL) && (dauerBahn == dauerBus)) {
				//wenn bahnreise und busreise gleich schnell -> sendet reisedauer in stunden gerundet auf 2 nachkommastellen
				k.empfangeNachricht("Bahnreise ist am schnellsten und dauert " +(Math.round(dauerBahn *100.0)/100.0) +" Stunden.");
				return dauerBahn;
		}else {
			//wenn kein konkretes transportmittel angegeben wird
			k.empfangeNachricht("Transportmittel ist nicht bekannt. Bitte Eingabe ueberpruefen");
			return -1;
		}
	}

}