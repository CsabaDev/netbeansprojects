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
public class Betet extends Tranzakcio {
    
    @Override
    public void vegrehajt(){
        ellenoriz();
        int tranzakcioDij = dijSzamol();
        szamla.egyenleg += OSSZEG - tranzakcioDij;
    }
    
    public void ellenoriz(){
        if(OSSZEG <= 0){
            throw new UnsupportedOperationException();
        }
    }
    
    public String toString(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " betett a szamlara " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    
}
