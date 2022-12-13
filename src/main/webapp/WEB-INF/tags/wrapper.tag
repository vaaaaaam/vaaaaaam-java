<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title><c:out value="${pageTitle}" /></title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src="js/helpers.js"></script>
</head>


<nav class="deep-purple lighten-3" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="indexx.jsp" class="brand-logo">Vaaam</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/user">Пользователи</a></li>
            <li><a href="/connection">Подключения</a></li>
            <li><a href="/invoice">Счета</a></li>
            <li><a href="/service">Услуги</a></li>
            <li><% java.util.Date date = new java.util.Date(); out.print(date.toString()); %></li>
        </ul>
    </div>
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
		<!-- Page body will be here -->
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>