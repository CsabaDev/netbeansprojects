package models;

public class Tranzakcio {
    private final int osszeg;
    private final int dij;
    private final int ujEgyenleg;
    
    public Tranzakcio(int osszeg, int dij, int ujEgyenleg){
        this.osszeg = osszeg;
        this.dij = dij;
        this.ujEgyenleg = ujEgyenleg;
    }
    
    public int getOsszeg() {
        return osszeg;
    }

    public int getDij() {
        return dij;
    }

    public int getUjEgyenleg() {
        return ujEgyenleg;
    }
    
    @Override
    public String toString(){
        return "Összeg: " + osszeg + " új egyenleg: " + ujEgyenleg + " díj: " + dij;
    }
}
