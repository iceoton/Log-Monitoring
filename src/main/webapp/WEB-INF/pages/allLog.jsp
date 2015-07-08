<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        .log{
            white-space: nowrap;
        }

    </style>
</head>
<body>
    <h1>All Log</h1>
    <br>
    <c:forEach var="log" items="${logList}">
        <div class="log"><c:out value="${log.message}"/></div>
        <hr />
    </c:forEach>

</body>
</html>
