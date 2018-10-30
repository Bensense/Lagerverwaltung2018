package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

import java.util.ArrayList;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * POJO Klasse
 * Data Transfer Object
 * speziell zur Uebergabe von Artikeln
 */
public class DTO {
    private ArrayList<Artikel> listeArtikel;
    private String artikelBezeichnung;
    private int artikelNummer;
    private double verpackungsMenge;
    private String verpackungsEinheit;
    private String lieferant;
    private double preis;
    private int positionZeile;
    private int positionSpalte;

    /**
     * Konstruktor
     */
    public DTO() {
        listeArtikel = new ArrayList<>();
    }

    public DTO(String artikelBezeichnung, double verpackungsMenge, String verpackungsEinheit, String lieferant, double preis) {
        this.artikelBezeichnung = artikelBezeichnung;
        this.verpackungsMenge = verpackungsMenge;
        this.verpackungsEinheit = verpackungsEinheit;
        this.lieferant = lieferant;
        this.preis = preis;
    }

    public void addArtikel(Artikel artikel) {
        this.listeArtikel.add(artikel);
    }

    /**
     * GET - SET Methoden
     * @return
     */
    public ArrayList<Artikel> getListeArtikel() {
        return listeArtikel;
    }

    public void setListeArtikel(ArrayList<Artikel> listeArtikel) {
        this.listeArtikel = listeArtikel;
    }

    public String getArtikelBezeichnung() {
        return artikelBezeichnung;
    }

    public void setArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelBezeichnung = artikelBezeichnung;
    }

    public int getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public double getVerpackungsMenge() {
        return verpackungsMenge;
    }

    public void setVerpackungsMenge(double verpackungsMenge) {
        this.verpackungsMenge = verpackungsMenge;
    }

    public String getVerpackungsEinheit() {
        return verpackungsEinheit;
    }

    public void setVerpackungsEinheit(String verpackungsEinheit) {
        this.verpackungsEinheit = verpackungsEinheit;
    }

    public String getLieferant() {
        return lieferant;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getPositionZeile() {
        return positionZeile;
    }

    public void setPositionZeile(int positionZeile) {
        this.positionZeile = positionZeile;
    }

    public int getPositionSpalte() {
        return positionSpalte;
    }

    public void setPositionSpalte(int positionSpalte) {
        this.positionSpalte = positionSpalte;
    }
}
