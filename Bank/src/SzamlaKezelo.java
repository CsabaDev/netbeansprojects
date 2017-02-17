package logika;

class SzamlaKezelo {

    private final BankSzamla szamla;
    
    public SzamlaKezelo(BankSzamla szamla) {
        this.szamla = szamla;
    }
    
    public void betesz(int osszeg){
        elojeletEllenoriz(osszeg);
        vegrehajt(osszeg);
    }
    
    public void kivesz(int osszeg){
        elojeletEllenoriz(osszeg);
        osszeg *= -1;
        vegrehajt(osszeg);
    }
    
    private void vegrehajt(int osszeg){
        int tranzakcioDij = dijSzamol(osszeg);
        fedezetetEllenoriz(osszeg, tranzakcioDij);
        szamla.egyenleg += osszeg - tranzakcioDij;
    }
    
    private int dijSzamol(int osszeg){
        int tranzakcioDij = osszeg * szamla.getDijSzazalek() / 100;
        if (tranzakcioDij < szamla.getDijMin()){
            tranzakcioDij = szamla.getDijMin();
        }
        return tranzakcioDij;
    }
    
    private void fedezetetEllenoriz(int osszeg, int tranzakcioDij){
        if(szamla.egyenleg - tranzakcioDij + osszeg <= 0){
            throw new UnsupportedOperationException();
        }
    }
    
    private void elojeletEllenoriz(int osszeg){
        if (osszeg <= 0){
            throw new UnsupportedOperationException();
        }
    }
    
    public String toStringBe(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " betett a szamlara " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    public String toStringKi(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.nev + " kivett a szamlarol " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
}
