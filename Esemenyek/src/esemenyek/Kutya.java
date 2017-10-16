package esemenyek;

public class Kutya {
    
    public interface Ugatas{
        String ugatas();
    }
    
    public String ugat(Ugatas u){
        return u.ugatas();
    }
}
