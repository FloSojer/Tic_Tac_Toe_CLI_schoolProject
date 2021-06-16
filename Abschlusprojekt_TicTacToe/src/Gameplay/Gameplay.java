package Gameplay;

import Exceptions.SaveDataException;
import Player.PHuman;
import Player.PComputer;
import Player.SpielerListe;
import TicTacToe.Spielfeld;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Gameplay implements Serializable {
    private String nameSp1, zeichenSp1, nameSp2, zeichenSp2, zeichenPC;
    private int rundenZahlSp1 = 0, rundenZahlSp2 = 0;
    private String winner, looser;
    private boolean zuletztgespieltPlr1, zuletztgespieltPlr2, zuletztgespieltPC ,spielGegenPC = false;
    PHuman plr1, plr2;
    PComputer plrPC;
    Spielfeld spielfeld = new Spielfeld();
    SpielerListe spielerListe = new SpielerListe();

    public Gameplay() throws SaveDataException {
        spielerSetzen();
        werFaengtAn();
    }
    private void spielerSetzen(){
        boolean zeichenRichtig = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Spieler 1, bitte geben Sie ihren Namen ein: ");
        nameSp1 = scan.nextLine();
        while (!zeichenRichtig){
            System.out.println("Welches Zeichen wuerden Sie gerne verwenden? (X oder O)");
            zeichenSp1 = scan.nextLine();
            if(zeichenSp1.equals("O") || zeichenSp1.equals("o") || zeichenSp1.equals("0")){
                zeichenSp1 = "O";
                zeichenSp2 = "X";
                zeichenPC = "X";
                zeichenRichtig = true;
            }
            else if(zeichenSp1.equals("x") || zeichenSp1.equals("X")){
                zeichenSp1 = "X";
                zeichenSp2 = "O";
                zeichenPC = "O";
                zeichenRichtig = true;
            }
            else{
                System.out.println("Ihre Eingabe war leider Falsch, bitte wiederholen Sie diese!");
            }

        }
        boolean eingabeRichtig = false;
        while(!eingabeRichtig){
            System.out.println("Moechten sie gegen einen PC oder gegen eine Person spielen? (PC / pers)");
            System.out.println("Spiele gegen einen PC werden nicht gewertet und gespeichert! Dies sind nur Trainingsspiele!");
            String nextPer = scan.nextLine();
            if(nextPer.equals("PC") || nextPer.equals("pc") || nextPer.equals("Pc") || nextPer.equals("pC")){
                this.plr1 = new PHuman(this.nameSp1, this.zeichenSp1);
                this.plrPC = new PComputer(this.zeichenPC);
                eingabeRichtig = true;
                this.spielGegenPC = true;
            }
            else if(nextPer.equals("Pers") || nextPer.equals("pers") || nextPer.equals("PERS") || nextPer.equals("pErs") || nextPer.equals("peRs")|| nextPer.equals("perS") || nextPer.equals("person") || nextPer.equals("Person")){
                System.out.println("Spieler 2, bitte geben Sie Ihren Namen ein: ");
                nameSp2 = scan.nextLine();
                System.out.println("Das zeichen von " + nameSp2 + " ist " + getZeichenSp2());

                this.plr1 = new PHuman(this.nameSp1, this.zeichenSp1);
                this.plr2 = new PHuman(this.nameSp2, this.zeichenSp2);

                eingabeRichtig = true;
            }
            else {
                System.out.println("Ihre Eingabe war leider Falsch, bitte wiederholen Sie diese!");
            }
        }
    }

    private void werFaengtAn() throws SaveDataException {
        Random rand = new Random();
        boolean person =  rand.nextBoolean();
        if(!spielGegenPC){
            if(person){
                System.out.println("  ");
                System.out.println(plr1.getName() + " faengt an!");
                rundePlr1();
            }
            else{
                System.out.println("  ");
                System.out.println(plr2.getName() + " faengt an!");
                rundePlr2();
            }
        }
        else{
            if(person){
                System.out.println("  ");
                System.out.println(plr1.getName() + " faengt an!");
                rundePlr1();
            }
            else{
                System.out.println("  ");
                System.out.println("Der PC faengt an!");
                rundePC();
            }
        }
    }

    private void rundePlr1() throws SaveDataException {
        Scanner scan = new Scanner(System.in);

        System.out.println(plr1.getName() + " bitte geben Sie die Koordinate an: ");
        String koordinate = scan.nextLine();
        spielfeld.setFeld(koordinate, plr1.getZeichen());
        if(!spielfeld.isGueltigeEingabe()){
            System.out.println("Ihre Eingabe ist ungueltig! Bitte geben Sie erneut ein: ");
            spielfeld.setGueltigeEingabe(true);
            rundePlr1();
        }
        if(!spielfeld.isBelegtesFeld()){
            rundenZahlSp1++;
            spielfeld.spielfeldAnzeigen();
            if(spielGegenPC){
                zuletztgespieltPlr1 = true;
                zuletztgespieltPC = false;
                checkForWinvsPC();
            }
            else{
                zuletztgespieltPlr1 = true;
                zuletztgespieltPlr2 = false;
                checkForWin();
            }

        }
        else{
            System.out.println("Diese Feld ist bereits belegt! Bitte geben Sie erneut ein: ");
            spielfeld.setBelegtesFeld(false);
            rundePlr1();
        }

    }

    private void rundePlr2() throws SaveDataException {
        Scanner scan = new Scanner(System.in);
        zuletztgespieltPlr2 = true;
        zuletztgespieltPlr1 = false;
        System.out.println( plr2.getName() + " bitte geben Sie die Koordinate an: ");
        String koordinate = scan.nextLine();
        spielfeld.setFeld(koordinate, plr2.getZeichen());
        if(!spielfeld.isGueltigeEingabe()){
            System.out.println("Ihre Eingabe ist ungueltig! Bitte geben Sie erneut ein: ");
            spielfeld.setGueltigeEingabe(true);
            rundePlr2();
        }
        if(!spielfeld.isBelegtesFeld()){
            rundenZahlSp2++;
            spielfeld.spielfeldAnzeigen();
            checkForWin();
        }
        else{
            System.out.println("Diese Feld ist bereits belegt! Bitte geben Sie erneut ein: ");
            spielfeld.setBelegtesFeld(false);
            rundePlr2();
        }
    }

    private void rundePC(){
        Random rand = new Random();
        int zufall = rand.nextInt(8);
        System.out.println("Zeichen PC "+ plrPC.getZeichen());
        String koordinatePC = " ";
        switch (zufall){
            case 0:
                koordinatePC="ol";
                break;
            case 1:
                koordinatePC="om";
                break;
            case 2:
                koordinatePC="or";
                break;
            case 3:
                koordinatePC="ml";
                break;
            case 4:
                koordinatePC="mm";
                break;
            case 5:
                koordinatePC="mr";
                break;
            case 6:
                koordinatePC="ul";
                break;
            case 7:
                koordinatePC="um";
                break;
            case 8:
                koordinatePC="ur";
                break;
            default:
                koordinatePC="ol";
                break;

        }
        spielfeld.setFeld(koordinatePC, plrPC.getZeichen());
        if(!spielfeld.isBelegtesFeld()){
            spielfeld.spielfeldAnzeigen();
            zuletztgespieltPlr1 = false;
            zuletztgespieltPC = true;
            checkForWinvsPC();
        }
        else{
            System.out.println("Diese Feld ist bereits belegt! PC waehlt neues.");
            spielfeld.setBelegtesFeld(false);
            rundePC();
        }

    }

    public void checkForWin() throws SaveDataException {
        String[] field = spielfeld.getFeld();
        if(field[0].equals(getZeichenSp1()) && field[1].equals(getZeichenSp1()) && field[2].equals(getZeichenSp1()) || //Hor 1. Linie
                field[3].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[5].equals(getZeichenSp1()) || //Hor 2. Linie
                field[6].equals(getZeichenSp1()) && field[7].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1()) || //Hor 3. Linie
                    field[0].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1()) || //Ver. von Links oben nach Rechts unten
                    field[2].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[6].equals(getZeichenSp1()) || //Ver. von Rechts oben nach Links unten
            field[0].equals(getZeichenSp1()) && field[3].equals(getZeichenSp1()) && field[6].equals(getZeichenSp1()) || //Ver. 1. Reihe
            field[1].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[7].equals(getZeichenSp1()) || //Ver 2. Reihe
            field[2].equals(getZeichenSp1()) && field[5].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1())    //Ver 3. Reihe
        ){
            winner = plr1.getName();
            looser =plr2.getName();
            System.out.println(" Gratuliere " + winner + "! Du hast gewonnen! ");
            plr1.setRundenzahl(rundenZahlSp1);
            plr2.setRundenzahl(rundenZahlSp2);
            plr1.setWins(1);
            plr2.setWins(0);
            plr1.setLosts(0);
            plr2.setLosts(1);
            spielerListe.neueSpieler(plr1, plr2);

        }
        else if(field[0].equals(getZeichenSp2()) && field[1].equals(getZeichenSp2()) && field[2].equals(getZeichenSp2()) || //Hor 1. Linie
                field[3].equals(getZeichenSp2()) && field[4].equals(getZeichenSp2()) && field[5].equals(getZeichenSp2()) || //Hor 2. Linie
                field[6].equals(getZeichenSp2()) && field[7].equals(getZeichenSp2()) && field[8].equals(getZeichenSp2()) || //Hor 3. Linie
                field[0].equals(getZeichenSp2()) && field[4].equals(getZeichenSp2()) && field[8].equals(getZeichenSp2()) || //Ver. von Links oben nach Rechts unten
                field[2].equals(getZeichenSp2()) && field[4].equals(getZeichenSp2()) && field[6].equals(getZeichenSp2()) || //Ver. von Rechts oben nach Links unten
                field[0].equals(getZeichenSp2()) && field[3].equals(getZeichenSp2()) && field[6].equals(getZeichenSp2()) || //Ver. 1. Reihe
                field[1].equals(getZeichenSp2()) && field[4].equals(getZeichenSp2()) && field[7].equals(getZeichenSp2()) || //Ver 2. Reihe
                field[2].equals(getZeichenSp2()) && field[5].equals(getZeichenSp2()) && field[8].equals(getZeichenSp2())    //Ver 3. Reihe
        ){
            winner = plr2.getName();
            looser = plr1.getName();
            System.out.println(" Gratuliere " + winner + "! Du hast gewonnen! ");
            plr1.setRundenzahl(rundenZahlSp1);
            plr2.setRundenzahl(rundenZahlSp2);
            plr1.setWins(0);
            plr2.setWins(1);
            plr1.setLosts(1);
            plr2.setLosts(0);
            spielerListe.neueSpieler(plr1, plr2);

        }else if(!field[0].equals(" ") && !field[1].equals(" ") && !field[2].equals(" ") &&
                !field[3].equals(" ") && !field[4].equals(" ") && !field[5].equals(" ") &&
                !field[6].equals(" ") && !field[7].equals(" ") && !field[8].equals(" ")){
            System.out.println(" Das Spiel ist unentschieden!");
            plr1.setRundenzahl(rundenZahlSp1);
            plr2.setRundenzahl(rundenZahlSp2);
            plr1.setWins(0);
            plr2.setWins(0);
            plr1.setLosts(0);
            plr2.setLosts(0);
            spielerListe.neueSpieler(plr1, plr2);
        }
        else{
            if (zuletztgespieltPlr1) {
                    rundePlr2();
            } else if (zuletztgespieltPlr2) {
                    rundePlr1();
            }
        }

    }

    public void checkForWinvsPC(){
        String[] field = spielfeld.getFeld();
        if(field[0].equals(getZeichenSp1()) && field[1].equals(getZeichenSp1()) && field[2].equals(getZeichenSp1()) || //Hor 1. Linie
                field[3].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[5].equals(getZeichenSp1()) || //Hor 2. Linie
                field[6].equals(getZeichenSp1()) && field[7].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1()) || //Hor 3. Linie
                field[0].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1()) || //Ver. von Links oben nach Rechts unten
                field[2].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[6].equals(getZeichenSp1()) || //Ver. von Rechts oben nach Links unten
                field[0].equals(getZeichenSp1()) && field[3].equals(getZeichenSp1()) && field[6].equals(getZeichenSp1()) || //Ver. 1. Reihe
                field[1].equals(getZeichenSp1()) && field[4].equals(getZeichenSp1()) && field[7].equals(getZeichenSp1()) || //Ver 2. Reihe
                field[2].equals(getZeichenSp1()) && field[5].equals(getZeichenSp1()) && field[8].equals(getZeichenSp1())    //Ver 3. Reihe
        ){

            System.out.println(" Gratuliere " + plr1.getName() + "! Du hast gewonnen! ");
        }
        else if(field[0].equals(getZeichenPC()) && field[1].equals(getZeichenPC()) && field[2].equals(getZeichenPC()) || //Hor 1. Linie
                field[3].equals(getZeichenPC()) && field[4].equals(getZeichenPC()) && field[5].equals(getZeichenPC()) || //Hor 2. Linie
                field[6].equals(getZeichenPC()) && field[7].equals(getZeichenPC()) && field[8].equals(getZeichenPC()) || //Hor 3. Linie
                field[0].equals(getZeichenPC()) && field[4].equals(getZeichenPC()) && field[8].equals(getZeichenPC()) || //Ver. von Links oben nach Rechts unten
                field[2].equals(getZeichenPC()) && field[4].equals(getZeichenPC()) && field[6].equals(getZeichenPC()) || //Ver. von Rechts oben nach Links unten
                field[0].equals(getZeichenPC()) && field[3].equals(getZeichenPC()) && field[6].equals(getZeichenPC()) || //Ver. 1. Reihe
                field[1].equals(getZeichenPC()) && field[4].equals(getZeichenPC()) && field[7].equals(getZeichenPC()) || //Ver 2. Reihe
                field[2].equals(getZeichenPC()) && field[5].equals(getZeichenPC()) && field[8].equals(getZeichenPC())    //Ver 3. Reihe
        ){
            System.out.println(" Der PC hat gewonnen! Du hast leider verloren! ");


        }else if(!field[0].equals(" ") && !field[1].equals(" ") && !field[2].equals(" ") &&
                !field[3].equals(" ") && !field[4].equals(" ") && !field[5].equals(" ") &&
                !field[6].equals(" ") && !field[7].equals(" ") && !field[8].equals(" ")){
            System.out.println(" Das Spiel ist unentschieden!");
        }
        else{
            if (zuletztgespieltPlr1) {
                rundePC();
            } else if (zuletztgespieltPC) {
                try {
                    rundePlr1();
                } catch (SaveDataException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getNameSp1 () {
        return nameSp1;
    }

    public String getZeichenSp1 () {
        return zeichenSp1;
    }

    public String getNameSp2 () {
        return nameSp2;
    }

    public String getZeichenSp2 () {
        return zeichenSp2;
    }

    public String getZeichenPC () {
        return zeichenPC;
    }
}
