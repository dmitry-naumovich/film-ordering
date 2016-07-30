<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
	<fmt:setLocale value="${language}" />
   	<fmt:setBundle basename="resources.local" var="loc" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.mainPage" var="mainPage" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.moviesPage" var="moviesPage" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.profilePage" var="profilePage" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.aboutUsPage" var="aboutUsPage" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.newsPage" var="newsPage" />
   	<fmt:message bundle="${loc}" key="local.common.leftMenu.widenSearchPage" var="widenSearchPage" />
   	
<div class="col-md-2"> 
      <div class="left-sidebar" id="myScrollspy">
        <ul id='left-menu' class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="0" data-offset-bottom="240">
          
          <li><a href="index.jsp"> ${mainPage} </a></li>
          <li><a href="jsp/movies.jsp"> ${moviesPage} </a></li>
          <c:choose>
          	<c:when test="${sessionScope.authUser != null}"><li><a href="jsp/profile.jsp"> ${profilePage} </a></li> </c:when>
          	<c:otherwise><li><a href="jsp/logination.jsp" >${profilePage} </a></li>  </c:otherwise>
          </c:choose>
          <li><a href="jsp/about-us.jsp"> ${aboutUsPage} </a></li>
          <li><a href="jsp/news.jsp"> ${newsPage} </a></li>
          <li><a href="jsp/widen-search.jsp"> ${widenSearchPage} </a></li>
        </ul>
      </div>
    </div>