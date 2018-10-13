package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;


/**
 * @author Benjamin Hammerle
 * @version 01
 *
 * Lager als 2 dimensionales Array zum Verwalten von Artikel
 */
public class Lager implements IDatenbank {
    private int horizontal;
    private int vertikal;
    private Artikel[][] lagerPlan;
    private static int artikelNummerID = 0001;

    public Lager(int horizontal, int vertikal) {
        this.horizontal = horizontal;
        this.vertikal = vertikal;
        this.lagerPlan = new Artikel[vertikal][horizontal];

        for(int zeile=0; zeile<vertikal; zeile++) {
            for (int spalte=0; spalte<horizontal; spalte++) {
                this.lagerPlan[zeile][spalte] = null;
            }
        }
    }

    /**
     * Neuen Artikel anlegen und in naechst freier Lagerposition einlagern
     *
     * @param artikelBezeichnung
     * @param verpackungsMenge
     * @param verpackungsEinheit
     * @param lieferant
     * @param preis
     */
    public void addArtikelNeuAutoPosition(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis) {
        Artikel artikelNeu = new Artikel(artikelBezeichnung, artikelNummerID, verpackungsMenge, verpackungsEinheit, lieferant, preis);
        artikelNummerID++;

        for(int zeile=0; zeile<getVertikal(); zeile++) {
            for(int spalte=0;spalte<getHorizontal(); spalte++) {
                if(this.lagerPlan[zeile][spalte] == null) {
                    this.lagerPlan[zeile][spalte] = artikelNeu;
                    return;
                }
            }
        }

    }

    /**
     * Artikel in bestimmter Lagerposition einlagern
     *
     * @param artikel
     * @param zeile
     * @param spalte
     */
    public void addArtikel(Artikel artikel, int zeile, int spalte){
        lagerPlan[zeile][spalte] = artikel;
    }

    /**
     * Neuen Artikel anlegen und in bestimmter Lagerposition einlagern
     *
     * @param artikelBezeichnung
     * @param verpackungsMenge
     * @param verpackungsEinheit
     * @param lieferant
     * @param preis
     * @param zeile
     * @param spalte
     */
    public void addArtikelNeuSelectPosition(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis, int zeile, int spalte){
        Artikel artikelNeu = new Artikel(artikelBezeichnung, artikelNummerID, verpackungsMenge, verpackungsEinheit, lieferant, preis);
        artikelNummerID++;
        addArtikel(artikelNeu, zeile, spalte);
    }

    /**
     * Artikel aus bestimmter Lagerposition auslesen
     *
     * @param zeile
     * @param spalte
     * @return Artikel
     */
    public Artikel getArtikel(int zeile, int spalte){
        if(horizontal>=zeile && vertikal>=spalte){
            return lagerPlan[zeile][spalte];
        }
        return null;
    }

    /**
     * Durchsucht Lager nach Artikel mit bestimmter Artikelnummer und retourniert Artikel
     *
     * @param artikelNummer
     * @return Artikel
     */
    public Artikel getArtikelMitNummer(int artikelNummer){
        for(int zeile=0; zeile<getHorizontal(); zeile++){
            for(int spalte=0; spalte<getVertikal(); spalte++){
                if(lagerPlan[zeile][spalte] != null){
                    if(getArtikel(zeile, spalte).getArtikelNummer() == artikelNummer) {
                        return getArtikel(zeile, spalte);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Durchsucht Lager nach Artikel mit bestimmter Artikelnummer, retourniert Artikel und loescht ihn aus dem Lager
     *
     * @param artikelNummer
     * @return Artikel
     */
    public Artikel removeArtikel(int artikelNummer) {
        Artikel artikelAlt = null;
        for(int zeile=0; zeile<getVertikal(); zeile++) {
            for(int spalte=0;spalte<getHorizontal(); spalte++) {
                if(lagerPlan[zeile][spalte] != null){
                    if(lagerPlan[zeile][spalte].getArtikelNummer() == artikelNummer) {
                        artikelAlt = lagerPlan[zeile][spalte];
                        lagerPlan[zeile][spalte] = null;
                        return artikelAlt;
                    }
                }
            }
        }

        return artikelAlt;
    }

    /**
     * Durchsucht Lager nach Artikel mit bestimmter Artikelnummer und gibt ihn aus mit Position
     *
     * @param artikelNummer
     * @return String des Artikels mit Position
     */
    public String sucheArtikel(int artikelNummer){
        String gesuchterArtikel;
        String position;
        for(int zeile=0; zeile<getVertikal(); zeile++) {
            for(int spalte=0;spalte<getHorizontal(); spalte++) {
                if(lagerPlan[zeile][spalte] != null){
                    if(lagerPlan[zeile][spalte].getArtikelNummer() == artikelNummer) {
                        gesuchterArtikel = lagerPlan[zeile][spalte].toStringArtikelKomplett();
                        position = "\nZeile: " + zeile + "\t Spalte: " + spalte;
                        gesuchterArtikel += position;
                        return gesuchterArtikel;
                    }
                }
            }
        }
        return null;
    }

    /**
     * GET - SET Methoden
     */
    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public void setVertikal(int vertikal) {
        this.vertikal = vertikal;
    }

    public void setLagerPlan(Artikel[][] lagerPlan) {
        this.lagerPlan = lagerPlan;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertikal() {
        return vertikal;
    }

    public Artikel[][] getLagerPlan() {
        return lagerPlan;
    }
}
