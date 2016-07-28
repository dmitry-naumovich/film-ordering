<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Заказ фильмов</title>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Вход</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">


<form class="form-horizontal" method="post" name="new-review">
  <div class="form-group">
      <label class="col-sm-2 control-label">Ваша оценка фильму</label>
      <div class="col-sm-10">
      <label class="radio-inline"><input type="radio" name="optradio">1</label>
      <label class="radio-inline"><input type="radio" name="optradio">2</label>
      <label class="radio-inline"><input type="radio" name="optradio">3</label>
      <label class="radio-inline"><input type="radio" name="optradio">4</label>
      <label class="radio-inline"><input type="radio" name="optradio">5</label>
      </div>
    </div>
  <div class="form-group">
      <label class="col-sm-2 control-label">Тип вашей рецензии </label>
      <div class="col-sm-10">
      <label class="radio-inline"><input type="radio" name="optradio">Положительная</label>
      <label class="radio-inline"><input type="radio" name="optradio">Нейтральная</label>
      <label class="radio-inline"><input type="radio" name="optradio">Негативная</label>
      </div>
    </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">Текст вашей рецензии</label>
    <div class="col-sm-10"> <textarea class="form-control" rows="5" id="comment" required></textarea></div>
  </div>
  
  <a href="jsp/success-review.jsp" class="btn btn-primary" role="button">Отправить</a>
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