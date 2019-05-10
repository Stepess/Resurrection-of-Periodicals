<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="<c:url value="/comments" />">Comments</a>
                </li>
                <li class="nav-item">
                    <a href="<s:url value="/periodicals/register" />">Register</a>
                </li>
                <li class="nav-item">
                    <a href="<s:url value="/comment/add"/>">Add comment</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<h1><s:message code="periodicals.welcome"/></h1>

<div class="container vertical-offset-100 min_height">
    <div class="row">
        <div class="col-lg-8 ">
            <div class=" margin well">
                <h1>Periodicals</h1>
                info
            </div>
        </div>
    </div>
</div>
<div>
    <sf:form method="POST" action="${pageContext.request.contextPath}/logout">
    <input class="btn btn-lg btn-success btn-block" type="submit"
                                       value="logout">
    </sf:form>
</div>
</body>
</html>