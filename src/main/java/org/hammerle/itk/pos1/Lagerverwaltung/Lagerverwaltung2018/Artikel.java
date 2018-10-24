package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;


/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * Artikel die eingerlagert werden koennen
 */
public class Artikel {
    private String artikelBezeichnung;
    private int artikelNummer;
    private double verpackungsMenge;
    private String verpackungsEinheit;
    private Lieferant lieferant;
    private double preis;

    /**
     * Konstruktor
     *
     * @param artikelBezeichnung
     * @param artikelNummer
     * @param verpackungsMenge
     * @param verpackungsEinheit
     * @param lieferant
     * @param preis
     */
    public Artikel(String artikelBezeichnung, int artikelNummer, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis) {
        this.artikelBezeichnung = artikelBezeichnung;
        this.artikelNummer = artikelNummer;
        this.verpackungsMenge = verpackungsMenge;
        this.verpackungsEinheit = verpackungsEinheit;
        this.lieferant = new Lieferant(lieferant);
        this.preis = preis;
    }


    /**
     * erstellt einzeiligen String eines Artikels
     * @return String Artikel
     */
    public String toStringArtikelKurz() {
        String artikelString = "Artikelnummer: " + getArtikelNummer() + "\t Artikelbezeichnung: " + getArtikelBezeichnung() + "\t Verpackungseinheit: " + getVerpackungsMenge() + " "+ getVerpackungsEinheit() + "\t Preis: " + getPreis() +" Euro";
        return artikelString;
    }

    /**
     * erstellt mehrzeiligen String eines Artikels
     * @return String
     */
    public String toStringArtikelKomplett() {
        String artikelString = "";
        artikelString += "Artikelbezeichnung: " + getArtikelBezeichnung() + "\n";
        artikelString += "Artikelnummer:      " + getArtikelNummer() + "\n";
        artikelString += "Verpackungeinheit:  " + getVerpackungsMenge() + " " + getVerpackungsEinheit() + "\n";
        artikelString += "Lieferant:          " + getLieferant().getFirma() + "\n";
        artikelString += "Preis:              " + getPreis() + " Euro";
        return artikelString;
    }


    /**
     * GET - SET Methoden
     */
    public void setArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelBezeichnung = artikelBezeichnung;
    }

    public void setArtikelNummer(int artikelNummer) {
        artikelNummer = artikelNummer;
    }

    public void setVerpackungsMenge(double verpackungsMenge) {
        this.verpackungsMenge = verpackungsMenge;
    }

    public void setVerpackungsEinheit(String verpackungsEinheit) {
        this.verpackungsEinheit = verpackungsEinheit;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getArtikelBezeichnung() {
        return artikelBezeichnung;
    }

    public int getArtikelNummer() {
        return artikelNummer;
    }

    public double getVerpackungsMenge() {
        return verpackungsMenge;
    }

    public String getVerpackungsEinheit() {
        return verpackungsEinheit;
    }

    public Lieferant getLieferant() {
        return lieferant;
    }

    public double getPreis() {
        return preis;
    }
}
