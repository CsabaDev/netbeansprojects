package bank;

import java.util.Scanner;
import logika.BankSzamla;
import logika.SzamlaKezelo;

public class Terminal {
    //Scanner beOlvas = new Scanner(System.in);

    public Terminal() {
        
    }
    
    public void indit(){
        
        System.out.println("Nyisson uj bankszamlat!");
        Scanner beOlvas = new Scanner(System.in);
        BankSzamla ujSzamla = new BankSzamla(35, 1);
        SzamlaKezelo kezelo = new SzamlaKezelo(ujSzamla);
        toStringKoltsegek(ujSzamla);
        System.out.println("Adja meg az elso hozzafero nevet!");
        kezelo.hozzaferotFelvesz(beOlvas.nextLine());
        
    }
    
    private void muveletetValaszt(){
        System.out.println("Valasszon muveletet!");
        System.out.println("Penz betetelehez nyomja meg a 'b' billentyut!");
        System.out.println("Penz felvetelehez nyomja meg az 'f' billentyut!");
        System.out.println("Hozzafero felvetelehez nyomja meg a 'h' billentyut!");
        System.out.println("Hozzafero eltavolitasahoz nyomja meg a 'k' billentyut!");
        System.out.println("Kilepeshez nyomja meg az 'x' billentyut!");
        Scanner beOlvas = new Scanner(System.in);
        switch(beOlvas.nextLine()){
            case "b": penzfelvet();
        }
    }
    
    
    public void toStringKoltsegek(BankSzamla szamla){
        System.out.println("Egy tranzakcio dija a felvett vagy betett osszeg: "
                + szamla.getDijSzazalek() + "%-a");
        System.out.println("Egy tranzakcio minimalis dija: "+szamla.getDijMin());
    }

    private void penzfelvet() {
        throw new UnsupportedOperationException();
    }
    

}
