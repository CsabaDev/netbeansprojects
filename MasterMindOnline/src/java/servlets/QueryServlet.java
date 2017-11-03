
package servlets;

import com.google.gson.Gson;
import gamemodel.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QueryServlet extends HttpServlet {

    Connection con;
    HttpSession session;
    List<String> userNames;
    List<Integer> numbers;
    List<Integer> times;
    List<Date> dates;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numberOfColors = request.getParameter("numberOfColors");
        String codeLength = request.getParameter("codeLength");
        String colorsRepeatable = (request.getParameter("colorsRepeatable"));
        String colorsRepeatableInt = (colorsRepeatable.equals("true")) ? "1" : "0";
        String onlyMine = (request.getParameter("onlyMine"));
        session = request.getSession();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mastermind", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getServletContext().setAttribute("connection", con);
        try {
            String query = "select userName, numberOfGuesses, timeOfGame, dateOfGame FROM results where";
            StringBuilder queryBuilder = new StringBuilder(query);
            if(onlyMine.equals("true")) {
                queryBuilder.append(" userName = '").append(session.getAttribute("userName")).append("' and");
            }
            queryBuilder.append(" numberOfColors = ").append(numberOfColors);
            queryBuilder.append(" and codeLength = ").append(codeLength);
            queryBuilder.append(" and colorsRepeatable = ").append(colorsRepeatableInt);
            queryBuilder.append(" order by timeOfGame, numberOfGuesses;");
            query = queryBuilder.toString();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(query);
            List<Result> results = new ArrayList();
            while (rs.next()) {                
                Result result = new Result(rs.getString("userName"), rs.getInt("numberOfGuesses"),
                        rs.getInt("timeOfGame"), rs.getDate("dateOfGame"));
                results.add(result);
            }
            String json = new Gson().toJson(results);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception ex) {
            String errorMsg = "Sorry, an error occured.";
            errorMsg = errorMsg + ex;
            ex.printStackTrace();
            System.out.println(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
