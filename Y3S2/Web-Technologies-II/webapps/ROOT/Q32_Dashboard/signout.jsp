<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    session.removeAttribute("loginName");
    session.removeAttribute("password");
    session.removeAttribute("user");
    // session.invalidate();
    response.sendRedirect("/");
%>
