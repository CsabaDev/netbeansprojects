package servlets;

import gamemodel.GameModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RecorderServlet", urlPatterns = {"/RecorderServlet"})
public class RecorderServlet extends HttpServlet {
    
    Connection con;
    HttpSession session;
    String errorMsg;
    GameModel game;
    Calendar endTime;
    Calendar startTime;
    int timeOfGame;
    int numberOfColors;
    int codeLength;
    boolean colorsRepeatable;
    int colorsRepeatableInt;
    int numberOfGuesses;
    String userName;
        
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        session = request.getSession();
        game = (GameModel) session.getAttribute("game");
        endTime = (Calendar) session.getAttribute("endTime");
        startTime = (Calendar) session.getAttribute("startTime");
        timeOfGame = (int) (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 100;
        numberOfColors = game.getNumberOfColors();
        codeLength = game.getCodeLength();
        colorsRepeatable = game.isColorsRepeatable();
        colorsRepeatableInt = colorsRepeatable ? 1 : 0;
        numberOfGuesses = game.getGuessesUnmodifiable().size();
        userName = (String) session.getAttribute("userName");
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getServletContext().setAttribute("connection", con);
        boolean recorded = insertRecord();
        boolean deleted = deleteExtra();
        if(recorded) {
//            response.sendRedirect("game.jsp");
        } else {
            request.getSession().setAttribute("errorMessage", errorMsg);
//            response.sendRedirect("game.jsp");
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

    private boolean insertRecord() {
        try {
            String sql = "insert into results values(null,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, numberOfColors);
            ps.setInt(3, codeLength);
            ps.setInt(4, colorsRepeatableInt);
            ps.setInt(5, numberOfGuesses);
            ps.setInt(6, timeOfGame);
            ps.setDate(7, new Date(endTime.getTimeInMillis()));
            int result = ps.executeUpdate();
            if(result > 0) {
                return true;
            } else {
                errorMsg = "Sorry, an error occured. We couldn't record your result.";
                return false;
            }
        } catch (Exception ex) {
            errorMsg = "Sorry, an error occured. We couldn't record your result.";
            errorMsg = errorMsg + ex;
            ex.printStackTrace();
            System.out.println(ex);
        }
        return false;
    }
    
    private boolean deleteExtra() {
        try {
            String sql = "delete FROM results where " +
                    "id = (select id from (select * from results where " +
                    "userName = ? and numberOfColors = ? and codeLength = ? and colorsRepeatable = ? " +
                    "order by numberOfGuesses, timeOfGame limit 10,1) as t);";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setInt(2, numberOfColors);
            ps.setInt(3, codeLength);
            ps.setInt(4, colorsRepeatableInt);
            int result = ps.executeUpdate();
            if(result > 0) {
                return true;
            } else {
                errorMsg = "Sorry, an error occured. We couldn't record your result.";
                return false;
            }
        } catch (Exception ex) {
            errorMsg = "Sorry, an error occured. We couldn't record your result.";
            errorMsg = errorMsg + ex;
            ex.printStackTrace();
            System.out.println(ex);
        }
        return false;
    }
    
    private boolean getTopTen() {
        try {
            String query = "select * FROM mastermind.results where " +
                    "numberOfColors = ? and codeLength = ? and colorsRepeatable = ? " +
                    "order by numberOfGuesses, timeOfGame;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, numberOfColors);
            ps.setInt(2, codeLength);
            ps.setInt(3, colorsRepeatableInt);
            ResultSet rs = ps.executeQuery(query);
//            if(result > 0) {
//                return true;
//            } else {
//                errorMsg = "Sorry, an error occured. We couldn't record your result.";
//                return false;
//            }
        } catch (Exception ex) {
            errorMsg = "Sorry, an error occured.";
            errorMsg = errorMsg + ex;
            ex.printStackTrace();
            System.out.println(ex);
        }
        return false;
    }
}
