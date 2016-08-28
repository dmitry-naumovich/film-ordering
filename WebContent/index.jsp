<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.index.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.index.novelty" var="novelty" />
<fmt:message bundle="${loc}" key="local.index.director" var="director" />
<fmt:message bundle="${loc}" key="local.index.cast" var="cast" />
<fmt:message bundle="${loc}" key="local.index.genre" var="genre" />
<fmt:message bundle="${loc}" key="local.index.rublesShorten" var="rublesShorten" />
<fmt:message bundle="${loc}" key="local.index.readMoreBtn" var="readMore" />
<fmt:message bundle="${loc}" key="local.index.addInfo" var="addInfo" />
<fmt:message bundle="${loc}" key="local.index.editFilmBtn" var="editFilmBtn" />
   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${serviceName} - ${pageTitle}</title>
  <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    
  <link rel="icon"  type="image/x-icon" href="img/tab-logo.png">
  <link rel="stylesheet" href="css/bootstrap.min.css" >
  <link rel="stylesheet" href="css/styles.css">
  <script type="text/javascript">
    $('.nav li').click(function(e) {
	  e.preventDefault();
	  $('.nav li').removeClass('active');
	  $(this).addClass('active');
	})
  </script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="http://mybootstrap.ru/wp-content/themes/clear-theme/js/bootstrap-affix.js"></script>
</head>

<body data-spy="scroll" data-target="#myScrollspy" data-offset-top="15">
   	
  	<jsp:include page="/WEB-INF/static/header.jsp"></jsp:include>

  <div class="container-fluid"> 
    <div class="row content ">
    
     <jsp:include page="/WEB-INF/static/left-menu.jsp"></jsp:include>

      <div class="col-md-8 main content " style="text-align:justify;">
        <div class="panel panel-primary">
          <div class=" panel-heading" >
          	<h2 class=" text-left"> ${novelty} </h2>
          </div> 
          <div class="row panel-body">
			<jsp:include page="/IndexPageServlet" />
				<c:forEach items="${requestScope.noveltyList}" var="film">
		            <div class="col-sm-6 col-md-4 col-xs-12 col-lg-4" style="height:520px;">
		              <h2 style="text-align:center">${film.name} (${film.year}) </h2>
		              <p><b>${director}:</b> ${film.director} </p>
		              <p><b>${cast}:</b> ${film.actors} </p>
		              <p><b>${genre}:</b> ${film.genre} </p>
		              <img src="img/films/${film.id}/01.jpg" alt="${film.name}" class="img-rounded" style="width: 100%; height: auto;" />
		              
		              <br><p>${film.description} </p>
		              <p>
		              	<c:choose> 
              				<c:when test="${sessionScope.isAdmin}">
              					<a class="btn btn-info" href="<c:url value="/Controller?command=edit_film&filmID=${film.id}"/>" role="button">${editFilmBtn}</a>
              				</c:when>
              				<c:otherwise>
              					<a class="btn btn-info" href="<c:url value="/Controller?command=open_order_page&filmID=${film.id}"/>" role="button">${film.price} ${rublesShorten}</a>
              				</c:otherwise>
		              	</c:choose> 
		                
		                <a class="btn btn-link" href="<c:url value="/Controller?command=open_film_page&filmID=${film.id}"/>" role="button"> ${readMore} &raquo;</a> 
		              </p>
		            </div>
           		</c:forEach>
          </div>
        </div>
    </div>
		<jsp:include page="/WEB-INF/static/right-sidebar.jsp"></jsp:include>
     </div>
  </div>  
  <jsp:include page="/WEB-INF/static/footer.jsp"></jsp:include>
</body>
</html>