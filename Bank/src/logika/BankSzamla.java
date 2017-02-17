package logika;

import java.util.ArrayList;
import java.util.List;

public class BankSzamla {
    protected int egyenleg;
    public List<Hozzafero> hozzaferok = new ArrayList<>();
    private int dijMin;
    private int dijSzazalek;
    
    private void urit(){
        
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
        }if (dijSzazalek > 20){
            System.out.println("Tul koltseges.");
            //throw RuntimeException;
        }else{
            this.dijSzazalek = dijSzazalek;
        }
        
    }
    
    public String toStringBe(Hozzafero hozzafero, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " betett a szamlara " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + egyenleg;
    }
    
    public String toStringKi(Hozzafero hozzafero, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " kivett a szamlarol " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + egyenleg;
    }

    
    
}
