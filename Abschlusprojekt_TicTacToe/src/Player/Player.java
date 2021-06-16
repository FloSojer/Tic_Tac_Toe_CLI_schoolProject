package Player;

import java.io.Serializable;

public abstract class Player implements Serializable {
    private String name, zeichen;
    private int rundenzahl, wins, losts;
    public Player( String name, String zeichen ){
        this.name = name;
        this.zeichen = zeichen;
    }

    public String getZeichen(){
        return this.zeichen;
    }
    public void setZeichen(String zeichen){
        this.zeichen  = zeichen;
    }
    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public int getRundenzahl ( ) {
        return rundenzahl;
    }

    public void setRundenzahl ( int rundenzahl ) {
        this.rundenzahl = rundenzahl;
    }

    public int getWins ( ) {
        return wins;
    }

    public void setWins ( int wins ) {
        this.wins = wins;
    }

    public int getLosts ( ) {
        return losts;
    }

    public void setLosts ( int losts ) {
        this.losts = losts;
    }

    @Override
    public String toString () {
        return "Player{" +
                "name='" + name + '\'' +
                ", zeichen='" + zeichen + '\''+
                '}';
    }
}
