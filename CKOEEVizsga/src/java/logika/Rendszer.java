/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import entities.Baba;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import entities.Korhaz;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import persistence.BabaJpaController;
import persistence.KorhazJpaController;

/**
 *
 * @author Czinéné Kertész Orsolya
 */
public class Rendszer {
    private List<Baba> osszesBaba;
    private List<Korhaz> korhazak;
    
    Random rnd = new Random();

    public List<Baba> getOsszesBaba() {
        return osszesBaba;
    }

    public List<Korhaz> getKorhazak() {
        
        return korhazak;
    }
    
    
    
    public String ujAdoszam(BabaJpaController babaController){
        int sorszam = babaController.getBabaCount();
        String adoszam = String.valueOf(sorszam);
        return adoszam;
    }
    
//    
//    public static void ujSzuletes(Baba ujBaba, HttpServletRequest request, 
//            KorhazJpaController korhazController, BabaJpaController babaController){
//        ujBaba.setNev(request.getParameter("nev"));
//        Calendar ma = Calendar.getInstance();
//        Date szulDatum = ma.getTime();
//        ujBaba.setSzulDatum(szulDatum);
//        ujBaba.setAnyaNev(request.getParameter("anyaNev"));
//        ujBaba.setApaNev(request.getParameter("apaNev"));
//        ujBaba.setNem(Integer.valueOf((request.getParameter("nem"))));
//        ujBaba.setVaros(request.getParameter("varos"));
//        String korhazIdString = request.getParameter("korhaz");
//        Long korhazId = Long.valueOf(korhazIdString);
//        Korhaz korhaz = korhazController.findKorhaz(korhazId);
//        ujBaba.setKorhaz(korhaz);
//        String adoszam = "123456789123";
//        ujBaba.setAdoszam(adoszam);
//        try {
//            babaController.create(ujBaba);
//        } catch (Exception ex) {
//            Logger.getLogger(BabaRegisztralo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
