<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.addFilm.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.addFilm.newFilm" var="newFilm" />
<fmt:message bundle="${loc}" key="local.addFilm.folder" var="folder" />
<fmt:message bundle="${loc}" key="local.addFilm.name" var="name" />
<fmt:message bundle="${loc}" key="local.addFilm.addFilmBtn" var="addFilmBtn" />
<fmt:message bundle="${loc}" key="local.addFilm.enterName" var="enterName" />
<fmt:message bundle="${loc}" key="local.addFilm.enterYear" var="enterYear" />
<fmt:message bundle="${loc}" key="local.addFilm.enterGenre" var="enterGenre" />
<fmt:message bundle="${loc}" key="local.addFilm.enterCountry" var="enterCountry" />
<fmt:message bundle="${loc}" key="local.addFilm.enterDirector" var="enterDirector" />
<fmt:message bundle="${loc}" key="local.addFilm.enterCast" var="enterCast" />
<fmt:message bundle="${loc}" key="local.addFilm.enterLength" var="enterLength" />
<fmt:message bundle="${loc}" key="local.addFilm.enterPrice" var="enterPrice" />
<fmt:message bundle="${loc}" key="local.addFilm.enterComposer" var="enterComposer" />
<fmt:message bundle="${loc}" key="local.film.director" var="director" />
<fmt:message bundle="${loc}" key="local.film.cast" var="cast" />
<fmt:message bundle="${loc}" key="local.film.genre" var="genre" />
<fmt:message bundle="${loc}" key="local.film.year" var="year" />
<fmt:message bundle="${loc}" key="local.film.country" var="country" />
<fmt:message bundle="${loc}" key="local.film.composer" var="composer" />
<fmt:message bundle="${loc}" key="local.film.lengthmin" var="lengthmin" />
<fmt:message bundle="${loc}" key="local.film.description" var="description" />
<fmt:message bundle="${loc}" key="local.film.price" var="price" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${pageTitle}</title>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;">${newFilm}</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">


<form class="form-horizontal" name="newFilm" action="Controller" method="post">
    <div class="form-group">
		 <input type="hidden" name="command" value="add_new_film" />
	</div>
    
    <div class="form-group">
      <label class="col-sm-2 control-label">${name}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="nameInput" type="text" placeholder="${enterName}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${year}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="yearInput" type="text" placeholder="${enterYear}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${director}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="directorInput" type="text" placeholder="${enterDirector}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${cast}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="yearInput" type="text" placeholder="${enterCast}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${country}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="countriesInput" type="text" placeholder="${enterCountry}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${composer}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="composerInput" type="text" placeholder="${enterComposer}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${genre}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="genresInput" type="text" placeholder="${enterGenre}" required>
      </div>
    </div>
     <div class="form-group">
      <label class="col-sm-2 control-label">${lengthmin}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="lengthInput" type="text" placeholder="${enterLength}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${price}*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="costInput" type="text" placeholder="${enterPrice}" required>
      </div>
    </div>

     <div class="form-group">
      <label class="col-sm-2 control-label">${folder}: </label>
      <div class="col-sm-10">
            <input type="file" name="avatar">
      </div>
    </div>    

    <div class="form-group">
      <label class="col-sm-2 control-label" for="comment">${description}:</label>
      <div class="col-sm-10">
        <textarea class="form-control" rows="5" id="comment"></textarea>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-2 col-md-offset-2">
      <button type="submit" class="btn btn-primary">${addFilmBtn}</button>
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

  </div>  
  

  <jsp:include page="/WEB-INF/static/footer.jsp"></jsp:include>
</body>
</html>