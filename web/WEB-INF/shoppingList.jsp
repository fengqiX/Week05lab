<%-- 
    Document   : shoppingList
    Created on : Oct 10, 2017, 1:30:48 PM
    Author     : 715583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello,${username}</p>
        <p><a href="shoppinglist?action=logout" >Logout</a></p>
        <h1>List</h1>
        <form action="shoppinglist?action=add" method="post">
        <p>Add item:<input type="text" name="item"><input type="submit" value="Add"></p>
        </form>
        <c:if test="${listsize > 0}">
            <form action="shoppinglist?action=delete" method="post">
                <c:forEach var="item" items="${itemlist}" varStatus="status">
                <input type="radio" name="itemname" value="${status.index}"> ${item}<br>
            </c:forEach>
                <input type="submit" value="Delete">
            </form>
        </c:if>
    </body>
</html>
