package logika;

public class SzamlaKezelo {

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
    
    public int dijSzamol(int osszeg){
        int tranzakcioDij = osszeg * szamla.getDijSzazalek() / 100;
        if (tranzakcioDij < szamla.getDijMin()){
            tranzakcioDij = szamla.getDijMin();
        }
        return tranzakcioDij;
    }
    
    private void fedezetetEllenoriz(int osszeg, int tranzakcioDij){
        if(szamla.egyenleg - tranzakcioDij + osszeg <= 0){
            throw new UnsupportedOperationException("nincs fedezet");
        }
    }
    
    private void elojeletEllenoriz(int osszeg){
        if (osszeg <= 0){
            throw new UnsupportedOperationException("Pozitiv egesz szamot adjon meg!");
        }
    }
    
    
    public String toStringBe(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.getNev() + " betett a szamlara " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    public String toStringKi(Hozzafero hozzafero, int ujEgyenleg, int osszeg, int tranzakcioDij){
        return
                hozzafero.getNev() + " kivett a szamlarol " + osszeg + " forintot.\n"
                + "A tranzakcio dija: " + tranzakcioDij + "\n" 
                + "Az uj egyenleg: " + ujEgyenleg;
    }
    
    
    public void hozzaferotFelvesz(String nev){
        if (szamla.hozzaferok.size() == 3){
            throw new UnsupportedOperationException("max 3 fo");
        }
        for (int i = 0; i < szamla.hozzaferok.size(); i++) {
            if (szamla.hozzaferok.get(i).getNev().equals(nev)){
                throw new UnsupportedOperationException(nev+" mar hozzafero");
            }
        }
        Hozzafero hozzafero = new Hozzafero(nev);
        szamla.hozzaferok.add(hozzafero);
    }
    
    public void hozzaferotEltavolit(Hozzafero hozzafero){
        if (szamla.hozzaferok.size() == 1){
            throw new UnsupportedOperationException();
        }
        szamla.hozzaferok.remove(hozzafero);
    }
    
    public Hozzafero hozzaferotKeres(String nev){
        for (int i = 0; i < szamla.hozzaferok.size(); i++) {
            if (szamla.hozzaferok.get(i).getNev().equals(nev)){
                return szamla.hozzaferok.get(i);
            }
        }
        throw new UnsupportedOperationException("Nincs ilyen nevu felhasznalo.");
    }
}
