<%-- 
    Document   : signout
    Created on : Oct 12, 2017, 12:37:07 PM
    Author     : Czinéné Kertész Orsi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*, java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:remove var="user" scope="session" />

<%
    response.sendRedirect("index.jsp");
%>

