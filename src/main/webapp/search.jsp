<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Accio.SearchResult" %>

<%--
  Created by IntelliJ IDEA.
  User: gopal
  Date: 28-01-2023
  Time: 11:08
  To change this template use File | Settings | File Templates.

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
--%>
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
        <td>Title</td>
        <td>Link</td>
    </tr>
    <%
        //Get the result search servlet

        ArrayList<SearchResult> results=(ArrayList<SearchResult>) request.getAttribute("results");
        //iterate every data present in the array
        for(SearchResult result:results){


    %>
    <tr>
        <td> <%out.println(result.getPageTitle());%></td>
        <td><a href="<%out.println(result.getPageLink());%>" ><%out.println(result.getPageLink());%></>a></td>
    </tr>
    <%
        }
    %>
</table>
</div>

</body>
</html>
