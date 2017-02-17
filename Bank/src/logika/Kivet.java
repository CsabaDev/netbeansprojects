package logika;

public class Kivet extends Tranzakcio{
    
    
    @Override
    public void vegrehajt(){
        int tranzakcioDij = dijSzamol();
        ellenoriz(tranzakcioDij);
        szamla.egyenleg -= OSSZEG - tranzakcioDij;
    }
    
    public void ellenoriz(int tranzakcioDij){
        if(OSSZEG <= 0 || szamla.egyenleg - tranzakcioDij - OSSZEG <= szamla.getDijMin()){
            throw new UnsupportedOperationException();
        }
    }
    
    public String toString(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " kivett a szamlarol " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    
}
