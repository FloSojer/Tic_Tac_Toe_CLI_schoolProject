package TicTacToe;

import Exceptions.SaveDataException;
import Gameplay.Erklaerung;
import Gameplay.Gameplay;
import Player.SpielerListe;

import java.util.Scanner;

public class Controller {
    public Controller(){
    }

    public static void menu() throws SaveDataException {

        String eingabe = " ";

        while(!eingabe.equals("4")){
            Scanner scan = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("####  ####  ####  #### ");
            System.out.println(" 1.) SPIELERKLAERUNG ");
            System.out.println(" 2.) NEUES SPIEL STARTEN ");
            System.out.println(" 3.) SPIELSTAND ABRUFEN ");
            System.out.println(" 4.) SPIEL BEENDEN");
            eingabe = scan.nextLine();
            switch (eingabe){
                case "1":
                    Erklaerung erklaerung = new Erklaerung();
                    break;
                case "2":
                    Gameplay gameplay = new Gameplay();

                    break;
                case "3":
                    SpielerListe spielerListe = new SpielerListe();
                    spielerListe.showBestenListe();
                    break;
                case "4":
                    System.out.println("Ich hoffe Sie hatten großen Spass an diesem Spiel! ");
                    System.out.println(" AUF WIEDERSEHEN!");
                    break;
                default:
                    System.out.println("Eingabe Ungueltig");
                    System.out.println("Bitte nur die Nummer eingeben.");
                    break;
            }
        }
    }
}
