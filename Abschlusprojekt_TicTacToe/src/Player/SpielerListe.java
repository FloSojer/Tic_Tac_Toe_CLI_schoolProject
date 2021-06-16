package Player;

import Exceptions.SaveDataException;
import Speicherung.SafeData;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

public class SpielerListe {
    PHuman plr1, plr2;
    ArrayList<PHuman> spielerliste = new ArrayList<>();
    SafeData safeData = new SafeData();
    public void neueSpieler ( PHuman plr1, PHuman plr2) throws SaveDataException {
        this.plr1 = plr1;
        this.plr2 = plr2;
        getSpielerListe();
        checkForDoubles();
        safeSpielerliste();
        showBestenListe();
    }
    public void safeSpielerliste() throws SaveDataException {
        safeData.safeDate(this.spielerliste);
    }

    public void getSpielerListe() throws SaveDataException {
        spielerliste = safeData.showData();
    }

    private void checkForDoubles(){
        ArrayList<PHuman> tempSpielerliste = new ArrayList<>();
        tempSpielerliste.addAll(spielerliste);
        boolean foundPlr1 = false;
        boolean foundPlr2 = false;
        if(spielerliste.size() == 0){
            spielerliste.add(plr1);
            spielerliste.add(plr2);
        }
        else{
            for(PHuman tmpPlr : tempSpielerliste){
                if(tmpPlr.getName().equals(plr1.getName())){
                    plr1.setRundenzahl(plr1.getRundenzahl() + tmpPlr.getRundenzahl());
                    plr1.setWins(plr1.getWins()+tmpPlr.getWins());
                    plr1.setLosts(plr1.getLosts() + tmpPlr.getLosts());
                    int index = tempSpielerliste.indexOf(tmpPlr);
                    spielerliste.set(index, plr1);
                    foundPlr1 = true;
                }
                else if(tmpPlr.getName().equals(plr2.getName())){
                    plr2.setRundenzahl(plr2.getRundenzahl() + tmpPlr.getRundenzahl());
                    plr2.setWins(plr2.getWins()+tmpPlr.getWins());
                    plr2.setLosts(plr2.getLosts() + tmpPlr.getLosts());
                    int index = tempSpielerliste.indexOf(tmpPlr);
                    spielerliste.set(index, plr2);
                    foundPlr2 = true;
                }
            }
            if(!foundPlr1){
                spielerliste.add(plr1);
            }
            if(!foundPlr2){
                spielerliste.add(plr2);
            }
        }
    }


    public void showBestenListe() throws SaveDataException {
        getSpielerListe();
        for(PHuman plr : spielerliste){
            System.out.println("Name: "+plr.getName() +" Gewonnen: "+ plr.getWins() + " Verloren: "+plr.getLosts() + " Gespielte Runden: "+plr.getRundenzahl());
        }
    }
}