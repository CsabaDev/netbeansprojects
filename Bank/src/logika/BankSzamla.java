package logika;

import java.util.ArrayList;
import java.util.List;

public class BankSzamla {
    protected int egyenleg;
    public List<Hozzafero> hozzaferok;
    private int dijMin;
    private int dijSzazalek;

    public BankSzamla(int dijMin, int dijSzazalek) {
        this.dijMin = dijMin;
        this.dijSzazalek = dijSzazalek;
        this.hozzaferok = new ArrayList<>();
    }
    
    public int getEgyenleg() {
        return egyenleg;
    }
    
    public int getDijMin() {
        return dijMin;
    }
    
    public int getDijSzazalek() {
        return dijSzazalek;
    }
    
    
}
