<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
</head>
<body>
<h1>Your Profile</h1>
<%--Hello <security:authentication property="principal.username" />!--%>

<div class="container vertical-tb-offset-10 min_height">
    <div class="row">
        <table class="table">
            <tbody>
            <tr>
                <th>
                    username
                </th>
                <th>
                    <c:out value="${user.username}"/>
                </th>
            </tr>
            <tr>
                <th>
                    email
                </th>
                <th>
                    <c:out value="${user.email}"/>
                </th>
            </tr>
            <tr>
                <th>
                    firstname
                </th>
                <th>
                    <c:out value="${user.firstName}"/>
                </th>
            </tr>
            <tr>
                <th>
                    lastname
                </th>
                <th>
                    <c:out value="${user.lastName}"/>
                </th>
            </tr>
            <tr>
                <th>
                    registration date
                </th>
                <th>
                    <c:out value="${user.registrationDate}"/>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<%--<s:url href="/periodicals/{username}" var="periodicalsUrl">
 <s:param name="username" value="jbauer" />
</s:url>--%>
</body>
</html>