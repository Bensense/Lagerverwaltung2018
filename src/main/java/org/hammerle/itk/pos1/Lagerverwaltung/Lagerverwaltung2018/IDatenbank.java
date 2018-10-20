package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 13.10.2018
 *
 * IDatenbank wird implementiert von Lager
 */
public interface IDatenbank {

    /**
     * lagert einen Artikel im Lager an einer bestimmten Position
     * @param artikel
     * @param zeile
     * @param spalte
     */
    void addArtikel(Artikel artikel, int zeile, int spalte);

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
     * gibt Artikel (Inhalt) einer bestimmten Lagerposition wieder
     * @param zeile
     * @param spalte
     * @return Artikel
     */
    Artikel getArtikel(int zeile, int spalte);
}
