<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Accio.HistoryResult" %><%--
  Created by IntelliJ IDEA.
  User: gopal
  Date: 28-01-2023
  Time: 13:20
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %> --%>
<html>
<head>

    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<form action="Search">
    <input type="text" name ="keyword">
    <button type="submit">Search</button>
</form>
<div class="resultTable">
<table border=2>
    <tr>
        <td>Keyword</td>
        <td>Link</td>
    </tr>
    <%
        //get result from history servlet
        ArrayList<HistoryResult> results=(ArrayList<HistoryResult>)request.getAttribute("results");
        //iterate through array list
        for(HistoryResult result:results){

    %>
    <tr>
        <td> <%out.println(result.getKeyword());%></td>
        <td> <a href="<%out.println(result.getLink());%>"><%out.println(result.getLink());%></a></td>
    </tr>

    <%
        }
    %>
</table>
</div>
</body>
</html>
