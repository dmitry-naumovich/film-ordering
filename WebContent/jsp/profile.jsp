<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.profile.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.profile.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.profile.myReviews" var="myReviews" />
<fmt:message bundle="${loc}" key="local.profile.myOrders" var="myOrders" />
<fmt:message bundle="${loc}" key="local.profile.userReviews" var="userReviews" />
<fmt:message bundle="${loc}" key="local.profile.userOrders" var="userOrders" />
<fmt:message bundle="${loc}" key="local.profile.banUser" var="banUser" />
<fmt:message bundle="${loc}" key="local.profile.editProfile" var="editProfile" />
<fmt:message bundle="${loc}" key="local.profile.name" var="name" />
<fmt:message bundle="${loc}" key="local.profile.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.profile.login" var="login" />
<fmt:message bundle="${loc}" key="local.profile.regDateTime" var="regDateTime" />
<fmt:message bundle="${loc}" key="local.profile.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.signUp.male" var="male" />
<fmt:message bundle="${loc}" key="local.signUp.female" var="female" />
<fmt:message bundle="${loc}" key="local.signUp.unknown" var="unknown" />
<fmt:message bundle="${loc}" key="local.profile.phoneNum" var="phoneNum" />
<fmt:message bundle="${loc}" key="local.profile.birthDate" var="birthDate" />
<fmt:message bundle="${loc}" key="local.profile.email" var="email" />
<fmt:message bundle="${loc}" key="local.profile.aboutMe" var="aboutMe" />

<c:set var="user" value="${requestScope.user}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${pageTitle}: ${user.login}</title>
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
          <h3 class=" text-left">${pageHeader}: ${user.login} </h3>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>
                          <figure><img src="img/avatars/avatars${user.id}.gif" alt="${user.login} avatar" class="img-thumbnail img-responsive" width="150" height="150" /> </figure>
                        </th>
                        <th>
                        	<c:choose>
	  							<c:when test="${sessionScope.authUser != null}">
		                           <c:choose>
			                           	<c:when test="${sessionScope.isAdmin && user.id != sessionScope.userID}">
				                          	<a href="<c:url value="/Controller?command=open_user_orders&userID=${user.id}"/>" class="btn btn-primary" role="button">${userOrders}</a> 
				                          	<a href="<c:url value="/Controller?command=open_user_reviews&userID=${user.id}"/>" class="btn btn-warning" role="button">${userReviews}</a>
				                          	<a href="jsp/ban.jsp" class="btn btn-danger" role="button">${banUser}</a>
				                        </c:when>
				                        <c:when test="${!sessionScope.isAdmin && user.id != sessionScope.userID}">
				                        	<a href=""<c:url value="/Controller?command=open_user_reviews&userID=${user.id}"/>" class="btn btn-warning" role="button">${userReviews}</a>
				                        </c:when>
				                        <c:when test="${sessionScope.isAdmin && user.id == sessionScope.userID}"> 
				                        	<a href="jsp/profile-settings.jsp" class="btn btn-danger" role="button">${editProfile}</a>
				                        </c:when>
				                        <c:otherwise>
				                        	<a href="<c:url value="/Controller?command=open_user_orders&userID=${sessionScope.userID}"/>" class="btn btn-primary" role="button">${myOrders}</a> 
				                            <a href="<c:url value="/Controller?command=open_user_reviews&userID=${sessionScope.userID}"/>" class="btn btn-warning" role="button">${myReviews}</a>
				                            <a href="jsp/profile-settings.jsp" class="btn btn-danger" role="button">${editProfile}</a>
				                        </c:otherwise>
			                       </c:choose>
			                     </c:when>
			                     <c:otherwise> 
			                     	<a href="<c:url value="/Controller?command=open_user_reviews&userID=${user.id}"/>" class="btn btn-warning" role="button">${userReviews}</a>
			                     </c:otherwise>
		                     </c:choose>
	                        
                        </th>
                      </tr>
                      <tr>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${name}</td>
                        <td>${user.name}</td>
                      </tr>
                      <tr>
                        <td>${surname}</td>
                        <td>${user.surname }</td>
                      </tr>
                      <tr>
                        <td>${login}</td>
                        <td>${user.login}</td>
                      </tr>
                      <tr>
                        <td>${regDateTime}</td>
                        <td>${user.regDate} ${user.regTime}</td>
                      </tr>
                      <tr>
                        <td>${sex}</td>
                        <c:choose>
                        	<c:when test="${user.sex eq 'm'.charAt(0)}">
                        		<td>${male}</td>
                        	</c:when>
                        	<c:when test="${user.sex eq 'f'.charAt(0)}">
                        		<td>${female}</td>
                        	</c:when>
                        	<c:otherwise> 
                        		<td>${unknown}</td>
                        	</c:otherwise>
                         </c:choose>
                        
                      </tr>
                      <tr>
                        <td>${birthDate}</td>
                        <td>${user.birthDate }</td>
                      </tr>
                      <tr>
                        <td>${phoneNum}</td>
                        <td>${user.phone}</td>
                      </tr>
                      <tr>
                        <td>${email}</td>
                        <td>${user.email}</td>
                      </tr>
                      <tr>
                        <td>${aboutMe}</td>
                        <td><p>
                              ${user.about}
                          </p></td>
                      </tr>
                    </tbody>
                </table>

                         
                        <div class="col-md-12">
                          
                        </div>
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