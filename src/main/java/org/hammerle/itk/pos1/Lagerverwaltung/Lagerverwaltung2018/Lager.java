package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

import java.util.ArrayList;


/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * Lager als 2 dimensionales Array zum Einlagern von Artikel
 */
public class Lager implements IDatenbank {
    private int maxZeilen;
    private int maxSpalten;
    private Artikel[][] lagerPlan;


    /**
     * Konstruktor
     *
     * @param maxZeilen
     * @param maxSpalten
     */
    public Lager(int maxZeilen, int maxSpalten) {
        this.maxZeilen = maxZeilen;
        this.maxSpalten = maxSpalten;
        this.lagerPlan = new Artikel[maxZeilen][maxSpalten];

        for(int zeile=0; zeile<maxZeilen; zeile++) {
            for (int spalte=0; spalte<maxSpalten; spalte++) {
                this.lagerPlan[zeile][spalte] = null;
            }
        }
    }

    /**
     * Neuen Artikel anlegen und in naechst freier Lagerposition einlagern
     *
     * @param artikelNeu
     */
    public void addArtikelNeuAutoPosition(Artikel artikelNeu) {
        for(int zeile=0; zeile<getMaxZeilen(); zeile++) {
            for(int spalte=0;spalte<getMaxSpalten(); spalte++) {
                if(this.lagerPlan[zeile][spalte] == null) {
                    int positionZeile = zeile + 1;
                    int positionSpalte = spalte +1;
                    artikelNeu.setPositionZeile(positionZeile);
                    artikelNeu.setPositionSpalte(positionSpalte);
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
        int positionZeile = zeile + 1;
        int positionSpalte = spalte + 1;
        artikel.setPositionZeile(positionZeile);
        artikel.setPositionSpalte(positionSpalte);
        lagerPlan[zeile][spalte] = artikel;
    }

    /**
     * Neuen Artikel anlegen und in bestimmter Lagerposition einlagern
     *
     * @param artikelNeu
     * @param zeile
     * @param spalte
     */
    public void addArtikelNeuSelectPosition(Artikel artikelNeu, int zeile, int spalte){
        artikelNeu.setPositionZeile(zeile);
        artikelNeu.setPositionSpalte(spalte);
        addArtikel(artikelNeu, zeile-1, spalte-1);
    }


    /**
     * Artikel aus bestimmter Lagerposition auslesen
     *
     * @param zeile
     * @param spalte
     * @return Artikel
     */
    public Artikel getArtikel(int zeile, int spalte){
        if(maxZeilen>=zeile && maxSpalten>=spalte){
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
        for(int zeile=0; zeile<getMaxZeilen(); zeile++){
            for(int spalte=0; spalte<getMaxSpalten(); spalte++){
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
        for(int zeile=0; zeile<getMaxZeilen(); zeile++) {
            for(int spalte=0;spalte<getMaxSpalten(); spalte++) {
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
    public Artikel sucheArtikel(int artikelNummer){
        Artikel gesuchterArtikel;
        for(int zeile=0; zeile<getMaxZeilen(); zeile++) {
            for(int spalte=0;spalte<getMaxSpalten(); spalte++) {
                if(lagerPlan[zeile][spalte] != null){
                    if(lagerPlan[zeile][spalte].getArtikelNummer() == artikelNummer) {
                        gesuchterArtikel = lagerPlan[zeile][spalte];
                        return gesuchterArtikel;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Durchsucht Lager nach Artikeln mit bestimmter Artikelbezeichnung
     *
     * @param artikelBezeichnung
     * @return ArrayList mit gesuchten Artikeln
     */
    public ArrayList<Artikel> sucheArtikelViaBezeichnung(String artikelBezeichnung) {
        ArrayList<Artikel> ergebnisListe = new ArrayList<>();
        for(int zeile=0; zeile<getMaxZeilen(); zeile++) {
            for (int spalte = 0; spalte < getMaxSpalten(); spalte++) {
                if (lagerPlan[zeile][spalte] != null) {
                    if(lagerPlan[zeile][spalte].getArtikelBezeichnung().equals(artikelBezeichnung)) {
                        ergebnisListe.add(lagerPlan[zeile][spalte]);
                    }
                }
            }
        }
        return ergebnisListe;
    }

    /**
     * GET - SET Methoden
     */
    public void setMaxZeilen(int maxZeilen) {
        this.maxZeilen = maxZeilen;
    }

    public void setMaxSpalten(int maxSpalten) {
        this.maxSpalten = maxSpalten;
    }

    public void setLagerPlan(Artikel[][] lagerPlan) {
        this.lagerPlan = lagerPlan;
    }

    public int getMaxZeilen() {
        return maxZeilen;
    }

    public int getMaxSpalten() {
        return maxSpalten;
    }

    public Artikel[][] getLagerPlan() {
        return lagerPlan;
    }
}
