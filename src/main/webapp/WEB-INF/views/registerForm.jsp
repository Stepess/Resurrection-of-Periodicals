<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<div class="container center-block">
    <div class="row vertical-offset-100 justify-content-center">
        <div class="col-md-4 col-md-offset-4 well">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><s:message code="periodicals.registration"/></h3>
                </div>
                <div class="panel-body">
                    <sf:form method="POST" modelAttribute="user" enctype="multipart/form-data">
                        <fieldset>
                            <div class="form-group">
                                <sf:label path="firstName"
                                          cssErrorClass="error">First Name</sf:label>:<br/>
                                <sf:input path="firstName"/><br/>
                                <sf:errors path="firstName" element="div" cssClass="errors"/>
                            </div>
                            <div class="form-group">
                                <sf:label path="lastName"
                                          cssErrorClass="error">Last Name</sf:label>:<br/>
                                <sf:input path="lastName"/><br/>
                                <sf:errors path="lastName" element="div" cssClass="errors"/>
                            </div>
                            <div class="form-group">
                                <sf:label path="email"
                                          cssErrorClass="error">Email</sf:label>:<br/>
                                <sf:input path="email" type="email"/><br/>
                                <sf:errors path="email" element="div" cssClass="errors"/>
                            </div>
                            <div class="form-group">
                                <sf:label path="username"
                                          cssErrorClass="error">Username</sf:label>:<br/>
                                <sf:input path="username"/><br/>
                                <sf:errors path="username" element="div" cssClass="errors"/>
                            </div>
                            <div class="form-group">
                                <sf:label path="password"
                                          cssErrorClass="error">Password</sf:label>:<br/>
                                <sf:password path="password"/><br/>
                                <sf:errors path="password" element="div" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label>Profile Picture</label>:
                                <input type="file"
                                       name="profilePicture"
                                       accept="image/jpeg,image/png,image/gif"/><br/>
                                <input type="submit" value="Register"/>
                            </div>
                        </fieldset>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>