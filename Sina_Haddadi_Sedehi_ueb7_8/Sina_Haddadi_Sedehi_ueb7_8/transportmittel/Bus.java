package transportmittel;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

/**
 * Bus Klasse fuer Reiseanbieter die einen Bus als Transportmittel besitzten
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */

import kunden.*;

public class Bus implements Transportmittel {
	private int plaetze;
	private double dGeschwindigkeit;
	private Privatperson[] personenliste;

	/**
	 * Default Konstruktor
	 */
	public Bus() {
		System.out.println("Warnung @Bus");
	}
	
	/**
	 * Erstellt ein Bus mit Durchschnittsgeschwindigkeit und Anzahl der verfuegbaren Plaetze
	 * @param plaetze Maximal verfuegbare Pleatze im Bus
	 * @param dGeschwindigkeit Durchschnittsgeschwindigkeit vom Bus
	 */
	public Bus(double dGeschwindigkeit, int plaetze) {
		this.plaetze = plaetze;
		this.dGeschwindigkeit = dGeschwindigkeit;
		personenliste = new Privatperson[plaetze];
	}

	@Override
	public double getGeschwindigkeit() {
		return dGeschwindigkeit;
	}

	@Override
	public int getAnzahlPlaetze() {
		return plaetze;
	}
	
	/**
	 * Gibt die Personenliste vom Bus wieder
	 * @return personenliste (Array von Personen die fuer den Bus gebucht haben)
	 */
	public Privatperson[] getPersonenliste() {
		return personenliste;
	}

	/**
	 * Aendert/Aktualisiert die maximal verfuegbare Anzahl der Plaetze des Transportmittels
	 * @param plaetze Die neue Anzahl der Plaetze
	 */
	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}

	/**
	 * Aendert/Aktualisiert die Durchschnittgeschwindigkeit des Transportmittels
	 * @param dGeschwindigkeit Die neue Durchschnittsgeschwindigkeit
	 */
	public void setGeschwindigkeit(double dGeschwindigkeit) {
		this.dGeschwindigkeit = dGeschwindigkeit;
	}

	/**
	 * Aendert/Aktualisiert die Personenliste
	 * @param personenliste Die neue Personenliste welche die alte ersetzten soll
	 */
	public void setPersonenliste(Privatperson[] personenliste) {
		this.personenliste = personenliste;
	}

}
