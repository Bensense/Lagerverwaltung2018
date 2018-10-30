package org.hammerle.itk.pos1.Lagerverwaltung.Lagerverwaltung2018;

import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Benjamin Hammerle
 * @version 23.10.2018
 *
 * Menue fuer die Lagerverwaltung
 */
public class Menue {
    Scanner scan = new Scanner(System.in);
    private IBenutzer verwaltung;
    private HashMap<Integer, String> menuePunkte;
    private String hilfeText;

    /**
     * Konstruktor
     */
    public Menue() {
        verwaltung = new Verwaltung();
        menuePunkte = new HashMap<>();
    }

    /**
     * scant Integer ein mit Ueberpruefung
     * @return int
     */
    public int intEingabeBefehl() {
        int eingabe = 0;
        boolean korrekt;
        do{
            korrekt = true;
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
        boolean korrekt;
        do{
            korrekt = true;
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
        Integer befehlEingabe;
        String befehlAuswahl;
        int horizontal = 10;
        int vertikal = 10;
        verwaltung.lagerErstellen(horizontal, vertikal);
        fuelleHashMapMitMenuePunkte();
        addBeispielArtikel();

        do {
            System.out.println("************************LAGERVERWALTUNG************************");
            System.out.println("Aktuelle Lagergroesse: \t\t           Zeilen: " + verwaltung.getLager().getMaxZeilen() + "  Spalten: " + verwaltung.getLager().getMaxSpalten());
            System.out.println();
            System.out.println(" 1 = Lager konfigurieren");
            System.out.println(" 2 = Artikel einlagern an naechst freier Position");
            System.out.println(" 3 = Artikel einlagern an bestimmter Position");
            System.out.println(" 4 = Artikel ausliefern");
            System.out.println(" 5 = Artikel suchen via Artikelnummer");
            System.out.println(" 6 = Artikel suchen via Artikelbezeichnung");
            System.out.println(" 7 = Artikel aendern");
            System.out.println(" 8 = Lager Inventar ausgeben");
            System.out.println(" 9 = Lager Position Inhalt ausgeben");
            System.out.println("----------------------------------------------------------------");
            System.out.println("10 = Hilfe");
            System.out.println("11 = BEENDEN");
            System.out.println();
            do{
                System.out.print("Eingabe: ");
                befehlEingabe = intEingabeBefehl();
                System.out.println();
                if(befehlEingabe<1 || befehlEingabe>11) {
                    System.out.println("Eingabe ungueltig");
                }
            }while(befehlEingabe<1 || befehlEingabe>11);

            befehlAuswahl = menuePunkte.get(befehlEingabe);

            switch(befehlAuswahl){
                case "Lager konfigurieren": // 1
                    int zeilen, spalten;
                    boolean konfigMoeglich=false;
                    do {
                        System.out.println("********************LAGER KONFIGURIEREN***********************");
                        do{
                            System.out.print("Geben Sie die Anzahl der Zeilen ein: ");
                            zeilen = intEingabeBefehl();
                            if(zeilen<1) {
                                System.out.println("Eingabe ungueltig");
                            }
                        }while(zeilen<1);
                        do{
                            System.out.print("Geben Sie die Anzahl der Spalten ein: ");
                            spalten = intEingabeBefehl();
                            if(spalten<1) {
                                System.out.println("Eingabe ungueltig");
                            }
                        }while(spalten<1);

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

                case "Artikel einlagern an naechst freier Position": // 2
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
                    DTO artikelVO = new DTO(artikelBezeichnung1, verpackungsMenge1, verpackungsEinheit1, lieferant1, preis1);

                    System.out.println();
                    verwaltung.artikelEinlagernAutoPosition(artikelVO);
                    System.out.println("Artikel eingelagert");
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Artikel einlagern an bestimmter Position": // 3
                    boolean positionierenMoeglich;
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
                    DTO artikelVO2 = new DTO(artikelBezeichnung2, verpackungsMenge2, verpackungsEinheit2, lieferant2, preis2);

                    do{
                        System.out.println("POSITION WAEHLEN: ");
                        System.out.print("Zeile: ");
                        int zeile = intEingabeBefehl();
                        System.out.print("Spalte: ");
                        int spalte = intEingabeBefehl();
                        if(zeile <= verwaltung.getLager().getMaxZeilen() && spalte <= verwaltung.getLager().getMaxSpalten()){
                            if(verwaltung.getLager().getArtikel(zeile-1, spalte-1) == null){
                                verwaltung.artikelEinlagernSelectPosition(artikelVO2, zeile, spalte);
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

                case "Artikel ausliefern": // 4
                    int artikelNummerAuswahl;
                    System.out.println("***********************ARTIKEL AUSLIEFERN**********************");

                    for(int i=0; i < verwaltung.getAlleArtikel().getListeArtikel().size(); i++) {
                        System.out.println(verwaltung.getAlleArtikel().getListeArtikel().get(i).toStringArtikelKurz());
                    }
                    System.out.println();
                    System.out.print("Artikelnummer eingeben: ");
                    artikelNummerAuswahl = intEingabeBefehl();


                    if(verwaltung.getArtikelViaArtikelID(artikelNummerAuswahl).getListeArtikel().get(0)!= null){
                        System.out.println();
                        System.out.println("Artikel ausgeliefert:");

                        System.out.println(verwaltung.getArtikelViaArtikelID(artikelNummerAuswahl).getListeArtikel().get(0).toStringArtikelKomplett());

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

                case "Artikel suchen via Artikelnummer": // 5
                    System.out.println("***************ARTIKEL SUCHEN VIA ARTIKELNUMMER***************");
                    System.out.print("Artikelnummer eingeben: ");
                    int artikelNummerSuche = intEingabeBefehl();

                    if(verwaltung.getArtikelViaArtikelID(artikelNummerSuche).getListeArtikel().get(0)!= null){
                        System.out.println();
                        System.out.println("Gesuchter Artikel:");

                        System.out.println(verwaltung.getArtikelViaArtikelID(artikelNummerSuche).getListeArtikel().get(0).toStringArtikelKomplett());
                        System.out.println();
                    }
                    else{
                        System.out.println("Artikel nicht gefunden");
                        System.out.println();
                    }
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Artikel suchen via Artikelbezeichnung": // 6
                    System.out.println("*************ARTIKEL SUCHEN VIA ARTIKELBEZEICHNUNG************");
                    System.out.print("Artikelbezeichnung eingeben: ");
                    String artikelBezeichnungSuche = scan.next();
                    if(verwaltung.getArtikelViaArtikelBezeichnung(artikelBezeichnungSuche).getListeArtikel().size() > 0){
                        for(int i=0; i<verwaltung.getArtikelViaArtikelBezeichnung(artikelBezeichnungSuche).getListeArtikel().size(); i++) {
                            System.out.println(verwaltung.getArtikelViaArtikelBezeichnung(artikelBezeichnungSuche).getListeArtikel().get(i).toStringArtikelKurz());
                        }
                    }
                    else {
                        System.out.println("Keine Artikel mit der Bezeichnung '" + artikelBezeichnungSuche + "' gefunden");
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Artikel aendern": // 7
                    System.out.println("***********************ARTIKEL AENDERN************************");
                    System.out.print("Artikelnummer eingeben: ");
                    int artikelIDSuche = intEingabeBefehl();

                    if(verwaltung.getArtikelViaArtikelID(artikelIDSuche).getListeArtikel().get(0)!= null){
                        System.out.println();
                        System.out.println("Gesuchter Artikel:");

                        System.out.println(verwaltung.getArtikelViaArtikelID(artikelIDSuche).getListeArtikel().get(0).toStringArtikelKomplett());
                        System.out.println();

                        System.out.print("Neue Artikelbezeichnung: ");
                        String artikelBezeichnungNeu = scan.next();
                        System.out.print("Verpackungsmenge(double): ");
                        double verpackungsMengeNeu = doubleEingabeBefehl();
                        System.out.print("Verpackungseinheit: ");
                        String verpackungsEinheitNeu = scan.next();
                        System.out.print("Lieferant: ");
                        String lieferantNeu = scan.next();
                        System.out.print("Preis(double): ");
                        double preisNeu = doubleEingabeBefehl();
                        DTO artikelVeraendert = new DTO(artikelBezeichnungNeu, verpackungsMengeNeu, verpackungsEinheitNeu, lieferantNeu, preisNeu);
                        verwaltung.artikelAendern(artikelVeraendert, artikelIDSuche);

                        System.out.println();
                        System.out.println("Artikel geaendert");
                    }
                    else{
                        System.out.println("Artikel nicht gefunden");
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Lager Inventar ausgeben": // 8
                    System.out.println("*****************LAGER INVENTAR AUSGEBEN**********************");
                    String lagerString = "";
                    for(int i=0; i < verwaltung.getAlleArtikel().getListeArtikel().size(); i++) {
                        lagerString += verwaltung.getAlleArtikel().getListeArtikel().get(i).toStringArtikelKurz();
                        System.out.println(verwaltung.getAlleArtikel().getListeArtikel().get(i).toStringArtikelKurz());
                    }

                    if(lagerString == ""){
                        System.out.println();
                        System.out.println("Keine Artikel im Lager");
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Lager Position Inhalt ausgeben": // 9
                    System.out.println("***************LAGER POSITION INHALT AUSGEBEN*****************");
                    int zeile, spalte;
                    do {
                        System.out.print("Zeile eingegen: ");
                        zeile = intEingabeBefehl();
                        if(zeile<=0) {
                            System.out.println("Eingabe ungueltig");
                        }
                    }while(zeile<=0);
                    do {
                        System.out.print("Spalte eingeben: ");
                        spalte = intEingabeBefehl();
                        if(zeile<=0) {
                            System.out.println("Eingabe ungueltig");
                        }
                    }while(spalte<=0);

                    int indexZeile = zeile-1;
                    int indexSpalte = spalte-1;

                    if(verwaltung.getPositionInhalt(indexZeile, indexSpalte) != null){
                        System.out.println(verwaltung.getPositionInhalt(indexZeile, indexSpalte).getListeArtikel().get(0).toStringArtikelKomplett());
                    }
                    else{
                        System.out.println("Lagerposition ist noch frei");
                    }
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "Hilfe": // 10
                    System.out.println("**************************HILFE*******************************");
                    System.out.println(getHilfeText());
                    System.out.println();
                    System.out.println("**************************************************************");
                    System.out.println();
                    break;

                case "BEENDEN": // 11
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
        this.hilfeText += " 1 = Lager konfigurieren \n";
        this.hilfeText += "     Veraendert die Anzahl an Zeilen und Spalten \n";
        this.hilfeText += " 2 = Artikel einlagern an naechst freier Position \n";
        this.hilfeText += "     Neuen Artikel anlegen und an naechst freier Position einlagern \n";
        this.hilfeText += " 3 = Artikel einlagern an bestimmter Position \n";
        this.hilfeText += "     Neuen Artikel anlegen und an bestimmter Position einlagern \n";
        this.hilfeText += " 4 = Artikel ausliefern \n";
        this.hilfeText += "     Artikel via Artikelnummer aus Lagerbestand auswaehlen und entnehmen \n";
        this.hilfeText += " 5 = Artikel suchen via Artikelnummer \n";
        this.hilfeText += "     Artikel via Artikelnummer im Lager suchen und Position ausgeben \n";
        this.hilfeText += " 6 = Artikel suchen via Artikelbezeichnung \n";
        this.hilfeText += "     Alle Artikel mit einer bestimmten Artikelbezeichnung suchen und ausgeben \n";
        this.hilfeText += " 7 = Artikel aendern \n";
        this.hilfeText += "     Artikel suchen via Artikelnummer und Angaben aendern \n";
        this.hilfeText += " 8 = Lager Inventar ausgeben \n";
        this.hilfeText += "     Gibt kompletten Lagerbestand mit Position aus \n";
        this.hilfeText += " 9 = Lager Position Inhalt ausgeben \n";
        this.hilfeText += "     Zeigt Inhalt einer bestimmten Lager Position an (via Position) \n";
        this.hilfeText += "------------------------------------------------------------------------- \n";
        this.hilfeText += "10 = Hilfe \n";
        this.hilfeText += "     Gibt Hilfe Text zum Menue aus \n";
        this.hilfeText += "11 = BEENDEN \n";
        this.hilfeText += "     Beendet das Programm";
    }

    /**
     * fuellt HashMap mit allen MenuePunkten
     */
    public void fuelleHashMapMitMenuePunkte() {
        menuePunkte.put(1, "Lager konfigurieren");
        menuePunkte.put(2, "Artikel einlagern an naechst freier Position");
        menuePunkte.put(3, "Artikel einlagern an bestimmter Position");
        menuePunkte.put(4, "Artikel ausliefern");
        menuePunkte.put(5, "Artikel suchen via Artikelnummer");
        menuePunkte.put(6, "Artikel suchen via Artikelbezeichnung");
        menuePunkte.put(7, "Artikel aendern");
        menuePunkte.put(8, "Lager Inventar ausgeben");
        menuePunkte.put(9, "Lager Position Inhalt ausgeben");
        menuePunkte.put(10, "Hilfe");
        menuePunkte.put(11, "BEENDEN");
    }

    /**
     * fuellt Lager mit Beispielartikel
     */
    public void addBeispielArtikel(){
        DTO beispielArtikel1 = new DTO("Milch", 0.5, "Liter", "Tirol Milch", 1.20);
        DTO beispielArtikel2 = new DTO("Semmel", 5.0, "Stueck", "Baecker", 1.80);
        DTO beispielArtikel3 = new DTO("Banane", 0.3, "kg   ", "Banana Joe", 1.20);
        DTO beispielArtikel4 = new DTO("Wurst", 0.4, "kg   ", "Metzger", 3.50);
        DTO beispielArtikel5 = new DTO("Salat", 1.0, "Stueck", "vom Baua", 1.20);
        DTO beispielArtikel6 = new DTO("Brot", 1.0, "Stueck", "Tirol Brot", 2.20);

        verwaltung.artikelEinlagernAutoPosition(beispielArtikel1);
        verwaltung.artikelEinlagernAutoPosition(beispielArtikel2);
        verwaltung.artikelEinlagernAutoPosition(beispielArtikel3);
        verwaltung.artikelEinlagernAutoPosition(beispielArtikel4);
        verwaltung.artikelEinlagernAutoPosition(beispielArtikel5);
        verwaltung.artikelEinlagernAutoPosition(beispielArtikel6);
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
