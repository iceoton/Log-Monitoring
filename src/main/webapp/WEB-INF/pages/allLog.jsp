<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    All Log
    <c:forEach var="log" items="${logList}">
        <tr>
       <td><c:out value="${log.message}"/> </td><br />
           </tr>>
    </c:forEach>

</body>
</html>
