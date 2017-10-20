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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        String[] colors = request.getParameterValues("colors");
//        response.getWriter().write(colors.toString());
    }

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
            String data = game.getGameState().toString();
            for (int i = 0; i < codeLength; i++) {
                evaluationColors[i] = evaluation[i].getResultColorName(evaluation[i]);
                data = data + " " + evaluationColors[i];
            }
            response.getWriter().write(data);
        } catch (Exception ex) {
            response.getWriter().write(ex.getMessage());
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
    }

    
}
