package servlets;

import gamemodel.GameModel;
import java.io.IOException;
import java.io.PrintWriter;
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

    //Connection con;
    String errorMsg;
    GameModel game;
    //CodePeg[] code;
    int numberOfColors;
    int codeLength;
    int maxNumberOfGuesses;
    boolean colorsRepeatable;
    HttpSession session;
    
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
    //String userName = (String)session.getAttribute("userName");
    numberOfColors = Integer.parseInt(request.getParameter("numberOfColors"));
    codeLength = Integer.parseInt(request.getParameter("codeLength"));
    maxNumberOfGuesses = Integer.parseInt(request.getParameter("maxNumberOfGuesses"));
    colorsRepeatable = (request.getParameter("colorsRepeatable") != null);
    game = new GameModel(numberOfColors, codeLength, maxNumberOfGuesses, colorsRepeatable);
    session = request.getSession();

    //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind?zeroDateTimeBehavior=convertToNull", "root", "1234");
    //application.setAttribute("connection", con);


    session.setAttribute("game", game);
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
