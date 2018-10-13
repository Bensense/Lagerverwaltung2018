package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 13.10.2018
 *
 * IBenutzer wird implementiert von Verwaltung
 */
public interface IBenutzer {

    /**
     * erstellun neues Lager (2 dimensionales Array)
     * @param horizontal
     * @param vertikal
     * @return IDatenbank
     */
    public IDatenbank lagerErstellen(int horizontal, int vertikal);

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
     * @param horizontalNeu
     * @param vertikalNeu
     * @return IDatenbank Lager
     */
    IDatenbank lagerKonfigurieren(Lager lagerAktuell, int horizontalNeu, int vertikalNeu);


    /**
     * erstellt String des Lagerbestandes
     * @return String Lager
     */
    String lagertoString();

    /**
     * lagert Beispielartikel ins Lager ein
     */
    void addBeispielArtikel();

    /**
     * GET - SET Methoden
     */
    public Lager getLager();
    public void setLager(IDatenbank lager);
}
