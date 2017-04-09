/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Czinéné Kertész Orsolya
 */

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baba.findAll", query = "SELECT b FROM Baba b")
    , @NamedQuery(name = "Baba.findById", query = "SELECT b FROM Baba b WHERE b.id = :id")
    , @NamedQuery(name = "Baba.findBySzulido", query = "SELECT b FROM Baba b WHERE b.szulIdo = :szulido")
    , @NamedQuery(name = Baba.QUERY_MAI_BABAK, query = "SELECT b FROM Baba b WHERE b.szulIdo = CURRENT_DATE")
    , @NamedQuery(name = "Baba.findByVaros", query = "SELECT b FROM Baba b WHERE b.varos = :varos")})

public class Baba implements Serializable{
    
    public static final String QUERY_MAI_BABAK = "Baba.maiBabak";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nev;
    @Temporal(DATE)
    private Date szulIdo;
    private String anyaNev;
    private String apaNev;
    private int nem;
    private String varos;
    @ManyToOne
    private Korhaz korhaz;
    private String adoszam;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) throws Exception {
        this.nev = nev;
    }

    public Date getSzulIdo() {
        return szulIdo;
    }

    public void setSzulIdo(Date szulIdo) {
        this.szulIdo = szulIdo;
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

    public Korhaz getKorhaz() {
        return korhaz;
    }

    public void setKorhaz(Korhaz korhaz) {
        this.korhaz = korhaz;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baba)) {
            return false;
        }
        Baba other = (Baba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return String.format(
                "%1s baba, anyja: '%2s', apja: '%3s', ado: '%4s', korhaz: '%5s', varos: '%6s'", 
                nev, anyaNev, apaNev, adoszam, korhaz, varos);
    }
    
    public String nemToString(){
        if(nem %2 == 0){
            return "nő";
        }else{
            return "férfi";
        }
    }
    
}
