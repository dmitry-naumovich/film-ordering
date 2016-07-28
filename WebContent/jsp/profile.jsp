<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Мой профиль</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>
                          <figure><img src="img/avatars-01-01.jpg" alt="Intouchables" class="img-thumbnail img-responsive" width="210" height="140" /> </figure>
                        </th>
                        <th>
                          <a href="jsp/orders.jsp" class="btn btn-primary" role="button">Мои заказы</a> 
                          <a href="jsp/user-reviews.jsp" class="btn btn-warning" role="button">Мои рецензии</a>
                          <a href="jsp/profile-settings.jsp" class="btn btn-danger" role="button">Изменить профиль</a>
                        </th>
                      </tr>
                      <tr>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Имя</td>
                        <td>Дмитрий</td>
                      </tr>
                      <tr>
                        <td>Фамилия</td>
                        <td>Иванов</td>
                      </tr>
                      <tr>
                        <td>Логин</td>
                        <td>loggy</td>
                      </tr>
                      <tr>
                        <td>Дата и время регистрации</td>
                        <td>02.07.2016 20:02</td>
                      </tr>
                      <tr>
                        <td>Пол</td>
                        <td>Мужской</td>
                      </tr>
                      <tr>
                        <td>Дата рождения</td>
                        <td>28.11.1994</td>
                      </tr>
                      <tr>
                        <td>Номер телефона</td>
                        <td>+375447081144</td>
                      </tr>
                      <tr>
                        <td>E-mail адрес</td>
                        <td>example@gmail.com</td>
                      </tr>
                      <tr>
                        <td>О себе</td>
                        <td><p>
                              Я студент факультета межкорреляционных взаимопояснений кафедры славянской этимологической философии Беларусского Нигилистического Университета имени Франциска Сигизмундовича

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