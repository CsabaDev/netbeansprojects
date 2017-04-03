package models;

import java.util.ArrayList;
import java.util.List;

public class BankSzamla {
    protected int egyenleg;
    public List<Hozzafero> hozzaferok;
    private List<Tranzakcio> tranzakciok;
    private int dijMin;
    private int dijSzazalek;

    public BankSzamla(int dijMin, int dijSzazalek) {
        this.dijMin = dijMin;
        this.dijSzazalek = dijSzazalek;
        this.hozzaferok = new ArrayList<>();
        this.tranzakciok = new ArrayList<>();
    }
    
    public int getEgyenleg() {
        return egyenleg;
    }
    
    public void setEgyenleg(int egyenleg) {
        this.egyenleg = egyenleg;
    }
    
    public int getDijMin() {
        return dijMin;
    }
    
    public int getDijSzazalek() {
        return dijSzazalek;
    }
    
    public List<Tranzakcio> getTranzakciok() {
        return tranzakciok;
    }
}
