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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Фильмотека сервиса</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                
                    <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Фильм №1</h4>
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
                      <td><a href="jsp/order.jsp" class="btn btn-primary" role="button">Купить в один клик</a> </td>
                      <td> <a href="jsp/movie.jsp" class="btn btn-success" role="button">Перейти на страницу фильма</a></td>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Оригинальное название</td>
                        <td>Intouchables</td>
                      </tr>
                      <tr>
                        <td>Год</td>
                        <td>2011</td>
                      </tr>
                      <tr>
                        <td>Страна</td>
                        <td>Франция</td>
                      </tr>
                      <tr>
                        <td>Режиссер</td>
                        <td>Оливье Накаш, Эрик Толедано</td>
                      </tr>
                      <tr>
                        <td>В ролях</td>
                        <td>Омар Си, Франсуа Клозе, Анн Ле Ни, Одри Флеро, Жозефин де Мо и другие</td>
                      </tr>
                      <tr>
                        <td>Композитор</td>
                        <td>Людовико Эйнауди</td>
                      </tr>
                      <tr>
                        <td>Жанр</td>
                        <td>Драма, комедия, биография</td>
                      </tr>
                      <tr>
                        <td>Рейтинг фильма</td>
                        <td>8,6</td>
                      </tr>
                    </tbody>
                </table>

                          </div>
                          
                        </div>
                        </div>
                        </div>

                         <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Фильм №2</h4>
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
                      <td><a href="jsp/order.jsp" class="btn btn-primary" role="button">Купить в один клик</a> </td>
                      <td> <a href="jsp/movie.jsp" class="btn btn-success" role="button">Перейти на страницу фильма</a></td>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Оригинальное название</td>
                        <td>Intouchables</td>
                      </tr>
                      <tr>
                        <td>Год</td>
                        <td>2011</td>
                      </tr>
                      <tr>
                        <td>Страна</td>
                        <td>Франция</td>
                      </tr>
                      <tr>
                        <td>Режиссер</td>
                        <td>Оливье Накаш, Эрик Толедано</td>
                      </tr>
                      <tr>
                        <td>В ролях</td>
                        <td>Омар Си, Франсуа Клозе, Анн Ле Ни, Одри Флеро, Жозефин де Мо и другие</td>
                      </tr>
                      <tr>
                        <td>Композитор</td>
                        <td>Людовико Эйнауди</td>
                      </tr>
                      <tr>
                        <td>Жанр</td>
                        <td>Драма, комедия, биография</td>
                      </tr>
                      <tr>
                        <td>Рейтинг фильма</td>
                        <td>8,6</td>
                      </tr>
                    </tbody>
                </table>

                          </div>
                          
                        </div>
                        </div>
                        </div>

                         <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Фильм №3</h4>
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
                      <td><a href="jsp/order.jsp" class="btn btn-primary" role="button">Купить в один клик</a> </td>
                      <td> <a href="jsp/movie.jsp" class="btn btn-success" role="button">Перейти на страницу фильма</a></td>
                        
                      </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Оригинальное название</td>
                        <td>Intouchables</td>
                      </tr>
                      <tr>
                        <td>Год</td>
                        <td>2011</td>
                      </tr>
                      <tr>
                        <td>Страна</td>
                        <td>Франция</td>
                      </tr>
                      <tr>
                        <td>Режиссер</td>
                        <td>Оливье Накаш, Эрик Толедано</td>
                      </tr>
                      <tr>
                        <td>В ролях</td>
                        <td>Омар Си, Франсуа Клозе, Анн Ле Ни, Одри Флеро, Жозефин де Мо и другие</td>
                      </tr>
                      <tr>
                        <td>Композитор</td>
                        <td>Людовико Эйнауди</td>
                      </tr>
                      <tr>
                        <td>Жанр</td>
                        <td>Драма, комедия, биография</td>
                      </tr>
                      <tr>
                        <td>Рейтинг фильма</td>
                        <td>8,6</td>
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