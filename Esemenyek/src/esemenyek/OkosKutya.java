package esemenyek;

import java.util.ArrayList;

public class OkosKutya {
    public static final int POSTAS = 0;
    public static final int GAZDI = 1;
    private ArrayList<OkosUgatas> ugatasok = new ArrayList<>();
    
    public abstract class OkosUgatas{
        int kihezTartozikAzUgatas;
        public abstract String ugatas();
    }
    
    public String ugat(int kitKellUgatni){
        for (int i = 0; i < ugatasok.size(); i++) {
            if (ugatasok.get(i).kihezTartozikAzUgatas==kitKellUgatni) {
                return ugatasok.get(i).ugatas();
            }
        } 
        
        return "Vau";
    }
    
    public void adUgatas(OkosUgatas oU){
        ugatasok.add(oU);
    }
    public void visszavonUgatas(OkosUgatas oU){
        ugatasok.remove(oU);
    }
}
