package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 13.10.2018
 *
 * Verwaltung des Lagers
 */
public class Verwaltung implements IBenutzer {

    private IDatenbank lager;

    public Verwaltung() {

    }

    /**
     * erstellt neues Lager (2 dimensionales Array)
     * @param horizontal
     * @param vertikal
     * @return IDatenbank Lager
     */
    public IDatenbank lagerErstellen(int horizontal, int vertikal) {
        this.lager = new Lager(horizontal, vertikal);
        return this.lager;
    }

    /**
     * prueft ob die Aenderung des Lagers moeglich ist
     * falls Lager verkleinert wird: ob ein Artikel weggeschnitten wird
     * @param lagerAktuell
     * @param vertikalNeu
     * @param horizontalNeu
     * @return boolean ob konfigurieren moeglich
     */
    public boolean pruefLagerKonfigMoeglich(Lager lagerAktuell, int vertikalNeu, int horizontalNeu){
        int vertikalAktuell = lagerAktuell.getVertikal();
        int horizontalAktuell = lagerAktuell.getHorizontal();
        boolean vertikalNeuMoeglich = false;
        boolean horizontalNeuMoeglich = false;

        boolean restNochFrei = true;
        boolean pruefSummeMoeglich = false;

        if(vertikalNeu>=vertikalAktuell){
            vertikalNeuMoeglich = true;
        }
        if(horizontalNeu>=horizontalAktuell){
            horizontalNeuMoeglich = true;
        }


        // wenn neu groesser
        if(horizontalNeuMoeglich==true && vertikalNeuMoeglich==true){
            pruefSummeMoeglich = true;
            return pruefSummeMoeglich;
        }
        // wenn neu schmaeler und laenger
        if(horizontalNeuMoeglich==false && vertikalNeuMoeglich==true) {
            for(int zeile=0; zeile<=vertikalAktuell-1;zeile++) {
                for (int spalte = horizontalNeu; spalte <= horizontalAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
        }
        // wenn neu breiter und kuerzer
        if(horizontalNeuMoeglich==true && vertikalNeuMoeglich==false){
            for (int zeile = vertikalNeu; zeile <= vertikalAktuell-1; zeile++) {
                for (int spalte = 0; spalte <= horizontalAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
        }
        // wenn neu schmaeler und kuerzer
        if(horizontalNeuMoeglich==false && vertikalNeuMoeglich==false){
            for (int zeile = vertikalNeu; zeile <= vertikalAktuell-1; zeile++) {
                for (int spalte = 0; spalte <= horizontalAktuell-1; spalte++) {
                    if (lagerAktuell.getArtikel(zeile, spalte) != null){
                        restNochFrei = false;
                        return restNochFrei;
                    }
                }
            }
            for(int zeile=0; zeile<=vertikalAktuell-1;zeile++) {
                for (int spalte = horizontalNeu; spalte <= horizontalAktuell-1; spalte++) {
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
     * @param horizontalNeu
     * @param vertikalNeu
     * @return IDatenbank neues Lager
     */
    public IDatenbank lagerKonfigurieren(Lager lagerAktuell, int horizontalNeu, int vertikalNeu) {
        int horizontalAktuell = lagerAktuell.getHorizontal();
        int vertikalAktuell = lagerAktuell.getVertikal();
        IDatenbank lagerNeu = new Lager(horizontalNeu, vertikalNeu);
        int zeile;
        int spalte;

        if(horizontalAktuell<horizontalNeu){
            zeile = horizontalAktuell;
        }
        else{
            zeile = horizontalNeu;
        }
        if(vertikalAktuell<vertikalNeu){
            spalte = vertikalAktuell;
        }
        else{
            spalte = vertikalNeu;
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
        for(int zeile=0; zeile<lager.getHorizontal(); zeile++){
            for(int spalte=0; spalte<lager.getVertikal(); spalte++){
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
