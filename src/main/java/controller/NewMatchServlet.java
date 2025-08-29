package controller;

import dto.PlayerDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OngoingMatchesService;
import service.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    PlayerService playerService = PlayerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name1 = req.getParameter("player1");
        String name2= req.getParameter("player2");

        PlayerDto createDto1 = PlayerDto.builder()
                .name(name1)
                .build();

        PlayerDto createDto2 = PlayerDto.builder()
                .name(name2)
                .build();


        UUID MatchUUID = ongoingMatchesService.startNewMatch(playerService.save(createDto1), playerService.save(createDto2));

        resp.sendRedirect("http://localhost:8081/match-score?uuid="+MatchUUID);

    }
}
