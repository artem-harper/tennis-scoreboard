<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <div>
        <h1>Start new match</h1>
        <div class="new-match-image"></div>
        <div class="form-container center">
            <form method="post" action="${pageContext.request.contextPath}/new-match">
                <label for="player1">Player 1</label>
                <input class="input-player" type="text" id="player1" placeholder="Enter a player 1 name">

                <label for="player2">Player 2</label>
                <input class="input-player" type="text" id="player2" placeholder="Enter a player 2 name">
                <input class="form-button" type="submit" value="Start">
            </form>
        </div>
    </div>
</div>
</body>
</html>
