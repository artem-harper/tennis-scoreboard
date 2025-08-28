package controller;

import dto.MatchScore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    MatchService matchService = MatchService.getInstance();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID MatchUUID = UUID.fromString(req.getParameter("uuid"));

        MatchScore matchScore = matchService.getMatches().get(MatchUUID);

        req.setAttribute("matchScore", matchScore);

        req.getRequestDispatcher("match-score.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID MatchUUID = UUID.fromString(req.getParameter("uuid"));

        MatchScore matchScore = matchService.getMatches().get(MatchUUID);


        int winnerId = Integer.parseInt(req.getParameter("winner"));

        matchService.winPoint(matchScore, winnerId);

        req.setAttribute("matchScore", matchScore);

        req.getRequestDispatcher("match-score.jsp")
                .forward(req, resp);
    }
}
