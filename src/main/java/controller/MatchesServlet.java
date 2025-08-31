package controller;

import dto.MatchDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FinishedMatchesService;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    FinishedMatchesService finishedMatchesService = FinishedMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNum = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")):1;

        List<MatchDto> matches = finishedMatchesService.findMatchesPage(pageNum);

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", pageNum);
        req.getRequestDispatcher("jsp/matches.jsp")
                .forward(req, resp);
    }
}
