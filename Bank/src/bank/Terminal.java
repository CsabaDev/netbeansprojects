package bank;

import java.util.Scanner;
import models.BankSzamla;
import models.Hozzafero;
import logika.SzamlaKezelo;
import models.Bank;
import models.Tranzakcio;

public class Terminal {
    private final Bank bank;
    private BankSzamla szamla;
    private SzamlaKezelo kezelo;
    private final Scanner scanner = new Scanner(System.in);
    private Hozzafero aktivHozzafero;
    
    public Terminal(Bank bank) {
        this.bank = bank;
    }
    
    public void indit(){
        login();
        
        String muvelet = muveletetValaszt();
        
        while (!muvelet.equals("x")){
            switch(muvelet){
                case "b": penztBetesz(); break;
                case "f": penztKivesz(); break;
                case "l": tranzakciokatListaz(); break;
            }
            
            muvelet = muveletetValaszt();
        }
        
        kilep();
    }
    
    private String muveletetValaszt(){
        System.out.println("Valasszon muveletet!");
//        System.out.println("Penz betetelehez nyomja meg a 'b' billentyut!");
//        System.out.println("Penz felvetelehez nyomja meg az 'f' billentyut!");
//        System.out.println("Tranzakciok listázásához nyomja meg az 'l' billentyut!");
//        System.out.println("Hozzafero felvetelehez nyomja meg a 'h' billentyut!");
//        System.out.println("Hozzafero eltavolitasahoz nyomja meg a 'k' billentyut!");
//        System.out.println("Kilepeshez nyomja meg az 'x' billentyut!");
        
        return scanner.nextLine();
    }
    
    public void tranzakciokatListaz(){
        for(Tranzakcio tranzakcio : szamla.getTranzakciok()){
            System.out.println(tranzakcio);
        }
    }
    
    public void koltsegeketKiir(){
        System.out.println("Egy tranzakcio dija a felvett vagy betett osszeg: "
                + szamla.getDijSzazalek() + "%-a");
        System.out.println("Egy tranzakcio minimalis dija: "+szamla.getDijMin());
    }
    
    private void penztBetesz() {
        System.out.println("Adja meg a betenni kivant osszeget!");
        ellenoriz();
        int osszeg = scanner.nextInt();
        scanner.nextLine();
        try {
            kezelo.betesz(osszeg);
        }
        catch (UnsupportedOperationException ex) {
            hibatKiir(ex);
        }
    }

    private void penztKivesz() {
        System.out.println("Adja meg a felvenni kivant osszeget!");
        ellenoriz();
        int osszeg = scanner.nextInt();
        scanner.nextLine();
        try {
            kezelo.kivesz(osszeg);
        }
        catch (UnsupportedOperationException ex) {
            hibatKiir(ex);
        }
    }
    
    private void ellenoriz(){
        if(!scanner.hasNextInt()){
            System.out.println("Pozitiv egesz szamot adjon meg!");
        }
    }
    
    public String toStringBe(int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                "Betett a szamlara " + osszeg + " forintot.\n"
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
        System.out.println("Vizslat!");
    }

    private void login() {
        System.out.println("Adja meg a hozzafero nevet!");
        String nev = scanner.nextLine();
        
        for (BankSzamla bankSzamla : bank.getSzamlak()) {
            SzamlaKezelo szamlaKezelo = new SzamlaKezelo(bankSzamla);
            if (szamlaKezelo.vanEIlyenHozzafero(nev)){
                szamla = bankSzamla;
                kezelo = szamlaKezelo;
                aktivHozzafero = kezelo.hozzaferotKeres(nev);
                break;
            }
        }
        
        if (kezelo == null){
            hibatKiir("Ehhez a felhasználónévhez nem tartozik számla.");
        }
    }
    
    private void logout() {
        aktivHozzafero = null;
    }

    private void hibatKiir(Exception ex) {
        hibatKiir(ex.getMessage());
    }
    
    private void hibatKiir(String message) {
        System.err.println("Hiba: " + message);
    }
}
