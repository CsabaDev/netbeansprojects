/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import entities.Baba;
import entities.Korhaz;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import persistence.BabaJpaController;
import persistence.KorhazJpaController;

/**
 *
 * @author User
 */
public class BabaRegisztralo extends HttpServlet {

    private BabaJpaController babaController;
    private KorhazJpaController korhazController;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        if (babaController == null) {
            babaController = new BabaJpaController(Persistence.createEntityManagerFactory("CKOEEVizsgaPU"));
        }
        if (korhazController == null) {
            korhazController = new KorhazJpaController(Persistence.createEntityManagerFactory("CKOEEVizsgaPU"));
        }
        //Baba ujBaba = new Baba();
        Baba ujBaba = (Baba)request.getSession().getAttribute("ujBaba");
        try {
            ujSzuletes(ujBaba, request, korhazController, babaController);
            response.sendRedirect("babak.jsp");
        } catch (Exception ex) {
            response.sendRedirect("ujbaba.jsp");
        }
        
    }
    
    public void ujSzuletes(Baba ujBaba, HttpServletRequest request, 
            KorhazJpaController korhazController, BabaJpaController babaController) throws Exception{
        ujBaba.setNev(request.getParameter("nev"));
        Calendar ma = Calendar.getInstance();
        Date szulDatum = ma.getTime();
        ujBaba.setSzulDatum(szulDatum);
        ujBaba.setAnyaNev(request.getParameter("anyaNev"));
        ujBaba.setApaNev(request.getParameter("apaNev"));
        ujBaba.setNem(Integer.valueOf((request.getParameter("nem"))));
        ujBaba.setVaros(request.getParameter("varos"));
        String korhazIdString = request.getParameter("korhaz");
        Long korhazId = Long.valueOf(korhazIdString);
        Korhaz korhaz = korhazController.findKorhaz(korhazId);
        ujBaba.setKorhaz(korhaz);
        String adoszam = "123456789123";
        ujBaba.setAdoszam(adoszam);
        try {
            babaController.create(ujBaba);
        } catch (Exception ex) {
            Logger.getLogger(BabaRegisztralo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
