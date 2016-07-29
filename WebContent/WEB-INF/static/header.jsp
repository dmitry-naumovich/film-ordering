<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
	<fmt:setLocale value="${language}" />
   	<fmt:setBundle basename="resources.local" var="loc" />
   	<fmt:message bundle="${loc}" key="local.serviceName" var="serviceName" />
   	<fmt:message bundle="${loc}" key="local.search" var="search" />
   	<fmt:message bundle="${loc}" key="local.ruLanguage" var="ru_lang" />
   	<fmt:message bundle="${loc}" key="local.enLanguage" var="en_lang" />
   	<fmt:message bundle="${loc}" key="local.login" var="login" />
   	<fmt:message bundle="${loc}" key="local.logout" var="logout" />
   	<fmt:message bundle="${loc}" key="local.password" var="password" />
   	<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
   	<fmt:message bundle="${loc}" key="local.signUp" var="signUp" />
   	<fmt:message bundle="${loc}" key="local.profilePage" var="profile" />
   	<fmt:message bundle="${loc}" key="local.myReviews" var="myReviews" />
   	<fmt:message bundle="${loc}" key="local.myOrders" var="myOrders" />
   	<fmt:message bundle="${loc}" key="local.settings" var="settings" />

	<jsp:useBean id="errorMessage" class="java.lang.String" scope="request" />


<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="nav navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${serviceName}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
                
                <form class="navbar-form navbar-left " role="search">
                  <div class="input-group">
                      <input type="text" class="form-control" placeholder="${search}" name="srch-term" id="srch-term" required>
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
											 <a href="#" class="active">${en_lang}</a>
											<a  href = "<c:url value="/Controller?command=change_language">
												<c:param name="language" value="ru" ></c:param> </c:url>">${ru_lang}</a>
										</c:when>
										<c:otherwise>
											<a href = "<c:url value="/Controller?command=change_language">
												<c:param name="language" value="en" ></c:param> </c:url>">${en_lang}</a>
											
											 <a href="#" class="active">${ru_lang}</a>
										</c:otherwise>
									</c:choose>
			          </div>
			             	   
						<div class="form-group dropdown">
                                <a href="jsp/profile.jsp" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="color:white">
                                ${sessionScope.authUser }
                                <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                  <li><a href="/Controller?command=open_profile" role="menuItem">${profile}</a></li>
                                  <li><a href="/Controller/command=open_reviews" role="menuItem">${myReviews}</a></li>
                                  <li><a href="/Controller/command=open_orders" role="menuItem">${myOrders}</a></li>
                                  <li><a href="/Controller/command=open_settings" role="menuItem">${mySettings}</a></li>
                                  <li class="divider"></li>
                                  <li><a href="<c:url value="/Controller?command=logout" />" class="btn btn-primary" role="button">${logout}</a></li>
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
                                <input type="text" placeholder="${login}" class="form-control" name="login" required>
                              </div>
                              <div class="form-group">
                                <input type="password" placeholder="${password}" class="form-control" name="password" required>
                              </div>
                              <button type="submit" class="btn btn-primary" name="Sign in">${signIn}</button>
                              <a href="jsp/register.jsp" class="btn btn-primary" role="button">${signUp}</a>
                              <span class="divider-vertical"> </span>
                              
                              <div class="form-group" style="padding-right:10px">
                              		<c:choose>
										<c:when test="${language eq 'en'}">
											 <a href="#" class="active">${en_lang}</a>
											<a  href = "<c:url value="/Controller?command=change_language">
												<c:param name="language" value="ru" ></c:param> </c:url>">${ru_lang}</a>
										</c:when>
										<c:otherwise>
											<a href = "<c:url value="/Controller?command=change_language">
												<c:param name="language" value="en" ></c:param> </c:url>">${en_lang}</a>
											
											 <a href="#" class="active">${ru_lang}</a>
										</c:otherwise>
									</c:choose>
			          			</div>
			                	
			                

                                            
              </form>
              
               </c:otherwise>
              </c:choose>
          
        </div>
      </div>
  </nav>