<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="col-md-2"> 
      <div class="left-sidebar" id="myScrollspy">
        <ul id='left-menu' class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="0" data-offset-bottom="240">
          
          <li><a href="index.jsp"> Главная </a></li>
          <li><a href="jsp/movies.jsp" >Фильмы</a></li>
          <c:choose>
          	<c:when test="${sessionScope.authUser != null}"><li><a href="jsp/profile.jsp" >Профиль</a></li> </c:when>
          	<c:otherwise><li><a href="jsp/logination.jsp" >Профиль</a></li>  </c:otherwise>
          </c:choose>
          <li><a href="jsp/about-us.jsp" >О нас</a></li>
          <li><a href="jsp/news.jsp" >Новости</a></li>
          <li><a href="jsp/widen-search.jsp" >Расширенный поиск</a></li>
        </ul>
      </div>
    </div>