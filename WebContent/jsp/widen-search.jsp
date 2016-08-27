<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.widenSearch.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.widenSearch.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.widenSearch.filmName" var="filmName" />
<fmt:message bundle="${loc}" key="local.widenSearch.filmYear" var="filmYear" />
<fmt:message bundle="${loc}" key="local.widenSearch.filmGenre" var="filmGenre" />
<fmt:message bundle="${loc}" key="local.widenSearch.searchBtn" var="searchBtn" />
<fmt:message bundle="${loc}" key="local.addFilm.enterName" var="enterName" />
<fmt:message bundle="${loc}" key="local.addFilm.enterYear" var="enterYear" />
<fmt:message bundle="${loc}" key="local.addFilm.enterGenre" var="enterGenre" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
          <h2 class=" text-left">${pageHeader}</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">


<form class="form-horizontal" method="post" name="register">
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmName}:</label>
      <div class="col-sm-10">
        <input class="form-control" name="film-name" type="text" placeholder="${enterName}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmYear}:</label>
      <div class="col-sm-10">
        <input class="form-control" name="film-year" type="text" placeholder="${enterYear}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmGenre}:</label>
      <div class="col-sm-10">
        <input class="form-control" name="film-genre" type="text" placeholder="${enterGenre}" required>
      </div>
    </div>
    
    <div class="form-group">
      <div class="col-sm-2 col-md-offset-2">
      <a href="jsp/search-results.jsp" class="btn btn-primary" role="button">${searchBtn}</a>
    </div>

    </div>    
    
    
</form>
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