package reiseanbieter;
//Sina Haddadi Sedehi
//Matrikelnummer 566440

import kunden.*;

/**
 * Schnittstelle Reiseanbieter <br>
 * Klassen, die diese Schnittstelle implementieren, sind konkrete Reiseanbieter, wie TuiReisen usw.
 * @author Adrianna Alexander
 * @author Sina Haddadi Sedehi
 * @version 1.0
 */
public interface Reiseanbieter {
	
	/**
	 * Fuer Auswahl des Transportmittels EGAL = 0
	 */
	public static final int EGAL = 0;
	
	/**
	 * Fuer Auswahl des Transportmittels BUS = 1
	 */
	public static final int BUS = 1;
	
	/**
	 * Fuer Auswahl des Transportmittels BAHN = 2
	 */
	public static final int BAHN = 2;
	
	/**
	 * Entfernung in Km auf Strassen (Busreise) 288.2 Km
	 */
	public static final double entfernungBus = 288.2;
	
	/**
	 * Entfernung in Km auf Schienen (Bahnreise) 310 Km
	 */
	public static final double entfernungBahn = 310;
	
	/**
	 * Einzelkunde kann eine Reise mit ausgewaehltem Transportmittel buchen, vorausgesetzt, es sind
	 * noch Plaetze vorhanden. Fuer das Transportmittel kann BUS, BAHN oder EGAL angegeben werden. 
	 * Die Zeitkomponente (Angabe des Termins fuer eine Buchung) wird der Einfachheit halber 
	 * vernachlaessigt (es wird immer tagesaktuell gebucht).
	 * @param k Kunde fuer dem die Reise gebucht werden soll
	 * @param transport Gewuenschtes Transportmittel
	 * @return True = erfolgreich gebucht <br> False = Buchung fehlgeschlagen
	 */
	public abstract boolean buchen(Kunde k, int transport);
	
	/**
	 * Eine Reisegruppe kann eine Reise mit ausgewaehltem Transportmittel buchen, vorausgesetzt, es 
	 * sind noch in diesem Transportmittel Plaetze fuer <strong>alle</strong> Gruppenmitglieder vorhanden. Anderenfalls 
	 * wird die Reise fuer die Reisegruppe nicht gebucht.
	 * @param gruppe Gruppe fuer dem die Reise gebucht werden soll
	 * @param transport Gewuenschtes Transportmittel
	 * @return True = erfolgreich gebucht <br> False = Buchung fehlgeschlagen
	 */
	public abstract boolean buchen(Kunde[] gruppe, int transport);
	
	/**
	 * War die Buchung erfolgreich, sendet der Reiseanbieter eine Buchungsbestaetigung an den Kunden.
	 * @param k Kunde fuer dem die Buchungsbestaetigung geschickt werden soll
	 * @param transport Transportmittel welches ueberprueft werden soll
	 */
	public abstract void buchungBestaetigen(Kunde k, int transport);
	
	/**
	 * War die Buchung nicht moeglich, sendet der Reiseanbieter eine Buchungsablehnung an den Kunden.
	 * @param k Kunde fuer dem die Buchungsablehnung geschickt werden soll
	 * @param transport Transportmittel fuer welches nicht gebucht werden konnte
	 */
	public abstract void buchungAblehnen(Kunde k, int transport);
	
	/**
	 * Der Einzelkunde kann eine Buchung mit ausgewaehlten Transportmittel stornieren. Dies wird 
	 * immer durchgefuehrt, sofern der Kunde die Reise mit diesem Transportmittel ueberhaupt gebucht 
	 * hatte. Wird vom Kunden als Transportmittel EGAL ausgewaehlt, sendet der Reiseanbieter eine 
	 * Aufforderung an den Kunden, dass er das Transportmittel bei der Stornierung festlegen muss 
	 * (Reiseanbieter fuehrt in dem Fall keine Stornierung durch).
	 * @param k Kunde bei dem die Reise storniert werden soll
	 * @param transport Transportmittel wo der Kunde gebucht hatte
	 * @return True = Stornierung erfolgreich <br> False = Stornierung abgelehnt
	 */
	public abstract boolean stornieren(Kunde k, int transport); 
	
	/**
	 * Eine Reisegruppe kann eine Reise mit ausgewaehltem Transportmittel stornieren; die Stornierung 
	 * wird fuer jeden Gruppenmitglied durchgefuehrt, vorausgesetzt, das Gruppenmitglied hat die Reise 
	 * mit dem Transportmittel ueberhaupt gebucht. Wird von der Reisegruppe als Transportmittel EGAL 
	 * ausgewaehlt, sendet der Reiseanbieter eine Aufforderung, das Transportmittel bei der Stornierung 
	 * festzulegen (Reiseanbieter fuehrt in dem Fall keine Stornierung durch).
	 * @param gruppe Gruppe bei dem die Reise storniert werden soll
	 * @param transport Transportmittel wo die Gruppe gebucht hatte
	 * @return True = Stornierung erfolgreich <br> False = Stornierung abgelehnt
	 */
	public abstract boolean stornieren(Kunde[] gruppe, int transport);
	
	/**
	 * War die Stornierung erfolgreich, sendet der Reiseanbieter eine Stornierungsbestaetigung an den Kunden.
	 * @param k Kunde fuer dem die Stornobestaetigung geschickt werden soll
	 * @param transport Transportmittel welches ueberprueft werden soll
	 */
	public abstract void stornoBestaetigen(Kunde k, int transport);
	
	/**
	 * War die Stornierung nicht moeglich, sendet der Reiseanbieter eine Stornierungssablehnung an den Kunden.
	 * @param k Kunde fuer dem die Stornoablehnung geschickt werden soll
	 * @param transport Transportmittel wo storniert werden sollte
	 */
	public abstract void stornoAblehnen(Kunde k, int transport);
	
	
	/**
	 * Der Kunde kann den Preis fuer ein ausgewaehltes Transportmittel erfragen. Der Reiseanbieter 
	 * sendet ihm dann eine Antwort. *Die Preise pro Strecke fuer eine Bus- und Bahnreise legt der 
	 * konkrete Reiseanbieter fest (kann also vom Anbieter zu Anbieter variieren).
	 * @param k Kunde der den Preis wissen will
	 * @param transport Transportmittel wo der Preis von benoetigt wird
	 * @return Gibt den Preis der Reise wieder
	 */
	public abstract double preisErfragen(Kunde k, int transport);
	
	/**
	 * Der Kunde kann die Dauer der Reise mit einem ausgewaehlten Transportmittel erfragen. Die Dauer 
	 * haengt natuerlich von der Entfernung mit einem konkreten Transportmittel ab und auch von der 
	 * Durchschnittsgeschwindigkeit, die ein konkretes Transportmittel besitzt. Der Reiseanbieter 
	 * sendet dem Kunden eine Antwort (*und sorgt dafuer, dass die angegebene Dauer in Stunden auf 
	 * maximal zwei Nachkommastellen aufgerundet wird).
	 * @param k Kunde der die Dauer wissen will
	 * @param transport Transportmittel wo die Dauer von benoetigt wird
	 * @return Gibt die Dauer der Reise in Stunden (Zwei Nachkommastellen werden angezeigt) an
	 */
	public abstract double dauerErfragen(Kunde k, int transport);
}
