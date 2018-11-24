package kunden;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

/**
* Schnittstelle Kunde <br>
* Klassen, die diese Schnittstelle implementieren, sind Kunden eines Reiseanbieters
* @author Adrianna Alexander
* @version 1.0
*/	
public interface Kunde {
	
  /**
  * @param nachricht Nachricht, die vom Kunden empfangen wird
  */
  public abstract void empfangeNachricht(String nachricht);
} 