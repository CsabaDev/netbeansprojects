/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

/**
 *
 * @author Csaba
 */
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
