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
     * erstellt neuen Artikel und positioniert an naechst freier Lagerposition
     *
     * @param artikelVO
     */
    void artikelEinlagernAutoPosition(DTO artikelVO);

    /**
     * erstell neuen Artikel und positioniert an selektierten Lagerposition
     *
     * @param artikelVO
     * @param zeile
     * @param spalte
     */
    void artikelEinlagernSelectPosition(DTO artikelVO, int zeile, int spalte);



    /**
     * GET - SET Methoden
     */
    Lager getLager();
    void setLager(IDatenbank lager);


    /**
     * sucht Artikel via Artikelnummer, fuegt ihn in DTO ein und uebergibt DTO
     *
     * @param artikelNummerAuswahl
     * @return
     */
    DTO getArtikelViaArtikelID(int artikelNummerAuswahl);

    /**
     * sucht alle Artikel im Lager, fuegt sie in DTO ein und uebergibt DTO
     *
     * @return
     */
    DTO getAlleArtikel();

    /**
     * sucht alle Artikel im Lager mit bestimmten Artikelbezeichnung, fuegt sie in DTO ein und uebergibt DTO
     *
     * @param artikelBezeichnungSuche
     * @return
     */
    DTO getArtikelViaArtikelBezeichnung(String artikelBezeichnungSuche);

    /**
     * gibt Inhalt einer bestimmten Lager Position wieder
     *
     * @param zeile
     * @param spalte
     * @return
     */
    DTO getPositionInhalt(int zeile, int spalte);

    /**
     * sucht Artikel via Artikelnummer und aendert ihn
     *
     * @param artikelVeraendert
     * @param artikelIDSuche
     */
    void artikelAendern(DTO artikelVeraendert, int artikelIDSuche);
}
