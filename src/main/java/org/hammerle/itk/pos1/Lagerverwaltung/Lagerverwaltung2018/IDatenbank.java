package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 13.10.2018
 *
 * IDatenbank wird implementiert von Lager
 */
public interface IDatenbank {

    public void addArtikel(Artikel artikel, int zeile, int spalte);

    /**
     * gibt Spaltenanzahl des Lagers wieder
     * @return int
     */
    int getHorizontal();

    /**
     * gibt Zeilenanzahl des Lagers wieder
     * @return int
     */
    int getVertikal();

    /**
     * gibt 2 dimensionales Array des Lagers wieder
     * @return Artikel[][]
     */
    Object[][] getLagerPlan();

    /**
     * gibt Artikel (Inhalt) einer bestimmten Lagerposition wieder
     * @param zeile
     * @param spalte
     * @return Artikel
     */
    Artikel getArtikel(int zeile, int spalte);

    /**
     * lagert neuen Artikel in naechst freier Position ein
     * @param milch
     * @param v
     * @param liter
     * @param tirol_milch
     * @param v1
     */
    void addArtikelNeuAutoPosition(String milch, double v, String liter, String tirol_milch, double v1);
}
