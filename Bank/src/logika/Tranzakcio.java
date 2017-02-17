package logika;

import bank.Terminal;

public class Tranzakcio {
    
    private static final Terminal terminal = new Terminal();
    public BankSzamla szamla;
    public Hozzafero hozzafero;
    public final int OSSZEG = terminal.getOsszeg();
    
    public int dijSzamol(){
        int tranzakcioDij = OSSZEG * szamla.getDijSzazalek() / 100;
        if (tranzakcioDij < szamla.getDijMin()){
            tranzakcioDij = szamla.getDijMin();
        }
        return tranzakcioDij;
    }
    
    public void vegrehajt(){
        
    }
    
    
}
