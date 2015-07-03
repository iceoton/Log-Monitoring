<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    All Log

    <c:forEach var="log" items="${logList}">



            <c:out value="${log.message}"/><br />
        </tr>>
    </c:forEach>

</body>
</html>
