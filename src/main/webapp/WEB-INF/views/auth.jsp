<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<div class="container min_height">
    <div class="row vertical-offset-100 justify-content-center">
        <div class="col-md-4 col-md-offset-4 well">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">login</h3>
                </div>
                <div class="panel-body">
                    <sf:form method="POST" action='<s:url value="/login"' enctype="multipart/form-data">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="username" name="login"
                                       type="text" value="${param.login}">
                                <p class="text-danger">
                                    ${wrongLogin}
                                </p>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="password"
                                       name="password" type="password" value="">
                                <p class="text-danger">
                                    ${wrongPassword}
                                </p>
                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit"
                                   value="login">
                        </fieldset>
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
