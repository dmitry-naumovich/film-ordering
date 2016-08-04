<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.news.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.news.pageHeader" var="pageHeader" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> ${pageHeader} </h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                <c:forEach var="news" items="${requestScope.news}">
                    <div class="panel panel-default">
                        <div class=" panel-heading" >
                          
                          <h4 class=" text-right" style="margin-bottom:0px; padding-bottom:0px;"> ${news.date} </h4>
                        </div> 
                    <div class=" panel-body">
                    	<div class="row">
                    		<p class="text-justify" style="margin:10px;"><u>${news.title}</u></p>
                    	 </div>
                    
                        <div class="row col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/news/${news.id}/01.jpg" alt="Img failed" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px; margin-right: 0px"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <br> <br> <p style="text-align:justify; margin-left: -10px;"> ${news.text} </p>

                          </div>
                          
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