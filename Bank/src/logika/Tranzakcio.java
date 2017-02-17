/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import bank.Terminal;

/**
 *
 * @author Csaba
 */
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
