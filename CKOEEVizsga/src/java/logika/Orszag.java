/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Czinéné Kertész Orsolya
 */
public class Orszag {
    private List<Baba> osszesBaba;
    
    Random rnd = new Random();
    
    public String ujAdoszam(){
        String adoszam;
        adoszam = atvalto(osszesBaba.size());
        return adoszam;
    }
    
    public String atvalto(int decim){
        char[] symbols = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] temp = new char[12];
        int i = 11;
        int szam = decim;
        while(szam != 0 && i >= 0){            
            temp[i] = symbols[szam/36 % 36];
            szam = decim/36;
            i--;
        }
        for (int j = 0; j <= i; j++) {
            temp[j] = symbols[rnd.nextInt(symbols.length)];
        }
        return temp.toString();
    }
    
    public void ujSzuletes(String nev, String anyaNev, 
            String apaNev, int nem, String varos, String korhaz){
        Baba ujBaba = new Baba();
        ujBaba.setNev(nev);
        Calendar ma = Calendar.getInstance();
        Date szulDatum = ma.getTime();
        ujBaba.setSzulDatum(szulDatum);
        ujBaba.setAnyaNev(anyaNev);
        ujBaba.setApaNev(apaNev);
        ujBaba.setNem(nem);
        ujBaba.setVaros(varos);
        ujBaba.setKorhaz(korhaz);
        String adoszam = ujAdoszam();
        ujBaba.setAdoszam(adoszam);
        osszesBaba.add(ujBaba);
    }
}
