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
          <h2 class=" text-left" style="margin:0px; padding:0px;">Напишите нам</h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
  <form>
    <div class="row">
      <div class="col-md-6">
        <div class="form-group">
          <label for="first">Имя</label>
          <input type="text" class="form-control" placeholder="" id="first" required>
        </div>
      </div>
      <!--  col-md-6   -->

      <div class="col-md-6">
        <div class="form-group">
          <label for="last">Фамилия</label>
          <input type="text" class="form-control" placeholder="" id="last" required>
        </div>
      </div>
      <!--  col-md-6   -->
    </div>


    <div class="row">
      <div class="col-md-6">
        <div class="form-group">
          <label for="company">Компания</label>
          <input type="text" class="form-control" placeholder="" id="company">
        </div>


      </div>
      <!--  col-md-6   -->

      <div class="col-md-6">

        <div class="form-group">
          <label for="phone">Номер телефона</label>
          <input type="tel" class="form-control" id="phone">
        </div>
      </div>
      <!--  col-md-6   -->
    </div>
    <!--  row   -->


    <div class="row">
      <div class="col-md-6">

        <div class="form-group">
          <label for="email">E-mail адрес</label>
          <input type="email" class="form-control" id="email">
        </div>
      </div>
      <!--  col-md-6   -->

      <div class="col-md-6">
        <div class="form-group">
          <label for="url">Ваш вебсайт <small>Пожалуйста, укажите http://</small></label>
          <input type="url" class="form-control" id="url" placeholder="url">
        </div>

      </div>
      <!--  col-md-6   -->
    </div>
    <!--  row   -->

    <label>Когда нам связаться с Вами?</label>
    <div class="radio">
      <label>
        <input type="radio" name="contact-preference" value="am" checked>Утро
      </label>
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="contact-preference" value="pm" >День
      </label>
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="contact-preference" value="pm" >Вечер
      </label>
    </div>
    <div class="radio">
      <label>
        <input type="radio" name="contact-preference" value="pm" >Ночь
      </label>
    </div>

    <label for="newsletter">Хотели ли бы вы получать нашу рассылку?</label>
    <div class="checkbox">

      <label>
        <input type="checkbox" value="Sure!" id="newsletter"> Конечно!
      </label>
    </div>


    <button type="submit" class="btn btn-primary">Отправить</button>
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