<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<h1>Add comment</h1>
<sf:form method="POST" modelAttribute="comment">
    <sf:errors path="*" element="div" cssClass="errors" />
    <sf:label path="userId"
              cssErrorClass="error">User Id</sf:label>:
    <sf:input path="userId" /><br/>
    <sf:label path="message"
              cssErrorClass="error">Message</sf:label>:
    <sf:input path="message" /><br/>
    <sf:label path="email"
              cssErrorClass="error">Email</sf:label>:
    <sf:input path="email" type="email" /><br/>
    <sf:label path="username"
              cssErrorClass="error">Username</sf:label>:
    <sf:input path="username" /><br/>
    <sf:label path="password"
              cssErrorClass="error">Password</sf:label>:
    <sf:password path="password" /><br/>
    <label>Profile Picture</label>:
    <input type="file"
           name="profilePicture"
           accept="image/jpeg,image/png,image/gif" /><br/>
    <input type="submit" value="Register" />
</sf:form>

</body>
</html>
