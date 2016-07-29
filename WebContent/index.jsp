<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var = "language" value = "${not empty sessionScope.language ? sessionScope.language : 'en' }" scope = "session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.novelty" var="novelty" />
<fmt:message bundle="${loc}" key="local.director" var="director" />
<fmt:message bundle="${loc}" key="local.cast" var="cast" />
<fmt:message bundle="${loc}" key="local.genre" var="genre" />
<fmt:message bundle="${loc}" key="local.rublesShorten" var="rublesShorten" />
<fmt:message bundle="${loc}" key="local.readMoreBtn" var="readMore" />
<fmt:message bundle="${loc}" key="local.addInfo" var="addInfo" />
   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${serviceName}</title>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> ${novelty} </h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>${director}:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>${cast}:</b> Омар Си, Франсуа Клозе </p>
              <p><b>${genre}:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="jsp/order.jsp" role="button">20.000 ${rublesShorten}</a>
                <a class="btn btn-link" href="jsp/movie.jsp" role="button"> ${readMore} &raquo;</a> 
              </p>
            </div>
            
          </div>
          </div>
<div class="panel panel-primary">
<div class=" panel-heading" >
          <h2 class=" text-left" style="margin:0px; padding:0px;"> ${addInfo}: </h2>
          </div> 
  <div class="row panel-body ">
    <div class="col-12">
            <p> Lorem ipsum <mark> adipiscing mattis tempus</mark>, sed lorem urna eros morbi diam. Vitae magna fusce, orci diam vitae ornare lorem odio at non ipsum morbi, eget sem mattis eros, non pharetra eu justo leo pellentesque leo porttitor. Ligula sem nam congue bibendum, vivamus ligula sodales ligula a nibh. Enim nam, justo sit metus massa ornare: sapien massa leo a mauris elementum porttitor sed quisque, diam sapien. Elementum porta proin eros, eu tellus pharetra morbi nulla massa vitae sed tellus. A nec massa <cite> "nibh fusce cursus porta nam ligula mattis at nulla arcu. At nec elementum enim amet duis molestie leo molestie magna sodales gravida sodales"</cite> ut pellentesque ut metus proin, eu. </p>
            <p> Pharetra adipiscing sagittis et donec sed enim adipiscing risus bibendum sem orci risus vitae fusce. Gravida ut ipsum ultricies curabitur pellentesque vitae nam auctor integer in adipiscing elementum risus eget cursus non ut nulla — magna duis pellentesque, proin urna. Un <abbr title="Cascading Style Sheets"> CSS </abbr> der mutilum cercu net aca. 
            Sem rutrum pellentesque, porttitor orci <del>sit porta, auctor, elementum mauris: congue ut diam porta quam pharetra enim rutrum nam molestie. Gravida metus nam ornare congue curabitur odio vivamus — pharetra magna cursus urna donec massa lorem adipiscing tempus sem a morbi in lorem vivamus ornare. Pharetra mattis sed proin eget ipsum odio risus — tempus mauris pharetra diam proin enim eget maecenas</del> ornare. At auctor sapien pellentesque integer vitae magna non metus, bibendum mattis amet molestie. Porttitor duis quisque morbi donec, orci lectus: maecenas ut integer nam bibendum — elementum tellus elementum arcu. Vivamus gravida sem in eu eget arcu ultricies urna metus non elementum ut at nibh pharetra arcu. Sem pharetra cursus arcu ipsum at sagittis leo vulputate amet justo ligula amet enim pellentesque, gravida ornare. </p>
            <p> Eros proin, bibendum odio urna adipiscing ligula, porttitor tellus commodo at nulla vulputate sem sodales — quisque a <u> lectus urna maecenas </u>sem quam sem ligula ut. Quam: quisque, cursus vitae duis gravida nec elementum, in ultricies. Curabitur, massa proin arcu — nibh molestie sagittis cursus — quam, metus, sem molestie.
            Bibendum eu adipiscing, mauris molestie, leo, lorem eu a eget duis enim, in lectus urna lectus mattis. Ornare nam sit ornare pharetra, cursus rutrum ut tellus risus. Leo sapien auctor gravida fusce sapien malesuada risus lorem donec entropica sale ligula <sup> 32323 </sup>. </p>
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