package Player;

import java.io.Serializable;

public class PComputer extends Player implements Serializable {

    public PComputer( String zeichen){
        super("Your finest Enemy", zeichen);
    }

}
