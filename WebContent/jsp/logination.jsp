<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.signin.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.signin.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.signin.login" var="login" />
<fmt:message bundle="${loc}" key="local.signin.password" var="password" />
<fmt:message bundle="${loc}" key="local.signin.enterLogin" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.signin.enterPswd" var="enterPswd" />

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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> ${signIn} </h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">

				<form action="Controller" method="post">
					<h4><c:out value="${errorMessage}" /></h4>
				  <div class="form-group">
				     <input type="hidden" name="command" value="login" />
				  </div>
				  <div class="form-group">
				    <label for="login">${login}</label>
				    <input class="form-control" name="login" type="text" placeholder="${enterLogin}" required>
				  </div>
				  <div class="form-group">
				    <label for="pwd">${password}:</label>
				    <input type="password" class="form-control" name="password" placeholder="${enterPswd}" required>
				  </div>
				<!--   <div class="checkbox"> -->
				<!--     <label><input type="checkbox">Запомнить меня</label> -->
				<!--   </div> -->
				  <button type="submit" class="btn btn-primary" name="Sign in">${signIn}</button>
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