
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<%@ include file="header.jsp"%>
<main>

    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>

                <tr class="player1">
                    <td class="table-text">${requestScope.matchScore.getPlayer1().getName()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore1().getSets()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore1().getGames()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore1().displayPoints()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore1().getStatus()}</td>


                </tr>

                <tr class="player2">
                    <td class="table-text">${requestScope.matchScore.getPlayer2().getName()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore2().getSets()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore2().getGames()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore2().displayPoints()}</td>
                    <td class="table-text">${requestScope.matchScore.getPlayerScore2().getStatus()}</td>
                </tr>

                </tbody>
            </table>
        </section>
    </div>
</main>

<%@ include file="footer.jsp"%>

</body>
</html>
