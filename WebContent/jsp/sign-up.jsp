<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.signUp.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.signUp.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.signUp.enterName" var="enterName" />
<fmt:message bundle="${loc}" key="local.signUp.enterSurname" var="enterSurname" />
<fmt:message bundle="${loc}" key="local.signUp.enterLogin" var="enterLogin" />
<fmt:message bundle="${loc}" key="local.signUp.chooseBDate" var="chooseBDate" />
<fmt:message bundle="${loc}" key="local.signUp.enterPhone" var="enterPhone" />
<fmt:message bundle="${loc}" key="local.signUp.enterEmail" var="enterEmail" />
<fmt:message bundle="${loc}" key="local.signUp.male" var="male" />
<fmt:message bundle="${loc}" key="local.signUp.female" var="female" />
<fmt:message bundle="${loc}" key="local.signUp.unknown" var="unknown" />
<fmt:message bundle="${loc}" key="local.signUp.signUpBtn" var="signUpBtn" />
<fmt:message bundle="${loc}" key="local.signUp.alreadySigned" var="alreadySigned" />
<fmt:message bundle="${loc}" key="local.signUp.signInBtn" var="signInBtn" />
<fmt:message bundle="${loc}" key="local.signUp.bDateFormat" var="bDateFormat" />

<fmt:message bundle="${loc}" key="local.profile.name" var="name" />
<fmt:message bundle="${loc}" key="local.profile.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.profile.login" var="login" />
<fmt:message bundle="${loc}" key="local.profile.regDateTime" var="regDateTime" />
<fmt:message bundle="${loc}" key="local.profile.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.profile.phoneNum" var="phoneNum" />
<fmt:message bundle="${loc}" key="local.profile.email" var="email" />
<fmt:message bundle="${loc}" key="local.settings.repeatPass" var="repeatPass" />
<fmt:message bundle="${loc}" key="local.settings.enterPass" var="enterPass" />
<fmt:message bundle="${loc}" key="local.settings.avatar" var="avatar" />
<fmt:message bundle="${loc}" key="local.settings.tellAbout" var="tellAbout" />

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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
  <script type="text/javascript">
	  window.setTimeout(function() {
		    $(".alert").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 1000);
   </script>
   <script>
	 $( function() {
		    $( ".datepicker" ).datepicker();
	} );
   </script>
	<script type="text/javascript">
	function validateForm(event)
	{
	    event.preventDefault(); // this will prevent the submit event
	    if(document.updSettingsForm.name.value=="") {
	      alert("User name can not be left blank");
	      document.updSettingsForm.name.focus();
	      return false;
	    }
	    else if(document.updSettingsForm.surname.value=="") {
	      alert("Surname can not be left blank");
	      document.updSettingsForm.surname.focus();
	      return false;
	    }
	    else if(document.updSettingsForm.password.value=="") {
	      alert("Password can not be left blank");
	      document.updSettingsForm.password.focus();
	      return false;
	    }
	    else if (document.updSettingsForm.passwordRepeated.value=="") {
	  	alert("Password repeated can not be left blank");
	  	document.updSettingsForm.passwordRepeated.focus();
	  	return false;
	    }
	    else if (document.updSettingsForm.password.value != document.updSettingsForm.passwordRepeated.value) {
	  	  alert("Passwords do not match! Try again.");
	  	  document.getElementById("pwd").value="";
	  	  document.getElementById("pwdRep").value="";
	  	  document.updSettingsForm.password.focus();
	  	  return false;
	    }
	    else if (document.updSettingsForm.email.value=="") {
	    	alert("Email can not be left blank");
	    	document.updSettingsForm.email.focus();
	    	return false;
	      }
	    else {
	        document.updSettingsForm.submit(); // fire submit event
	    }
	}
	</script>
	<script>
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
				<c:if test="${errorMessage != null && !errorMessage.isEmpty()}">
					<div class="alert alert-danger fade in">
					  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
					 ${errorMessage} 
					</div>
				</c:if>
<form name="signUpForm" class="form-horizontal" action="Controller" method="post" onSubmit="return validateForm(event);">
  	<div class="form-group">
    	<input type="hidden" name="command" value="sign_up" />
  	</div>
     <div class="form-group">
      <label class="col-sm-3 control-label">${login}*:</label>
      <div class="col-sm-9">
        <input class="form-control" name="login" type="text" placeholder="${enterLogin}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">${name}*:</label>
      <div class="col-sm-9">
        <input class="form-control" name="name" type="text" placeholder="${enterName}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">${surname}*:</label>
      <div class="col-sm-9">
        <input class="form-control" name="surname" type="text" placeholder="${enterSurname}" required>
      </div>
    </div>
    <div class="form-group">
      <label for="pwd" class="col-sm-3 control-label">${enterPass}*:</label>
      <div class="col-sm-9">
        <input class="form-control" name="password" type="password" placeholder="${enterPass}" required>
      </div>
    </div>
    <div class="form-group">
      <label for="pwd-again" class="col-sm-3 control-label">${repeatPass}*:</label>
      <div class="col-sm-9">
        <input class="form-control" name="passwordRepeated" type="password" placeholder="${enterPass}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">${sex}*: </label>
      <div class="col-sm-9">
      <label class="radio-inline"><input type="radio" name="sex">${male}</label>
      <label class="radio-inline"><input type="radio" name="sex">${female}</label>
      <label class="radio-inline"><input type="radio" name="sex" checked>${unknown}</label>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">${chooseBDate} <br> (${bDateFormat}):</label>
      <div class="col-sm-9">
        <input class="form-control datepicker" name="birthDate" type="date">
      </div>
    </div>
    
     <div class="form-group">
      <label class="col-sm-3 control-label">${phoneNum} (+375): </label>
      <div class="col-sm-9">
        <input class="form-control" name="phone" type="text" placeholder="${enterPhone}">
      </div>
    </div>
     <div class="form-group">
      <label class="col-sm-3 control-label">${email}*: </label>
      <div class="col-sm-9">
        <input class="form-control" name="email" type="email" placeholder="${enterEmail}" required>
      </div> 
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label" for="comment">${tellAbout}:</label>
      <div class="col-sm-9">
        <textarea class="form-control" rows="5" name="about"></textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-3 control-label">${avatar}: </label>
      <div class="col-sm-9">
            <input type="file" name="avatar">
      </div>
    </div>  
    <div class="form-group">
      <div class="col-sm-3 col-md-offset-2">
      <button type="submit" class="btn btn-primary">${signUpBtn}</button>
      </div>

      <label class="col-sm-4 col-md-offset-3 control-label" for="comment">${alreadySigned}</label>
      <a href="<c:url value="/Controller?command=open_logination_page" />">${signInBtn}</a>
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