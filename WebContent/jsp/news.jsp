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
          <h2 class=" text-left" style="margin:0px; padding:0px;"> Новости сервиса </h2>
          </div> 
          <div class="row panel-body">
            <div class="col-md-12">
                
                
                    <div class="panel panel-default">
                        <div class=" panel-heading" >
                          
                          <h4 class=" text-right" style="margin-bottom:0px; padding-bottom:0px;"> 09.07.2016 - 14:51 </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/news-01-01.jpg" alt="Img failed" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px; margin-right: 0px"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <br> <br> <p style="text-align:justify; margin-left: -10px;"> Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.

                                Quo malis omittam cu, est vocent scaevola mnesarchum ei. Malis nonumy temporibus at pro, te essent molestie assueverit vel. Maiorum consetetur vel ex, mea mucius viderer te. Ad brute principes scriptorem mei, vis ne ridens neglegentur. Ut iuvaret scriptorem cum, id eos agam ocurreret. Per in quem dicat diceret, natum melius consectetuer ne eam. Cu nonumes adipisci neglegentur vel.

                 
                  </p>

                          </div>
                          
                        </div>
                        </div>
                        </div>

                        <div class="panel panel-default">
                        <div class=" panel-heading" >
                          
                          <h4 class=" text-right" style="margin-bottom:0px; padding-bottom:0px;"> 09.07.2016 - 10:14 </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/news-02-01.jpg" alt="Img failed" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px; margin-right: 0px"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <br> <br> <p style="text-align:justify; margin-left: -10px;"> Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.

                                Quo malis omittam cu, est vocent scaevola mnesarchum ei. Malis nonumy temporibus at pro, te essent molestie assueverit vel. Maiorum consetetur vel ex, mea mucius viderer te. Ad brute principes scriptorem mei, vis ne ridens neglegentur. Ut iuvaret scriptorem cum, id eos agam ocurreret. Per in quem dicat diceret, natum melius consectetuer ne eam. Cu nonumes adipisci neglegentur vel.

                 
                  </p>

                          </div>
                          
                        </div>
                        </div>
                        </div>

                        <div class="panel panel-default">
                        <div class=" panel-heading" >
                          
                          <h4 class=" text-right" style="margin-bottom:0px; padding-bottom:0px;"> 08.07.2016 - 12:02 </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/news-03-01.jpg" alt="Img failed" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px; margin-right: 0px"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <br> <br> <p style="text-align:justify; margin-left: -10px;"> Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.

                                Quo malis omittam cu, est vocent scaevola mnesarchum ei. Malis nonumy temporibus at pro, te essent molestie assueverit vel. Maiorum consetetur vel ex, mea mucius viderer te. Ad brute principes scriptorem mei, vis ne ridens neglegentur. Ut iuvaret scriptorem cum, id eos agam ocurreret. Per in quem dicat diceret, natum melius consectetuer ne eam. Cu nonumes adipisci neglegentur vel.

                 
                  </p>

                          </div>
                          
                        </div>
                        </div>
                        </div>

                        <div class="panel panel-default">
                        <div class=" panel-heading" >
                          
                          <h4 class=" text-right" style="margin-bottom:0px; padding-bottom:0px;"> 05.07.2016 - 22:51 </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <div class="col-md-4">
                            
                              <figure>
                                <img src="img/news-04-01.jpg" alt="Img failed" class="img-thumbnail img-responsive" width="210" height="140" style="margin-top: 30px; margin-right: 0px"/> 
                              </figure>
                          </div>
                          <div class="col-md-8">
                              <br> <br> <p style="text-align:justify; margin-left: -10px;"> Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.

                                Quo malis omittam cu, est vocent scaevola mnesarchum ei. Malis nonumy temporibus at pro, te essent molestie assueverit vel. Maiorum consetetur vel ex, mea mucius viderer te. Ad brute principes scriptorem mei, vis ne ridens neglegentur. Ut iuvaret scriptorem cum, id eos agam ocurreret. Per in quem dicat diceret, natum melius consectetuer ne eam. Cu nonumes adipisci neglegentur vel.

                 
                  </p>

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