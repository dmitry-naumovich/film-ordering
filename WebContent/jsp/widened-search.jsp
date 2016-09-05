<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.widenedSearch.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.widenedSearch.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.widenedSearch.filmName" var="filmName" />
<fmt:message bundle="${loc}" key="local.widenedSearch.filmYear" var="filmYear" />
<fmt:message bundle="${loc}" key="local.widenedSearch.yearFrom" var="yearFrom" />
<fmt:message bundle="${loc}" key="local.widenedSearch.yearTo" var="yearTo" />
<fmt:message bundle="${loc}" key="local.widenedSearch.filmGenre" var="filmGenre" />
<fmt:message bundle="${loc}" key="local.widenedSearch.searchBtn" var="searchBtn" />
<fmt:message bundle="${loc}" key="local.addFilm.enterName" var="enterName" />
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
  <script type="text/javascript">
	  window.setTimeout(function() {
		    $(".alert").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 1000);
   </script>
   <script type="text/javascript">
	function validateForm(event)
	{
	    event.preventDefault(); // this will prevent the submit event
	    if(document.searchWidenedForm.name.value=="" && document.searchWidenedForm.yearFrom.value=="" 
	    	&& document.searchWidenedForm.yearTo.value=="" && document.searchWidenedForm.genre.value=="") {
		      alert("At least one of all fields must be filled");
		      document.searchWidenedForm.name.focus();
		      return false;
		}
	    else if (document.searchWidenedForm.yearFrom.value!="" && document.searchWidenedForm.yearFrom.value.length != 4) {
	    	alert("Year value must contain 4 numbers");
	    	document.searchWidenedForm.yearFrom.focus();
	    	return false;
	    }
	    else if (document.searchWidenedForm.yearTo.value!="" && document.searchWidenedForm.yearTo.value.length != 4) {
	    	alert("Year value must contain 4 numbers");
	    	document.searchWidenedForm.yearTo.focus();
	    	return false;
	    }
	    else if (parseInt(document.searchWidenedForm.yearFrom.value) > parseInt(document.searchWidenedForm.yearTo.value)) {
	    	alert("'From' year can not exceed 'to' year");
	    	return false;
	    }
	    else {
	        document.searchWidenedForm.submit(); // fire submit event
	    }
	}
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
				<c:if test="${errorMessage != null && !errorMessage.isEmpty()}">
					<div class="alert alert-danger fade in">
					  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
					 ${errorMessage} 
					</div>
				</c:if>
				
<form name="searchWidenedForm" class="form-horizontal" action="Controller" onSubmit="return validateForm(event);">
    <div class="form-group">
    	<input type="hidden" name="command" value="search_films_widened" />
  	</div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmName}:</label>
      <div class="col-sm-10">
        <input class="form-control" name="name" type="text" placeholder="${enterName}">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmYear}: </label>
      <p class="col-sm-1 control-label">${yearFrom}</p>
      <div class="col-sm-2"> 
        <input class="form-control" name="yearFrom" type="number">
      </div>
      <p class="col-sm-1 control-label">${yearTo}</p> 
      <div class="col-sm-2"> 
        <input class="form-control" name="yearTo" type="number">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${filmGenre}:</label>
      <div class="col-sm-10">
        <input class="form-control" name="genre" type="text" placeholder="${enterGenre}">
      </div>
    </div>
    
    <div class="form-group">
      	<div class="col-sm-2 col-md-offset-2">
      		<button type="submit" class="btn btn-primary">${searchBtn}</button>
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