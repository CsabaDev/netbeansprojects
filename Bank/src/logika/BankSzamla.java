package logika;

import java.util.ArrayList;
import java.util.List;

public class BankSzamla {
    protected int egyenleg;
    public List<Hozzafero> hozzaferok = new ArrayList<>();
    private int dijMin;
    private int dijSzazalek;

    public BankSzamla(int dijMin, int dijSzazalek) {
        this.dijMin = dijMin;
        this.dijSzazalek = dijSzazalek;
    }
    
    
    
    public void lekerdez(Hozzafero hozzafero){
        
    }
    
    public void hozzaferoKilep(Hozzafero kilepo){
        
    }
    
    public void hozzaferoBelep(Hozzafero belepo){
        
    }
    
    
    public int getEgyenleg() {
        return egyenleg;
    }

    public void setEgyenleg(int egyenleg) {
        this.egyenleg = egyenleg;
    }

    //public ArrayList<Hozzafero> getHozzafero() {
    //    return hozzaferok;
    //}

    public void setHozzafero(ArrayList<Hozzafero> hozzafero) {
        this.hozzaferok = hozzaferok;
    }

    public int getDijMin() {
        return dijMin;
    }

    public void setDijMin(int dijMin) {
        this.dijMin = dijMin;
    }

    public int getDijSzazalek() {
        return dijSzazalek;
    }

    public void setDijSzazalek(int dijSzazalek) {
        if (dijSzazalek < 0){
            System.out.println("Negativ dijat nem szamitunk fel.");
            //throw RuntimeException;
        }if (dijSzazalek > 10){
            System.out.println("Tul koltseges.");
            //throw RuntimeException;
        }else{
            this.dijSzazalek = dijSzazalek;
        }
        
    }
    
    
}
