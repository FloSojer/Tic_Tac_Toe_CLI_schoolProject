package TicTacToe;

public class Spielfeld {

    private String[] feld = {" ", " ", " ", " ", " ", " ",  " ", " ", " "};
    private boolean gueltigeEingabe = true;
    private boolean belegtesFeld = false;

    public Spielfeld(){
        spielfeldAnzeigen();
    }
    public void spielfeldAnzeigen(){
        System.out.println( "|-----------|" );
        System.out.println("| " + this.feld[0] + " | " + this.feld[1]+ " | " + this.feld[2] + " | ");
        System.out.println( "|-----------|" );
        System.out.println("| " + this.feld[3] + " | " + this.feld[4] + " | " + this.feld[5] + " | ");
        System.out.println( "|-----------|" );
        System.out.println("| " + this.feld[6] + " | " + this.feld[7] + " | " + this.feld[8] + " | ");
        System.out.println( "|-----------|" );
    }

    public void setFeld(String koordinate, String zeichen) {

        boolean links = koordinate.charAt(1) == 'L' || koordinate.charAt(1) == 'l';
        boolean mitteVer =koordinate.charAt(1) == 'M' || koordinate.charAt(1) == 'm';
        boolean rechts = koordinate.charAt(1) == 'R' || koordinate.charAt(1) == 'r';
        boolean oben = koordinate.charAt(0) == 'O' || koordinate.charAt(0) == 'o';
        boolean mitteHor = koordinate.charAt(0) == 'M' || koordinate.charAt(0) == 'm';
        boolean unten = koordinate.charAt(0) == 'U' || koordinate.charAt(0) == 'u';

        if (oben) {
            if (links) {
                if(this.feld[0].equals(" ")){
                    this.feld[0] = zeichen;
                } else{
                    belegtesFeld = true;
                }
            } else if (mitteVer) {
               if(this.feld[1].equals(" ")) {
                   this.feld[1] = zeichen;
               } else{
                   belegtesFeld = true;
               }
            } else if (rechts) {
                if(this.feld[2].equals(" ")){
                    this.feld[2] = zeichen;
                } else{
                    belegtesFeld = true;
                }
            } else {
                System.out.println("Ungueltige Eingabe!");
                gueltigeEingabe = false;
            }
        }


        else if (mitteHor) {
            if (links) {
                if(this.feld[3] == " "){
                    this.feld[3] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else if (mitteVer) {
                if(this.feld[4] == " "){
                    this.feld[4] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else if (rechts) {
                if(this.feld[5] == " "){
                    this.feld[5] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else {
                System.out.println("Ungueltige Eingabe!");
                gueltigeEingabe = false;
            }
        }
        else if (unten) {
            if (links) {
                if(this.feld[6] == " "){
                    this.feld[6] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else if (mitteVer) {
                if(this.feld[7] == " "){
                    this.feld[7] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else if (rechts) {
                if(this.feld[8] == " "){
                    this.feld[8] = zeichen;
                }else{
                    belegtesFeld = true;
                }
            } else {
                System.out.println("Ungueltige Eingabe!");
                gueltigeEingabe = false;
            }
        }
        else {
            System.out.println("Ungueltige Eingabe!");
            gueltigeEingabe = false;
        }
    }

    public void clear(){
        String[] feldLeer = {" ", " ", " ", " ", " ", " ",  " ", " ", " "};
        feld = feldLeer;
    }

    public boolean isGueltigeEingabe () {
        return gueltigeEingabe;
    }

    public void setGueltigeEingabe ( boolean gueltigeEingabe ) {
        this.gueltigeEingabe = gueltigeEingabe;
    }

    public void setBelegtesFeld ( boolean belegtesFeld ) {
        this.belegtesFeld = belegtesFeld;
    }

    public boolean isBelegtesFeld () {
        return belegtesFeld;
    }
    public String[] getFeld () {
        return feld;
    }
}
