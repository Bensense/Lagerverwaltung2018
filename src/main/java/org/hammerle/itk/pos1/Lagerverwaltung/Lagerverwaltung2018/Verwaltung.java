package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * Verwaltung des Lagers
 */
public class Verwaltung implements IBenutzer {

    private IDatenbank lager;
    private static int artikelNummerID = 0001;

    /**
     * Konstruktor
     */
    public Verwaltung() {

    }

    /**
     * erstellt neues Lager (2 dimensionales Array)
     * @param maxZeilen
     * @param maxSpalten
     * @return IDatenbank Lager
     */
    public IDatenbank lagerErstellen(int maxZeilen, int maxSpalten) {
        this.lager = new Lager(maxZeilen, maxSpalten);
        return this.lager;
    }

    /**
     * erstellt neuen Artikel
     *
     * @param artikelBezeichnung
     * @param verpackungsMenge
     * @param verpackungsEinheit
     * @param lieferant
     * @param preis
     * @return
     */
    public Artikel artikelErstellen(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis) {
        Artikel artikelNeu = new Artikel(artikelBezeichnung, artikelNummerID, verpackungsMenge, verpackungsEinheit, lieferant, preis);
        artikelNummerID++;

        return artikelNeu;
    }

    public void artikelEinlagernAutoPosition(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis) {
        Artikel artikelNeu = artikelErstellen(artikelBezeichnung, verpackungsMenge, verpackungsEinheit, lieferant, preis);
        this.lager.addArtikelNeuAutoPosition(artikelNeu);
    }

    public void artikelEinlagernSelectPosition(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis, int zeile, int spalte) {
        Artikel artikelNeu = artikelErstellen(artikelBezeichnung, verpackungsMenge, verpackungsEinheit, lieferant, preis);
        this.lager.addArtikelNeuSelectPosition(artikelNeu, zeile, spalte);
    }

    /**
     * prueft ob die Aenderung des Lagers moeglich ist
     * falls Lager verkleinert wird: ob ein Artikel weggeschnitten wird
     * @param lagerAktuell
     * @param maxZeilenNeu
     * @param maxSpaltenNeu
     * @return boolean ob konfigurieren moeglich
     */
    public boolean pruefLagerKonfigMoeglich(Lager lagerAktuell, int maxZeilenNeu, int maxSpaltenNeu){
        int maxZeilenAktuell = lagerAktuell.getMaxZeilen();
        int maxSpaltenAktuell = lagerAktuell.getMaxSpalten();
        boolean vertikalNeuMoeglich = false;
        boolean horizontalNeuMoeglich = false;

        boolean restNochFrei = true;
        boolean pruefSummeMoeglich = false;

        if(maxZeilenNeu>=maxZeilenAktuell){
            vertikalNeuMoeglich = true;
        }
        if(maxSpaltenNeu>=maxSpaltenAktuell){
            horizontalNeuMoeglich = true;
        }


        // wenn neu groesser
        if(horizontalNeuMoeglich==true && vertikalNeuMoeglich==true){
            pruefSummeMoeglich = true;
            return pruefSummeMoeglich;
        }
        // wenn neu schmaeler und laenger
        if(horizontalNeuMoeglich==false && vertikalNeuMoeglich==true) {
            for(int zeile=0; zeile<=maxZeilenAktuell-1;zeile++) {
                for (int spalte = maxSpaltenNeu; spalte <= maxSpaltenAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
        }
        // wenn neu breiter und kuerzer
        if(horizontalNeuMoeglich==true && vertikalNeuMoeglich==false){
            for (int zeile = maxZeilenNeu; zeile <= maxZeilenAktuell-1; zeile++) {
                for (int spalte = 0; spalte <= maxSpaltenAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
        }
        // wenn neu schmaeler und kuerzer
        if(horizontalNeuMoeglich==false && vertikalNeuMoeglich==false){
            for (int zeile = maxZeilenNeu; zeile <= maxZeilenAktuell-1; zeile++) {
                for (int spalte = 0; spalte <= maxSpaltenAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
            for(int zeile=0; zeile<=maxZeilenAktuell-1;zeile++) {
                for (int spalte = maxSpaltenNeu; spalte <= maxSpaltenAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
        }
        pruefSummeMoeglich = true;
        return pruefSummeMoeglich;
    }

    /**
     * erstellt neues Lager (2 dimensionales Array) und kopiert bestehende Artikel des alten Lagers
     * @param lagerAktuell
     * @param maxZeilenNeu
     * @param maxSpaltenNeu
     * @return IDatenbank neues Lager
     */
    public IDatenbank lagerKonfigurieren(Lager lagerAktuell, int maxZeilenNeu, int maxSpaltenNeu) {
        int maxZeilenAktuell = lagerAktuell.getMaxZeilen();
        int maxSpaltenAktuell = lagerAktuell.getMaxSpalten();
        IDatenbank lagerNeu = new Lager(maxZeilenNeu, maxSpaltenNeu);
        int zeile;
        int spalte;

        if(maxZeilenAktuell<maxZeilenNeu){
            zeile = maxZeilenAktuell;
        }
        else{
            zeile = maxZeilenNeu;
        }
        if(maxSpaltenAktuell<maxSpaltenNeu){
            spalte = maxSpaltenAktuell;
        }
        else{
            spalte = maxSpaltenNeu;
        }
        for(int z=0; z<zeile;z++){
            for(int s=0; s<spalte; s++){
                if(lagerAktuell.getArtikel(z, s)!=null){
                    lagerNeu.addArtikel(lagerAktuell.getArtikel(z, s), z, s);
                }
            }
        }
        setLager(lagerNeu);
        return lagerNeu;
    }

    /**
     * erstell String des Lagerbestandes
     * @return Strinh
     */
    public String lagertoString(){
        String lagerString = "";
        for(int zeile=0; zeile<lager.getMaxZeilen(); zeile++){
            for(int spalte=0; spalte<lager.getMaxSpalten(); spalte++){
                if(lager.getArtikel(zeile, spalte) != null){
                    lagerString += "Zeile: " + (zeile+1) + " Spalte: " + (spalte+1) + "    \t" + lager.getArtikel(zeile, spalte).toStringArtikelKurz() + "\n";
                }
            }
        }
        return lagerString;
    }




    /**
     *GET - SET Methoden
     */
    public void setLager(IDatenbank lager) {
        this.lager = lager;
    }

    public Lager getLager() {
        return (Lager) lager;
    }






}
