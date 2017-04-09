/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import entities.Baba;
import entities.Korhaz;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.BabaJpaController;
import persistence.KorhazJpaController;

/**
 *
 * @author Czinéné Kertész Orsolya
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CKOEEVizsgaPU");
        if (babaController == null) {
            babaController = new BabaJpaController(emf);
        }
        
        if (korhazController == null) {
            korhazController = new KorhazJpaController(emf);
        }
        
        Baba ujBaba = (Baba)request.getSession().getAttribute("ujBaba");
        try {
            ujSzuletes(ujBaba, request, korhazController, babaController);
            response.sendRedirect("babak.jsp");
            request.getSession().removeAttribute("ujBaba");
        } catch (Exception ex) {
            System.out.println("g"+ex.getMessage());
            response.sendRedirect("ujbaba.jsp");
        }
    }
    
    public void ujSzuletes(Baba ujBaba, HttpServletRequest request, 
            KorhazJpaController korhazController, BabaJpaController babaController) throws Exception{
        request.setCharacterEncoding("UTF-8");
        ujBaba.setNev(request.getParameter("nev"));
        Calendar ma = Calendar.getInstance();
        Date szulIdo = ma.getTime();
        ujBaba.setSzulIdo(szulIdo);
        ujBaba.setAnyaNev(request.getParameter("anyaNev"));
        ujBaba.setApaNev(request.getParameter("apaNev"));
        ujBaba.setNem(Integer.valueOf((request.getParameter("nem"))));
        ujBaba.setVaros(request.getParameter("varos"));
        Korhaz korhaz = korhazController.findKorhaz(Long.valueOf(request.getParameter("korhaz")));
        ujBaba.setKorhaz(korhaz);
        ujBaba.setAdoszam(request.getParameter("adoszam"));
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
