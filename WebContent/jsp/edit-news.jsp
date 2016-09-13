<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.editNews.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.editNews.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.addNews.newsTitle" var="newsTitle" />
<fmt:message bundle="${loc}" key="local.addNews.newsText" var="newsText" />
<fmt:message bundle="${loc}" key="local.editNews.editNewsBtn" var="editNewsBtn" />

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
  <script>
   $( function() {
	    $( ".datepicker" ).datepicker();
	  } );
  </script>
  <script>
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
      if(document.editNewsForm.newsTitle.value=="") {
	      alert("News title can not be left blank");
	      document.editNewsForm.newsTitle.focus();
	      return false;
		}
      else if(document.getElementById("newsTextArea").value.length < 50) {
        alert("News text must contain at least 50 symbols");
        document.editNewsForm.newsText.focus();
        return false;
      }
      else {
          document.editNewsForm.submit();
      }
  }</script>
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
				<c:if test="${errorMessage != null && !errorMessage.isEmpty()}" >
					<div class="alert alert-danger fade in">
					  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
					 ${errorMessage} 
					</div>
				</c:if>
				
<c:set var="news" value="${requestScope.news}" />

	<form  name="editNewsForm" class="form-horizontal" method="post" action="Controller" onSubmit="return validateForm(event);">
	  	<div class="form-group">
	    	<input type="hidden" name="command" value="edit_news"/>
	    	<input type="hidden" name="newsID" value="${news.id}"/>
	  	</div>
	  	<div class="form-group">
	      <label class="col-sm-2 control-label">${newsTitle}</label>
	      <div class="col-sm-10">
	        <input class="form-control" name="newsTitle" type="text" value="${news.title}">
	      </div>
		</div>
	  <div class="form-group">
	    <label class="col-sm-2 control-label">${newsText}</label>
	    <div class="col-sm-10"> 
	    	<textarea class="form-control" rows="10" name="newsText" id="newsTextArea">${news.text}</textarea>
	    </div>
	  </div>
	  <div class="col-sm-2 col-md-offset-2">
	  	<button type="submit" class="btn btn-primary">${editNewsBtn}</button>
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