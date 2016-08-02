<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.film.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.film.director" var="director" />
<fmt:message bundle="${loc}" key="local.film.cast" var="cast" />
<fmt:message bundle="${loc}" key="local.film.genre" var="genre" />
<fmt:message bundle="${loc}" key="local.film.year" var="year" />
<fmt:message bundle="${loc}" key="local.film.country" var="country" />
<fmt:message bundle="${loc}" key="local.film.composer" var="composer" />
<fmt:message bundle="${loc}" key="local.film.lengthmin" var="lengthmin" />
<fmt:message bundle="${loc}" key="local.film.filmRating" var="filmRating" />
<fmt:message bundle="${loc}" key="local.film.originName" var="originName" />
<fmt:message bundle="${loc}" key="local.film.description" var="description" />
<fmt:message bundle="${loc}" key="local.film.price" var="price" />
<fmt:message bundle="${loc}" key="local.film.buyWithOneClickBtn" var="buyWithOneClickBtn" />
<fmt:message bundle="${loc}" key="local.film.writeReviewBtn" var="writeReviewBtn" />
<fmt:message bundle="${loc}" key="local.film.reviewBy" var="reviewBy" />
<fmt:message bundle="${loc}" key="local.index.rublesShorten" var="rublesShorten" />

<%-- <jsp:useBean id="film" class="by.epam.naumovich.film_ordering.bean.Film" scope="request"/> --%>
<c:set var="film" value="${requestScope.film}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${pageTitle} - ${film.name} /> (${film.year })</title>
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
});
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

      <div class="col-md-8 main content ">
        <div class="panel panel-primary">
          <div class=" panel-heading" >
          <h2 class=" text-left" style="margin:0px; padding:0px;">${film.name}</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>
                          <figure>
                          	<img src="img/film/${film.id}/folder.jpg" alt="${film.name}" class="img-thumbnail img-responsive" width="210" height="140" />
                          </figure>
                        </th>
                        <th>
                          
                        </th>
                        
                      </tr>
                      <tr>
                        <th>
                          <a href="pages/order.html" class="btn btn-primary" role="button">${buyWithOneClickBtn}</a>
                        </th>
                        <td> <a href="pages/new-review.html" class="btn btn-warning" role="button">${writeReviewBtn}</a></td>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:if test="${sessionScope.language != 'en'}">
	                    <tr>
	                        <td>${originName}</td>
	                        <td>${film.name}</td>
	                    </tr>
                      
                    </c:if>
                    
                      <tr>
                        <td>${year}</td>
                        <td>${film.year}</td>
                      </tr>
                    <c:if test="${film.country != null}">
                      <tr>
                        <td>${country}</td>
                        <td>${film.country}</td>
                      </tr>
                    </c:if>
                      <tr>
                        <td>${director}</td>
                        <td>${film.director}</td>
                      </tr>
                      <c:if test="${film.actors != null}">
	                      <tr>
	                        <td>${cast}</td>
	                        <td>${film.actors}</td>
	                      </tr>
                      </c:if>
                      <c:if test="${film.composer != null}">
	                      <tr>
	                        <td>${composer}</td>
	                        <td>${film.composer}</td>
	                      </tr>
                       </c:if>
                       
                       <c:if test="${film.genre != null}">
	                      <tr>
	                        <td>${genre}</td>
	                        <td>${film.genre}</td>
	                      </tr>
                      </c:if>
                      <tr>
                        <td>${lengthmin}</td>
                        <td>${film.length}</td>
                      </tr>
                      <tr>
                        <td>${filmRating}</td>
                        <td>${film.rating}</td>
                      </tr>
                      <tr>
                        <td>${price}</td>
                        <td>${film.price} ${rublesShorten}</td>
                      </tr>
                      <c:if test="${film.description != null}">
	                      <tr>
	                        <td>${description}</td>
	                        <td>${film.description}</td>
	                      </tr>
                      </c:if>
                    </tbody>
                </table>

                
			<c:forEach items="${requestScope.reviews}" var="review">
                    <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> ${reviewBy} username</h4>
                        </div> 
	                    <div class="row panel-body">
	                        <div class="col-md-12">
	                          <p> <br>
	                              ${review.text}
	                          </p>
	                        </div>
	                    </div>
                     </div>
			</c:forEach>
                         
          </div>
          </div>

      </div>
      </div>

      <jsp:include page="/WEB-INF/static/right-sidebar.jsp"></jsp:include>
      
     </div>

  </div>  
  
  <jsp:include page="/WEB-INF/static/footer.jsp"></jsp:include>
  
</body>
</html>