<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.common.rublesShorten" var="rublesShorten" />
<fmt:message bundle="${loc}" key="local.films.openFilmPage" var="openFilmPage" />
<fmt:message bundle="${loc}" key="local.orders.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.orders.userOrder" var="userOrder" />
<fmt:message bundle="${loc}" key="local.orders.orderDate" var="orderDate" />
<fmt:message bundle="${loc}" key="local.orders.orderTime" var="orderTime" />
<fmt:message bundle="${loc}" key="local.orders.filmName" var="filmName" />
<fmt:message bundle="${loc}" key="local.orders.filmPrice" var="filmPrice" />
<fmt:message bundle="${loc}" key="local.orders.discount" var="discount" />
<fmt:message bundle="${loc}" key="local.orders.orderSum" var="orderSum" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title> ${serviceName} - ${pageTitle}</title>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;">${userOrder}</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                
                    <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Фильм 1+1 </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/poster-01.jpg" alt="Intouchables" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px;"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <table class="table table-striped">
                    <thead>
                        
                      <tr>
                      <td> <a href="jsp/film.jsp" class="btn btn-success" role="button">${openFilmPage}</a></td>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDate}</td>
                        <td>09.07.2016</td>
                      </tr>
                      <tr>
                        <td>${orderTime}</td>
                        <td>12:55</td>
                      </tr>
                      <tr>
                        <td>${filmName}</td>
                        <td>1 + 1</td>
                      </tr>
                      <tr>
                        <td>${filmPrice}</td>
                        <td>20,000 ${rublesShorten}</td>
                      </tr>
                      <tr>
                        <td>${discount}</td>
                        <td>0%</td>
                      </tr>
                      <tr>
                        <td>${orderSum}</td>
                        <td>20,000 ${rublesShorten}</td>
                      </tr>
                    </tbody>
                </table>

                          </div>
                          
                        </div>
                        </div>
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