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

        int pageSize=5;
        int pageNum = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")):1;
        String name = req.getParameter("filter_by_player_name") !=null ? req.getParameter("filter_by_player_name"): "";

        int allPagesNum;
        List<MatchDto> matches;

        if (name.isEmpty()){
            matches = finishedMatchesService.findMatchesPage(pageNum);
            allPagesNum = (int)Math.ceil((double)finishedMatchesService.findAllMatches().size() / pageSize);

        }else{
            matches = finishedMatchesService.findMatchesPageFilterByName(pageNum, name);
            allPagesNum = (int)Math.ceil((double)finishedMatchesService.findAllMatchesByName(name).size() / pageSize);
        }

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", pageNum);
        req.setAttribute("allPagesNum", allPagesNum);
        req.setAttribute("filterByName", name);
        req.getRequestDispatcher("jsp/matches.jsp")
                .forward(req, resp);
    }
}
