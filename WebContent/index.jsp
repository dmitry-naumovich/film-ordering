<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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

  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="nav navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Заказ фильмов</a>
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
              <form class="navbar-form navbar-right" action="Controller" method="get">
                              <div class="form-group">
                              	<input type="hidden" name="command" value="logination" />
                              </div>
                              <div class="form-group">
                                <input type="text" placeholder="Логин" class="form-control" name="login" required>
                              </div>
                              <div class="form-group">
                                <input type="password" placeholder="Пароль" class="form-control" name="password" required>
                              </div>
                              <button type="submit" class="btn btn-primary" name="Sing in">Вход</button>
                              <a href="pages/register.html" class="btn btn-primary" role="button">Регистрация</a>
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
          <li><a href="#" class="active"> Главная </a></li>
          <li><a href="pages/movies.html" >Фильмы</a></li>
          <li><a href="pages/profile.html" >Профиль</a></li>
          <li><a href="pages/about-us.html" >О нас</a></li>
          <li><a href="pages/news.html" >Новости</a></li>
          <li><a href="pages/widen-search.html" >Расширенный поиск</a></li>
        </ul>
      </div>
    </div>


      <div class="col-md-8 main content ">
        <div class="panel panel-primary">
          <div class=" panel-heading" >
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Новинки </h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html" role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html" role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html"  role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html"  role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html"  role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            <div class="col-md-4">
              <h2 style="text-align:center"><mark>1+1</mark> (2011) </h2>
              <p><b>Режиссер:</b> Оливье Накаш, Эрик Толедано </p>
              <p><b>В ролях:</b> Омар Си, Франсуа Клозе </p>
              <p><b>Жанр:</b> Драма, Комедия </p>
              <img src="img/02-intouchables.jpg" alt="Intouchables" class="img-rounded" width="250" height="160" />
              <br><p>Пострадав в результате несчастного случая, богатый аристократ Филипп нанимает в помощники человека, который менее всего подходит для этой работы, — молодого жителя предместья Дрисса, только что освободившегося из тюрьмы. Несмотря на то, что Филипп прикован к инвалидному креслу, Дриссу удается привнести в размеренную жизнь аристократа дух приключений. </p>
              <p> 
                <a class="btn btn-info" href="pages/order.html"  role="button">20.000 руб.</a>
                <a class="btn btn-link" href="pages/movie.html" role="button">Узнать больше &raquo;</a> 
              </p>
            </div>
            
          </div>
          </div>
<div class="panel panel-primary">
<div class=" panel-heading" >
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Дополнительная информация: </h2>
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

      <div class="col-md-2"> 
      <div id="right-sidebar">

        <figure><img src="img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 1</figcaption> </figure>
        <figure><img src="img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 2</figcaption> </figure>
        <figure><img src="img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 3</figcaption> </figure>
        <figure><img src="img/right-01.jpg" alt="Sidebar Picture" class="img-thumbnail img-responsive" width="210" height="140" /> <figcaption>Новость 4</figcaption> </figure>
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
          <li><a href="#">Главная</a></li>
          <li><a href="pages/movies.html">Фильмы</a></li>
          <li><a href="pages/reviews.html">Рецензии</a></li>
          <li><a href="pages/widen-search.html">Расширенный поиск</a></li>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <li><a href="pages/logination.html">Вход</a></li>
          <li><a href="pages/register.html">Регистрация</a></li>
          <li><a href="pages/profile.html">Профиль</a></li>
          <li><a href="pages/orders.html">Заказы</a></li>
        </ul>
      </div>
      <div class="col-md-2">
        <ul>
          <li><a href="pages/news.html">Новости</a></li>
          <li><a href="pages/feedback.html">Обратная связь</a></li>
          <li><a href="pages/about-us.html">О нас</a></li>
          <li><a href="pages/help.html">Помощь</a></li>
        </ul>
      </div>
    </div>
    <div class="row" style="text-align:right; margin-right:10px;"> Все права защищены. 2016, автор: Дмитрий Наумович. </div>
  </section>
  </footer>
</body>
</html>