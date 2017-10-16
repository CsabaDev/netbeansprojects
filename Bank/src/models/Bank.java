package models;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<BankSzamla> szamlak;

    public Bank() {
        this(false);
    }
    
    public Bank(boolean generaljonETesztadatokat) {
        szamlak = new ArrayList<>();
        
        if (generaljonETesztadatokat){
            szamlakatGeneral();
        }
    }
    
    private void szamlakatGeneral() {
        BankSzamla szamla = new BankSzamla(35, 1);
        szamla.hozzaferok.add(new Hozzafero("Kati"));
        szamla.hozzaferok.add(new Hozzafero("Peti"));
        szamla.hozzaferok.add(new Hozzafero("Zoli"));
        
        szamlak.add(szamla);
    }
    
    public List<BankSzamla> getSzamlak() {
        return szamlak;
    }
}
