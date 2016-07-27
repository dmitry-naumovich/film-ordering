<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Заказ фильмов</title>
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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Новый фильм</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">


<form class="form-horizontal" method="post" name="register">
    <div class="form-group">
      <label class="col-sm-2 control-label">Название*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="nameInput" type="text" placeholder="Введите название" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">Год*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="yearInput" type="text" placeholder="Введите год" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">Режиссер*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="directorInput" type="text" placeholder="Введите режиссера (-ов)" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">Страны*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="countriesInput" type="text" placeholder="Введите страну (-ы)" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">Жанр(ы)*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="genresInput" type="text" placeholder="Введите жанр(ы)" required>
      </div>
    </div>
     <div class="form-group">
      <label class="col-sm-2 control-label">Длительность*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="lengthInput" type="text" placeholder="Введите длительность" required>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label">Стоимость*:</label>
      <div class="col-sm-10">
        <input class="form-control" id="costInput" type="text" placeholder="Введите стоимость" required>
      </div>
    </div>

     <div class="form-group">
      <label class="col-sm-2 control-label">Обложка: </label>
      <div class="col-sm-10">
            <input type="file" name="avatar">
      </div>
    </div>    

    <div class="form-group">
      <label class="col-sm-2 control-label" for="comment">Описание фильма:</label>
      <div class="col-sm-10">
        <textarea class="form-control" rows="5" id="comment"></textarea>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-2 col-md-offset-2">
      <button type="submit" class="btn btn-primary">Добавить фильм</button>
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

  </div>  
  

  <jsp:include page="/WEB-INF/static/footer.jsp"></jsp:include>
</body>
</html>