package Speicherung;

import Exceptions.SaveDataException;
import Player.PHuman;

import java.io.*;
import java.util.ArrayList;
;

public class SafeData implements Serializable{
    public void safeDate(ArrayList<PHuman> splrListe) throws SaveDataException {
        if(splrListe!=null){
            ObjectOutputStream oos = null;
            try {
                FileOutputStream fos = new FileOutputStream("spielerlise.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(splrListe); // write splrListe to ObjectOutputStream
                oos.close();
            } catch(Exception ex) {
                ex.printStackTrace();
                throw new SaveDataException("Error on Saving Data!");
            }
        }
        else{
            System.out.println("Spielerliste ist Null!");
        }
    }
 public ArrayList<PHuman> showData() throws SaveDataException {
     ArrayList<PHuman> splrliste = new ArrayList<>();
     ObjectInputStream oos = null;
     try {
         FileInputStream fileInputStream = new FileInputStream("spielerlise.bin");
         oos = new ObjectInputStream(fileInputStream);
         splrliste = (ArrayList<PHuman>) oos.readObject();
         oos.close();
     } catch (IOException | ClassNotFoundException e) {
         ArrayList<PHuman> leereSpielerListe = new ArrayList<>();
         safeDate(leereSpielerListe);
         showData();
     }

     return splrliste;
 }
}
