<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'ru' }" scope = "session"/>
<fmt:setLocale value="${language}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="nav navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Заказ фильмов</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
                
                <form class="navbar-form navbar-left " role="search">
                  <div class="input-group">
                      <input type="text" class="form-control" placeholder="Поиск" name="srch-term" id="srch-term" required>
                      <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                      </div>
                  </div>
                </form>
                <c:choose>
              <c:when test="${sessionScope.authUser!=null}"> 
              		<form class=" navbar-form nav navbar-right">
              		
              		<div class="form-group">
                              		<c:choose>
										<c:when test="${language eq 'en'}">
											 <a href="#" class="active">EN</a>
											<a  href = "<c:url value = "/Controller?command=change_language">
												<c:param name = "language" value = "ru" ></c:param> </c:url>">RU</a>
										</c:when>
										<c:otherwise>
											<a href = "<c:url value = "/Controller?command=change_language">
												<c:param name = "language" value = "en" ></c:param> </c:url>">EN</a>
											
											 <a href="#" class="active">RU</a>
										</c:otherwise>
									</c:choose>
			          </div>
			             	   
						<div class="form-group dropdown">
                                <a href="jsp/profile.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="color:white">
                                ${sessionScope.authUser }
                                <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                  <li><a href="jsp/profile.jsp" role="menuItem">Профиль</a></li>
                                  <li><a href="jsp/user-reviews.jsp" role="menuItem">Мои рецензии</a></li>
                                  <li><a href="jsp/orders.jsp" role="menuItem">Мои заказы</a></li>
                                  <li><a href="jsp/profile-settings.jsp" role="menuItem">Настройки</a></li>
                                  <li class="divider"></li>
                                  <li><a href="<c:url value="/Controller?command=logout" />" class="btn btn-primary" role="button">Выход</a></li>
                                </ul>
                     	</div>
                     	<span class="divider-vertical"> </span>
                     	
              		</form>
              </c:when>
              <c:otherwise>
              
              		<form class="navbar-form navbar-nav nav navbar-right" action="Controller" method="post">
                              <div class="form-group">
                              	<input type="hidden" name="command" value="login" />
                              </div>
                              <div class="form-group">
                                <input type="text" placeholder="Логин" class="form-control" name="login" required>
                              </div>
                              <div class="form-group">
                                <input type="password" placeholder="Пароль" class="form-control" name="password" required>
                              </div>
                              <button type="submit" class="btn btn-primary" name="Sign in">Вход</button>
                              <a href="jsp/register.jsp" class="btn btn-primary" role="button">Регистрация</a>
                              <span class="divider-vertical"> </span>
                              
                              <div class="form-group">
                              		<c:choose>
										<c:when test="${language eq 'en'}">
											 <a href="#" class="active">EN</a>
											<a  href = "<c:url value = "/Controller?command=change_language">
												<c:param name = "language" value = "ru" ></c:param> </c:url>">RU</a>
										</c:when>
										<c:otherwise>
											<a href = "<c:url value = "/Controller?command=change_language">
												<c:param name = "language" value = "en" ></c:param> </c:url>">EN</a>
											
											 <a href="#" class="active">RU</a>
										</c:otherwise>
									</c:choose>
			             	   </div>
			                
			                

                                            
              </form>
              
               </c:otherwise>
              </c:choose>
          
        </div>
      </div>
  </nav>