<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.header" var="siteMapHeader" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.main" var="main" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.films" var="films" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.reviews" var="reviews" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.widenSearch" var="widenSearch" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.signUp" var="signUp" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.profile" var="profile" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.orders" var="orders" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.news" var="news" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.feedback" var="feedback" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.aboutUs" var="aboutUs" />
<fmt:message bundle="${loc}" key="local.footer.siteMap.help" var="help" />   	
<fmt:message bundle="${loc}" key="local.footer.info" var="footerInfo" />   	
   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<footer class="container-fluid text-center"> 
    
    <section id="sec1">

    <h2 id="sitemap-header">${siteMapHeader}</h2>
    <div class="row">
      <div class="col-md-2 col-md-offset-3">
        <ul>
          <li><a href="index.jsp">${main}</a></li>
          <li><a href="<c:url value="/Controller?command=open_film_list"/>" >${films}</a></li>
          <li><a href="jsp/reviews.jsp">${reviews}</a></li>
          <li><a href="jsp/widen-search.jsp">${widenSearch}</a></li>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <c:choose>
          	<c:when test="${sessionScope.authUser != null}">
          		<li><a href="jsp/profile.jsp"> ${signIn} </a></li> 
          		<li><a href="jsp/sign-up.jsp">${signUp}</a></li>
          		<li><a href="jsp/profile.jsp">${profile}</a></li>
          		<li><a href="jsp/orders.jsp">${orders}</a></li>
          	</c:when>
          	<c:otherwise>
          		<li><a href="jsp/logination.jsp" >${signIn} </a></li>
          		<li><a href="jsp/sign-up.jsp">${signUp}</a></li> 
          		<li><a href="jsp/logination.jsp">${profile}</a></li>
          		<li><a href="jsp/logination.jsp">${orders}</a></li> 
          	</c:otherwise>
          </c:choose>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <li><a href="jsp/news.jsp">${news}</a></li>
          <li><a href="jsp/feedback.jsp">${feedback}</a></li>
          <li><a href="jsp/about-us.jsp">${aboutUs}</a></li>
          <li><a href="jsp/help.jsp">${help}</a></li>
        </ul>
      </div>
    </div>
    <div class="row" style="text-align:right; margin-right:10px;"> ${footerInfo} </div>
  </section>
  </footer>