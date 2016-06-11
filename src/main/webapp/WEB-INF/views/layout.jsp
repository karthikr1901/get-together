<%@ taglib prefix="titles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="taglibs.jinc" %>
<html>
<head>
  <tiles:insertAttribute name="meta"/>
</head>
<body>
<sec:authorize access="hasAnyAuthority('GET-TOGETHER-AUTH-USER')">
  <div class="main-menu">
    <tiles:insertAttribute name="menu"/>
  </div>
  <div class="side-menu-bar">
    <tiles:insertAttribute name="side-nav"/>
  </div>
</sec:authorize>
<div class="main-body">
  <tiles:insertAttribute name="body"/>
</div>
<sec:authorize access="hasAnyAuthority('GET-TOGETHER-AUTH-USER')">
  <footer>
    <tiles:insertAttribute name="footer"/>
  </footer>
</sec:authorize>
</body>
</html>
