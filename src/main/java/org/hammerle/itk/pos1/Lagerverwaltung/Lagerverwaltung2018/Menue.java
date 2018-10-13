package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Benjamin Hammerle
 * @version 13.10.2018
 *
 * Menue fuer die Lagerverwaltung
 */
public class Menue {
    Scanner scan = new Scanner(System.in);
    IBenutzer verwaltung;
    private int BefehlEingabe;
    private String hilfeText;

    public Menue() {
        verwaltung = new Verwaltung();
    }

    /**
     * scant Integer ein mit Ueberpruefung
     * @return int
     */
    public int intEingabeBefehl() {
        int eingabe = 0;
        boolean korrekt = false;
        do{
            korrekt = true;
            // System.out.print("Eingabe: ");
            try {
                eingabe = scan.nextInt();
            }
            catch(InputMismatchException e) {
                scan.next();
                System.out.println("Falsche Eingabe");
                korrekt = false;
            }
        }while(korrekt==false);

        return eingabe;
    }

    /**
     * scant Double ein mit Uberpruefung
     * @return double
     */
    public double doubleEingabeBefehl() {
        double eingabe = 0.0;
        boolean korrekt = false;
        do{
            korrekt = true;
            // System.out.print("Eingabe: ");
            try {
                eingabe = scan.nextDouble();
            }
            catch(InputMismatchException e) {
                scan.next();
                System.out.println("Falsche Eingabe");
                korrekt = false;
            }
        }while(korrekt==false);

        return eingabe;
    }

    /**
     * startet das Menue der Lagerverwaltung
     */
    public void starteMenue(){
        setHilfeText();
        boolean menueBeenden = false;
        int befehlEingabe;
        int horizontal = 10;
        int vertikal = 10;
        verwaltung.lagerErstellen(horizontal, vertikal);
        verwaltung.addBeispielArtikel();

        do {
            System.out.println("*******************LAGERVERWALTUNG*******************");
            System.out.println("Aktuelle Lagergroesse: \t\t Zeilen: " + verwaltung.getLager().getHorizontal() + "  Spalten: " + verwaltung.getLager().getVertikal());
            System.out.println("1 = Lager konfigurieren");
            System.out.println("2 = Artikel einlagern an naechst freier Position");
            System.out.println("3 = Artikel einlagern an bestimmter Position");
            System.out.println("4 = Artikel ausliefern");
            System.out.println("5 = Artikel suchen via Artikelnummer");
            System.out.println("6 = Lager Inventar ausgeben");
            System.out.println("7 = Lager Position Inhalt ausgeben");
            System.out.println("------------------------------------------------------");
            System.out.println("8 = Hilfe");
            System.out.println("9 = BEENDEN");
            System.out.println();
            System.out.print("Eingabe: ");
            befehlEingabe = intEingabeBefehl();
            System.out.println();

            switch(befehlEingabe){
                case 1:
                    int zeilen, spalten;
                    boolean konfigMoeglich=false;
                    do {
                        System.out.println("********************LAGER KONFIGURIEREN***********************");
                        System.out.print("Geben Sie die Anzahl der Zeilen ein: ");
                        zeilen = intEingabeBefehl();
                        System.out.print("Geben Sie die Anzahl der Spalten ein: ");
                        spalten = intEingabeBefehl();
                        konfigMoeglich = verwaltung.pruefLagerKonfigMoeglich(verwaltung.getLager(), zeilen, spalten);
                        if(konfigMoeglich==false){
                            System.out.println("Lager verkleinern nicht moeglich!");
                        }
                    }while(konfigMoeglich==false);
                    verwaltung.lagerKonfigurieren(verwaltung.getLager(), zeilen, spalten);
                    System.out.println("Lager konfiguriert");
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 2:
                    System.out.println("*********ARTIKEL EINLAGERN AN NAECHST FREIER POSITION*********");
                    System.out.print("Artikelbezeichnung: ");
                    String artikelBezeichnung1 = scan.next();
                    System.out.print("Verpackungsmenge(double): ");
                    double verpackungsMenge1 = doubleEingabeBefehl();
                    System.out.print("Verpackungseinheit: ");
                    String verpackungsEinheit1 = scan.next();
                    System.out.print("Lieferant: ");
                    String lieferant1 = scan.next();
                    System.out.print("Preis(double): ");
                    double preis1 = doubleEingabeBefehl();
                    System.out.println();
                    verwaltung.getLager().addArtikelNeuAutoPosition(artikelBezeichnung1, verpackungsMenge1, verpackungsEinheit1, lieferant1, preis1);
                    System.out.println("Artikel eingelagert");
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 3:
                    boolean positionierenMoeglich = false;
                    System.out.println("***********ARTIKEL EINLAGERN AN BESTIMMTER POSITION***********");
                    System.out.print("Artikelbezeichnung: ");
                    String artikelBezeichnung2 = scan.next();
                    System.out.print("Verpackungsmenge(double): ");
                    double verpackungsMenge2 = doubleEingabeBefehl();
                    System.out.print("Verpackungseinheit: ");
                    String verpackungsEinheit2 = scan.next();
                    System.out.print("Lieferant: ");
                    String lieferant2 = scan.next();
                    System.out.print("Preis(double): ");
                    double preis2 = doubleEingabeBefehl();
                    do{
                        System.out.println("POSITION WAEHLEN: ");
                        System.out.print("Zeile: ");
                        int zeile = intEingabeBefehl();
                        System.out.print("Spalte: ");
                        int spalte = intEingabeBefehl();
                        if(zeile <= verwaltung.getLager().getVertikal() && spalte <= verwaltung.getLager().getHorizontal()){
                            if(verwaltung.getLager().getArtikel(zeile, spalte) == null){
                                verwaltung.getLager().addArtikelNeuSelectPosition(artikelBezeichnung2, verpackungsMenge2, verpackungsEinheit2, lieferant2, preis2, zeile, spalte);
                                System.out.println("Artikel eingelagert");
                                positionierenMoeglich = true;
                            }
                            else{
                                System.out.println("Position schon belegt");
                                positionierenMoeglich = false;
                            }
                        }
                        else{
                            System.out.println("Position nicht existent");
                            positionierenMoeglich = false;
                        }
                    }while(positionierenMoeglich==false);
                    break;

                case 4:
                    int artikelNummerAuswahl;
                    String artikelAlt = null;
                        System.out.println("***********************ARTIKEL AUSLIEFERN**********************");
                        for(int zeile=0; zeile<verwaltung.getLager().getHorizontal(); zeile++){
                            for(int spalte=0; spalte<verwaltung.getLager().getVertikal(); spalte++){
                                if(verwaltung.getLager().getArtikel(zeile, spalte)!=null){
                                    System.out.println(verwaltung.getLager().getArtikel(zeile, spalte).toStringArtikelKurz());
                                }
                            }
                        }
                        System.out.println();
                        System.out.print("Artikelnummer eingeben: ");
                        artikelNummerAuswahl = intEingabeBefehl();
                        artikelAlt = verwaltung.getLager().sucheArtikel(artikelNummerAuswahl);

                    if(artikelAlt != null){
                        System.out.println();
                        System.out.println("Artikel ausgeliefert:");
                        System.out.println(verwaltung.getLager().getArtikelMitNummer(artikelNummerAuswahl).toStringArtikelKomplett());
                        verwaltung.getLager().removeArtikel(artikelNummerAuswahl);

                        System.out.println();
                    }
                    else{
                        System.out.println();
                        System.out.println("Artikel nicht gefunden");
                        System.out.println();
                    }

                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 5:
                    System.out.println("***********************ARTIKEL SUCHEN**************************");
                    System.out.print("Artikelnummer eingeben: ");
                    int artikelNummerSuche = intEingabeBefehl();
                    String gesuchterArtikel = verwaltung.getLager().sucheArtikel(artikelNummerSuche);
                    if(gesuchterArtikel!=null){
                        System.out.println(gesuchterArtikel);
                        System.out.println();
                    }
                    else{
                        System.out.println("Artikel nicht gefunden");
                        System.out.println();
                    }
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 6:
                    System.out.println("*****************LAGER INVENTAR AUSGEBEN**********************");
                    String lagerString = verwaltung.lagertoString();
                    System.out.println(lagerString);
                    if(lagerString == ""){
                        System.out.println();
                        System.out.println("Keine Artikel im Lager");
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 7:
                    System.out.println("***************LAGER POSITION INHALT AUSGEBEN*****************");
                    System.out.print("Zeile eingegen: ");
                    int zeile = intEingabeBefehl();
                    System.out.print("Spalte eingeben: ");
                    int spalte = intEingabeBefehl();
                    System.out.println("Position: Zeile: " + zeile + " Spalte: " + spalte);
                    if(verwaltung.getLager().getArtikel(zeile, spalte) != null){
                        System.out.println(verwaltung.getLager().getArtikel(zeile, spalte).toStringArtikelKomplett());
                    }
                    else{
                        System.out.println("Lagerposition ist noch frei");
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;
                case 8:
                    System.out.println("**************************HILFE*******************************");
                    System.out.println(getHilfeText());
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case 9:
                    menueBeenden = true;
                    break;
                default:
                    System.out.println("Eingabe ungueltig!");
                    break;
            }
        }while(menueBeenden==false);

        System.out.println("Auf Wiedersehen!");

    }

    /**
     * erstellt Hilfetext fuer das Menue der Lagerverwaltung
     */
    public void setHilfeText(){
        this.hilfeText = "";
        this.hilfeText += "1 = Lager konfigurieren \n";
        this.hilfeText += "    Veraendert die Anzahl an Zeilen und Spalten \n";
        this.hilfeText += "2 = Artikel einlagern an naechst freier Position \n";
        this.hilfeText += "    Neuen Artikel anlegen und an naechst freier Position einlagern \n";
        this.hilfeText += "3 = Artikel einlagern an bestimmter Position \n";
        this.hilfeText += "    Neuen Artikel anlegen und an bestimmter Position einlagern \n";
        this.hilfeText += "4 = Artikel ausliefern \n";
        this.hilfeText += "    Artikel via Artikelnummer aus Lager auswaehlen und entnehmen \n";
        this.hilfeText += "5 = Artikel suchen via Artikelnummer \n";
        this.hilfeText += "    Artikel via Artikelnummer im Lager suchen und Position ausgeben \n";
        this.hilfeText += "6 = Lager Inventar ausgeben \n";
        this.hilfeText += "    Gibt kompletten Lagerbestand aus \n";
        this.hilfeText += "7 = Lager Position Inhalt ausgeben \n";
        this.hilfeText += "    Zeigt Inhalt einer bestimmten Lager Position an (via Position) \n";
        this.hilfeText += "------------------------------------------------------------------------- \n";
        this.hilfeText += "8 = Hilfe \n";
        this.hilfeText += "    Gibt Hilfe Text zum Menue aus \n";
        this.hilfeText += "9 = BEENDEN \n";
        this.hilfeText += "    Beendet das Programm";
    }

    /**
     * GET - SET Methoden
     * @return
     */
    public String getHilfeText(){
        return hilfeText;
    }

    public void setVerwaltung(IBenutzer verwaltung) {
        this.verwaltung = verwaltung;
    }

    public IBenutzer getVerwaltung() {
        return verwaltung;
    }
}
