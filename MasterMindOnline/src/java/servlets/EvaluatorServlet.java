/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import gamemodel.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author czine
 */
public class EvaluatorServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
//        String[] colors = request.getParameterValues("colors");
//        response.getWriter().write(colors.toString());
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        GameModel game = (GameModel) session.getAttribute("game");
        int codeLength = game.getCodeLength();
        CodePeg[] newGuess = new CodePeg[codeLength];
        for (int i = 0; i < codeLength; i++) {
            String id = "guessPeg" + String.valueOf(i + 1);
            String colorName = request.getParameter(id);
            newGuess[i] = CodePeg.getCodePeg(colorName);
        }
        ResultPeg[] evaluation = new ResultPeg[codeLength];
        try {
            evaluation = game.addGuess(newGuess);
            String[] evaluationColors = new String[codeLength];
            String evaluationColorsToPass = "";
            for (int i = 0; i < codeLength; i++) {
                evaluationColors[i] = evaluation[i].getResultColorName(evaluation[i]);
                evaluationColorsToPass = evaluationColorsToPass + evaluationColors[i] + " ";
            }
            response.getWriter().write(evaluationColorsToPass);
        } catch (Exception ex) {
            response.getWriter().write(ex.getMessage());
        }
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
