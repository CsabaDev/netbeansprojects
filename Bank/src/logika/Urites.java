package logika;

public class Urites {
    
    public Hozzafero hozzafero;
    private BankSzamla szamla;
    public int kiveheto;
    
    public void urit(Hozzafero hozzafero){
        int tranzakcioDij = szamla.egyenleg*szamla.getDijSzazalek()/(100 + szamla.getDijSzazalek());
        if(tranzakcioDij < szamla.getDijSzazalek()){
            tranzakcioDij = szamla.getDijMin();
        }
        kiveheto = szamla.egyenleg - tranzakcioDij;
        szamla.egyenleg = 0;
    }
    
}
