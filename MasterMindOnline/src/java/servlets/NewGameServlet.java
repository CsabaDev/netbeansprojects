package servlets;

import gamemodel.GameModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author czine
 */
public class NewGameServlet extends HttpServlet {

    String errorMsg;
    HttpSession session;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //String userName = (String)session.getAttribute("userName");
    int numberOfColors = Integer.parseInt(request.getParameter("numberOfColors"));
    int codeLength = Integer.parseInt(request.getParameter("codeLength"));
    int maxNumberOfGuesses = Integer.parseInt(request.getParameter("maxNumberOfGuesses"));
    boolean colorsRepeatable = (request.getParameter("colorsRepeatable") != null);
    GameModel game = new GameModel(numberOfColors, codeLength, maxNumberOfGuesses, colorsRepeatable);
    session = request.getSession();
    session.setAttribute("game", game);
    session.setAttribute("gameState", game.getGameState().toString());
    Calendar startTime = Calendar.getInstance();
    session.setAttribute("startTime", startTime);
    response.sendRedirect("game.jsp");
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
