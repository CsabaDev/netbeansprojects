package esemenyek;

public class Esemenyek {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kutya k = new Kutya();
        
        System.out.println(k.ugat(new KantorUgatas()));
        
        System.out.println(k.ugat(helyiUgatas));
        
        System.out.println(k.ugat(new Kutya.Ugatas() {
            @Override
            public String ugatas() {
                return "nev nelkuli ugatas";
            }
        }));
       
    }
    
    private static final Kutya.Ugatas helyiUgatas = new Kutya.Ugatas() {
        @Override
        public String ugatas() {
               return "helyi ugatas";
        }
    };
}
