/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.util.Date;

/**
 *
 * @author Czinéné Kertész Orsolya
 */
public class Baba {
//    private int id;
    private String nev;
    private Date szulDatum;
    private String anyaNev;
    private String apaNev;
    private int nem;
    private String varos;
    private String korhaz;
    private String adoszam;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Date getSzulDatum() {
        return szulDatum;
    }

    public void setSzulDatum(Date szulDatum) {
        this.szulDatum = szulDatum;
    }

    public String getAnyaNev() {
        return anyaNev;
    }

    public void setAnyaNev(String anyaNev) {
        this.anyaNev = anyaNev;
    }

    public String getApaNev() {
        return apaNev;
    }

    public void setApaNev(String apaNev) {
        this.apaNev = apaNev;
    }

    public int getNem() {
        return nem;
    }

    public void setNem(int nem) {
        this.nem = nem;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getKorhaz() {
        return korhaz;
    }

    public void setKorhaz(String korhaz) {
        this.korhaz = korhaz;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }

    
    public String toString(int nem){
        if(nem %2 == 0){
            return "nő";
        }else{
            return "férfi";
        }
    }
    
}
