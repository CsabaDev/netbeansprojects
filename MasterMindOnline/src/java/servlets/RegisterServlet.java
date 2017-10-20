/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author czine
 */
public class RegisterServlet extends HttpServlet {

    Connection con;
    String errorMsg;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
//        String passwordagain = request.getParameter("passwordagain");

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind?zeroDateTimeBehavior=convertToNull", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getServletContext().setAttribute("connection", con);


        boolean authFlag = createUser(userName, password);
        if(authFlag) {
            response.sendRedirect("login.jsp");
        } else {
            request.getSession().setAttribute("errorMessage", errorMsg);
            response.sendRedirect("register.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private boolean createUser(String userName, String password) {
        if(userName.length() < 4) {
            errorMsg = "That username is too short.";
            return false;
        }
        if(password.length() < 4) {
            errorMsg = "That password is too short.";
            return false;
        }
        try {
            String sql = "select * from users where userName=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                errorMsg = "That username is taken.";
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            String sql = "insert into users values(null,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            int result = ps.executeUpdate();
            if(result > 0) {
                return true;
            } else {
                errorMsg = "Invalid username os password.";
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }
        errorMsg = "Invalid username or password.";
        return false;
    }
}
