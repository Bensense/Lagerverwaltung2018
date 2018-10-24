package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
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
     * fuegt Artikel in naechst freier Lagerposition ein
     *
     * @param artikelNeu
     */
    void addArtikelNeuAutoPosition(Artikel artikelNeu);

    /**
     * fuegt Artikel in bestimmter Lagerposition ein
     *
     * @param artikelNeu
     * @param zeile
     * @param spalte
     */
    void addArtikelNeuSelectPosition(Artikel artikelNeu, int zeile, int spalte);

    /**
     * gibt Spaltenanzahl des Lagers wieder
     * @return int
     */
    int getMaxSpalten();

    /**
     * gibt Zeilenanzahl des Lagers wieder
     * @return int
     */
    int getMaxZeilen();

    /**
     * gibt Artikel (Inhalt) einer bestimmten Lagerposition wieder
     * @param zeile
     * @param spalte
     * @return Artikel
     */
    Artikel getArtikel(int zeile, int spalte);


}
