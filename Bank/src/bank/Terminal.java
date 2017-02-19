package bank;

import java.util.Scanner;
import logika.BankSzamla;
import logika.Hozzafero;
import logika.SzamlaKezelo;

public class Terminal {
    //Scanner beOlvas = new Scanner(System.in);

    BankSzamla szamla;
    SzamlaKezelo kezelo;
    
    public Terminal() {
        
    }
    
    Scanner beOlvas = new Scanner(System.in);
    Hozzafero aktivHozzafero;
    
    public void indit(){
        
        System.out.println("Nyisson uj bankszamlat!");
        szamla = new BankSzamla(35, 1);
        kezelo = new SzamlaKezelo(szamla);
        toStringKoltsegek();
        System.out.println("Adja meg az elso hozzafero nevet!");
        kezelo.hozzaferotFelvesz(beOlvas.nextLine());
        login();
        
    }
    
    private void muveletetValaszt(){
        System.out.println("Valasszon muveletet!");
        System.out.println("Penz betetelehez nyomja meg a 'b' billentyut!");
        System.out.println("Penz felvetelehez nyomja meg az 'f' billentyut!");
        System.out.println("Hozzafero felvetelehez nyomja meg a 'h' billentyut!");
        System.out.println("Hozzafero eltavolitasahoz nyomja meg a 'k' billentyut!");
        System.out.println("Kilepeshez nyomja meg az 'x' billentyut!");
        
        //TODO: fix this part
        
        switch(beOlvas.nextLine()){
            case "b": penzbetet(); return;
            case "f": penzfelvet(); return;
            case "x": kilep(); break;
        }
    }
    
    
    public void toStringKoltsegek(){
        System.out.println("Egy tranzakcio dija a felvett vagy betett osszeg: "
                + szamla.getDijSzazalek() + "%-a");
        System.out.println("Egy tranzakcio minimalis dija: "+szamla.getDijMin());
    }
    
    private void penzbetet() {
        System.out.println("Adja meg a betenni kivant osszeget!");
        ellenoriz();
        int osszeg = beOlvas.nextInt();
        try {
            kezelo.betesz(osszeg);
        }
        catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        //finally{
        //kezelo.betesz(osszeg);
        //int dij = kezelo.dijSzamol(osszeg);
        System.out.println(toStringBe(szamla.getEgyenleg(), osszeg, kezelo.dijSzamol(osszeg)));
        muveletetValaszt();
        //}
    }

    private void penzfelvet() {
        System.out.println("Adja meg a felvenni kivant osszeget!");
        ellenoriz();
        int osszeg = beOlvas.nextInt();
        try {
            kezelo.kivesz(osszeg);
        }
        catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
            muveletetValaszt();
        }
        finally{
        kezelo.kivesz(osszeg);
        //int dij = kezelo.dijSzamol(osszeg);
        System.out.println(toStringKi(szamla.getEgyenleg(), osszeg, kezelo.dijSzamol(osszeg)));
        muveletetValaszt();
        }
    }
    
    
    
    private void ellenoriz(){
        if(!beOlvas.hasNextInt()){
            System.out.println("Pozitiv egesz szamot adjon meg!");
        }
    }
    
    public String toStringBe(int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                " betett a szamlara " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    public String toStringKi(int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                " kivett a szamlarol " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }

    private void kilep() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void login() {
        System.out.println("Adja meg a hozzafero nevet!");
        String nev = beOlvas.nextLine();
        try{
            aktivHozzafero = kezelo.hozzaferotKeres(nev);
        }catch (UnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void logout() {
        aktivHozzafero = null;
    }

}
