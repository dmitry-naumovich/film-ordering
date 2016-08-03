<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.settings.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.settings.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.settings.changePass" var="changePass" />
<fmt:message bundle="${loc}" key="local.settings.repeatPass" var="repeatPass" />
<fmt:message bundle="${loc}" key="local.settings.enterPass" var="enterPass" />
<fmt:message bundle="${loc}" key="local.settings.avatar" var="avatar" />
<fmt:message bundle="${loc}" key="local.settings.tellAbout" var="tellAbout" />
<fmt:message bundle="${loc}" key="local.settings.changeSettingsBtn" var="changeSettingsBtn" />

<fmt:message bundle="${loc}" key="local.profile.name" var="name" />
<fmt:message bundle="${loc}" key="local.profile.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.profile.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.profile.phoneNum" var="phoneNum" />
<fmt:message bundle="${loc}" key="local.profile.birthDate" var="birthDate" />
<fmt:message bundle="${loc}" key="local.profile.email" var="email" />
<fmt:message bundle="${loc}" key="local.profile.aboutMe" var="aboutMe" />

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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> ${pageHeader}</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">


<form class="form-horizontal" method="post" name="register">
    <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="focusedInput" type="text" value="Дмитрий" required> 
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="disabledInput" type="text" value="Иванов" required>
      </div>
    </div>
    <div class="form-group">
      <label for="pwd" class="col-sm-2 control-label">${changePass}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="pwd" type="password" value="${pageTitle}" required>
      </div>
    </div>
    <div class="form-group">
      <label for="pwd-again" class="col-sm-2 control-label">${repeatPass}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="pwd-again" type="password" value="${pageTitle}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}:</label>
      <div class="col-sm-10">
        <input class="form-control" id="bDateInput" type="date" value="1994-11-28">
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}: </label>
      <div class="col-sm-10">
        <input class="form-control" id="emailInput" type="email" name="useremail" value="example@gmail.com" required>
      </div> 
    </div>
     <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}: </label>
      <div class="col-sm-10">
        <input class="form-control" id="phoneInput" type="text" name="userphone" value="+375447081144">
      </div>
    </div>

     <div class="form-group">
      <label class="col-sm-2 control-label">${pageTitle}: </label>
      <div class="col-sm-10">
            <input type="file" name="avatar">
      </div>
    </div>    

    <div class="form-group">
      <label class="col-sm-2 control-label" for="comment">${pageTitle}:</label>
      <div class="col-sm-10">
        <textarea class="form-control" rows="5" id="comment"></textarea>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-2 col-md-offset-2">
      <button type="submit" class="btn btn-primary">${changeSettingsBtn}</button>
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