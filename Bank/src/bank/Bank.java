package bank;

import logika.Hozzafero;
import logika.BankSzamla;
import java.util.ArrayList;

public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankSzamla bankSzamla =  new BankSzamla();
        bankSzamla.hozzaferok = new ArrayList<Hozzafero>();
    }
    
}
