package controller.utilServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.MatchRepository;

import java.io.IOException;

@WebServlet("/findMatches")
public class findAllMatchesServlet extends HttpServlet {

    MatchRepository matchRepository = MatchRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().write(matchRepository.findAll().toString());

    }
}
