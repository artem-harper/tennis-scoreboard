package controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import util.Validator;

import java.io.IOException;
import java.util.List;

@WebFilter("/new-match")
public class ValidationFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        if (!httpServletRequest.getMethod().equals("POST")){
           chain.doFilter(req, res);
           return;
        }

        String player1name = httpServletRequest.getParameter("player1");
        String player2name = httpServletRequest.getParameter("player2");


        List<String> errors = Validator.isCorrectName(player1name, player2name);

        if (!errors.isEmpty()){
            httpServletRequest.setAttribute("errorMessage", errors.getFirst());
            httpServletRequest.getRequestDispatcher("jsp/new-match.jsp").forward(req, res);
        }

        chain.doFilter(req, res);
    }
}
