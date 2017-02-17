package bank;

import logika.BankSzamla;
import logika.SzamlaKezelo;

public class Bank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BankSzamla szamla = new BankSzamla(35, 1);
        
        SzamlaKezelo szamlaKezelo =  new SzamlaKezelo(szamla);
        
        szamlaKezelo.betesz(10000);
        System.out.println(szamla.getEgyenleg());
        szamlaKezelo.kivesz(19000);
        System.out.println(szamla.getEgyenleg());
    }
    
}
