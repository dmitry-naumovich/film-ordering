<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Заказ фильмов</title>
  <link rel="icon"  type="image/x-icon" href="../img/tab-logo.png">
  
  <link rel="stylesheet" href="../css/bootstrap.min.css" >
  <link rel="stylesheet" href="../css/styles.css">
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

  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="nav navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../index.html">Заказ фильмов</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
                
                <form class="navbar-form navbar-left " role="search">
                  <div class="input-group">
                      <input type="text" class="form-control" placeholder="Поиск" name="srch-term" id="srch-term" required>
                      <div class="input-group-btn">
                        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                      </div>
                  </div>
                </form>
              <form class="navbar-form navbar-right">
                              <div class="form-group">
                                <input type="text" placeholder="Логин" class="form-control" required>
                              </div>
                              <div class="form-group">
                                <input type="password" placeholder="Пароль" class="form-control" required>
                              </div>
                              <button type="submit" class="btn btn-primary">Вход</button>
                              <a href="../pages/register.html" class="btn btn-primary" role="button">Регистрация</a>
                              <span class="divider-vertical"> </span>
                              <div class=" form-group dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="color:white">Язык <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                  <li><a href="#" role="menuitem">English</a></li>
                                  <li><a href="#" role="menuitem">Russian</a></li>
                                  <li class="divider"></li>
                                  <li class="disabled"><a href="#" role="menuitem">More info</a></li>
                                </ul>
                              </div>
              
              </form>
              
          
        </div><!--/.navbar-collapse -->
      </div>
  </nav>


  <div class="container-fluid"> 
    <div class="row content ">
      <div class="col-md-2"> 
      <div class="left-sidebar" id="myScrollspy">

        
        <ul id='left-menu' class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="0" data-offset-bottom="240">
          <li><a href="../index.html"> Главная </a></li>
          <li><a href="../pages/movies.html" >Фильмы</a></li>
          <li><a href="#" class="active">Профиль</a></li>
          <li><a href="../pages/about-us.html">О нас</a></li>
          <li><a href="../pages/news.html" >Новости</a></li>
          <li><a href="../pages/widen-search.html" >Расширенный поиск</a></li>
        </ul>
      </div>
    </div>


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
                          <figure><img src="../img/avatars-01-01.jpg" alt="Intouchables" class="img-thumbnail img-responsive" width="210" height="140" /> </figure>
                        </th>
                        <th>
                          <a href="../pages/orders.html" class="btn btn-primary" role="button">Мои заказы</a> 
                          <a href="../pages/my-reviews.html" class="btn btn-warning" role="button">Мои рецензии</a>
                          <a href="../pages/profile-settings.html" class="btn btn-danger" role="button">Изменить профиль</a>
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


      <div class="col-md-2"> 
      <div id="right-sidebar">

        <figure><img src="../img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 1</figcaption> </figure>
        <figure><img src="../img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 2</figcaption> </figure>
        <figure><img src="../img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 3</figcaption> </figure>
        <figure><img src="../img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 4</figcaption> </figure>
      
      <div class="well">
        <p>Тут тоже будет находиться некоторая информация</p>
      </div>
      </div>
    </div>
     </div>

  </div>  
  

  <footer class="container-fluid text-center"> 
    
    <section id="sec1">

    <h2 id="sitemap-header">Карта сайта</h2>
    <div class="row">
      <div class="col-md-2 col-md-offset-3">
        <ul>
          <li><a href="../index.html">Главная</a></li>
          <li><a href="../pages/movies.html">Фильмы</a></li>
          <li><a href="../pages/reviews.html">Рецензии</a></li>
          <li><a href="../pages/widen-search.html">Расширенный поиск</a></li>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <li><a href="../pages/logination.html">Вход</a></li>
          <li><a href="../pages/register.html">Регистрация</a></li>
          <li><a href="#">Профиль</a></li>
          <li><a href="../pages/orders.html">Заказы</a></li>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <li><a href="../pages/news.html">Новости</a></li>
          <li><a href="../pages/feedback.html">Обратная связь</a></li>
          <li><a href="../pages/about-us.html">О нас</a></li>
          <li><a href="../pages/help.html">Помощь</a></li>
        </ul>
      </div>
    </div>
    <div class="row" style="text-align:right; margin-right:10px;"> Все права защищены. 2016, автор: Дмитрий Наумович. </div>
  </section>
  </footer>
</body>
</html>