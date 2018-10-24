package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * Lieferant der Artikel
 */
public class Lieferant {
    private String firma;


    /**
     * Konstruktor
     *
     * @param firma
     */
    public Lieferant(String firma) {
        this.firma = firma;
    }


    /**
     * GET - SET Methoden
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }
    public String getFirma() {
        return firma;
    }
}
