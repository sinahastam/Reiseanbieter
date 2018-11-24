package kunden;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

/**
 * Klasse fuer Privatpersonen
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */
public class Privatperson implements Kunde {
	private String vorname;
	private String nachname;
	
	/**
	 * Default Konstruktor
	 */
	public Privatperson() {
		System.out.println("Warnung @Privatperson");
	}
	
	/**
	 * Erstellt ein Objekt Privatperson
	 * @param vorname Vorname der Person
	 * @param nachname Nachname der Person
	 */
	public Privatperson(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}


	@Override
	public void empfangeNachricht(String nachricht) {
		System.out.println("an " +vorname +" " +nachname +": " +nachricht);
	}

	/**
	 * @return Vorname von Person
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @return Nachname von Person
	 */
	public String getNachname() {
		return nachname;
	}

	
	/**
	 * @param vorname Vorname der Person festlegen
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @param nachname Nachname der Person festlegen
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

}
