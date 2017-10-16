package bank;

import models.Bank;

public class Program {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Bank bank = new Bank(true);
        
        Terminal terminal = new Terminal(bank);
        terminal.indit();
        
//        BankSzamla szamla = new BankSzamla(35, 1);
//        
//        SzamlaKezelo szamlaKezelo =  new SzamlaKezelo(szamla);
//        
//        szamlaKezelo.betesz(10000);
//        System.out.println(szamla.getEgyenleg());
//        szamlaKezelo.hozzaferotFelvesz("kati");
//        szamlaKezelo.hozzaferotFelvesz("pisti");
//        szamlaKezelo.hozzaferotFelvesz("pisti");
//        szamlaKezelo.hozzaferotFelvesz("misi");
        
    }
}
