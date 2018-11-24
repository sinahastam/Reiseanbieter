package kunden;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

/**
 * Klasse fuer Studenten
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */
public class Student extends Privatperson {
	private String hochschule;

	/**
	 * Default Konstruktor
	 */
	public Student() {
		System.out.println("Warnung @Student");
	}

	/**
	 * Erstellt ein Objekt Student
	 * @param vorname Vorname der Person
	 * @param nachname Nachname der Person
	 * @param hochschule Hochschule des Studenten
	 */
	public Student(String vorname, String nachname, String hochschule) {
		super(vorname, nachname);
		this.hochschule = hochschule;
	}

	@Override
	public void empfangeNachricht(String nachricht) {
		System.out.println("an " +getVorname() +" " +getNachname() +", " +hochschule +": " +nachricht);
	}

	/**
	 * @return Hochschule des Studenten
	 */
	public String getHochschule() {
		return hochschule;
	}

	/**
	 * @param hochschule Hochschule des Studenten festlegen
	 */
	public void setHochschule(String hochschule) {
		this.hochschule = hochschule;
	}

}
