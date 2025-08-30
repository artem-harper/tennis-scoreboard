package controller;

import dto.MatchScore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    ScoreCalculationService scoreCalculationService = ScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID MatchUUID = UUID.fromString(req.getParameter("uuid"));

        MatchScore matchScore = ongoingMatchesService.getMatches().get(MatchUUID);

        req.setAttribute("matchScore", matchScore);

        req.getRequestDispatcher("match-score.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID MatchUUID = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getMatches().get(MatchUUID);
        int winnerId = Integer.parseInt(req.getParameter("winner"));

        scoreCalculationService.winPoint(matchScore, winnerId);

        if (scoreCalculationService.isGameOver(matchScore)){
            ongoingMatchesService.removeEndedMatch(MatchUUID);

            req.setAttribute("matchScore", matchScore);
            req.getRequestDispatcher("/match-over.jsp").forward(req,resp);
        }

        req.setAttribute("matchScore", matchScore);

        req.getRequestDispatcher("match-score.jsp")
                .forward(req, resp);
    }
}
