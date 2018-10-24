package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * IBenutzer wird implementiert von Verwaltung
 */
public interface IBenutzer {

    /**
     * erstellt neues Lager (2 dimensionales Array)
     * @param maxZeilen
     * @param maxSpalten
     * @return IDatenbank
     */
    IDatenbank lagerErstellen(int maxZeilen, int maxSpalten);

    /**
     * prueft ob Lagergroesse veraendern moeglich ist
     * ob bei Lagerverkleinerung ein Artikel abgeschnitten wird
     * @param lager
     * @param zeilen
     * @param spalten
     * @return boolean konfigurieren moeglich
     */
    boolean pruefLagerKonfigMoeglich(Lager lager, int zeilen, int spalten);

    /**
     * erstellt neues Lager und kopiert bestehende Artikel des alten Lagers
     * @param lagerAktuell
     * @param maxZeilenNeu
     * @param maxSpaltenNeu
     * @return IDatenbank Lager
     */
    IDatenbank lagerKonfigurieren(Lager lagerAktuell, int maxZeilenNeu, int maxSpaltenNeu);

    /**
     * erstellt einen neuen Artikel
     *
     * @param artikelBezeichnung1
     * @param verpackungsMenge1
     * @param verpackungsEinheit1
     * @param lieferant1
     * @param preis1
     * @return
     */
    Artikel artikelErstellen(String artikelBezeichnung1, double verpackungsMenge1, String verpackungsEinheit1, String lieferant1, double preis1);


    /**
     * erstellt neuen Artikel und positioniert an naechst freier Lagerposition
     *
     * @param artikelBezeichnung
     * @param verpackungsMenge
     * @param VerpackungsEinheit
     * @param lieferant
     * @param preis
     */
    void artikelEinlagernAutoPosition(String artikelBezeichnung, double verpackungsMenge, String VerpackungsEinheit, String lieferant, double preis);

    /**
     * erstell neuen Artikel und positioniert an selektierten Lagerposition
     *
     * @param artikelBezeichnung2
     * @param verpackungsMenge2
     * @param verpackungsEinheit2
     * @param lieferant2
     * @param preis2
     * @param zeile
     * @param spalte
     */
    void artikelEinlagernSelectPosition(String artikelBezeichnung2, double verpackungsMenge2, String verpackungsEinheit2, String lieferant2, double preis2, int zeile, int spalte);

    /**
     * erstellt String des Lagerbestandes
     * @return String Lager
     */
    String lagertoString();



    /**
     * GET - SET Methoden
     */
    Lager getLager();
    void setLager(IDatenbank lager);


}
