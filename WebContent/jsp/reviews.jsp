<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="local.common.serviceName" var="serviceName" />
<fmt:message bundle="${loc}" key="local.reviews.pageTitle" var="pageTitle" />
<fmt:message bundle="${loc}" key="local.reviews.pageHeader" var="pageHeader" />
<fmt:message bundle="${loc}" key="local.reviews.author" var="author" />
<fmt:message bundle="${loc}" key="local.reviews.type" var="type" />
<fmt:message bundle="${loc}" key="local.reviews.mark" var="mark" />
<fmt:message bundle="${loc}" key="local.reviews.date" var="date" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${serviceName} - ${pageTitle}</title>
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
                    <div class="panel panel-default container-fluid">
                        <div class="row panel-heading" >
                        	<div class="col-md-6">
                        		<h4 class=" text-left" style="margin:0px; padding:0px;"> Фильм </h4>
                        	</div>
                        	<div class="col-md-6">
                        		<h4 class=" text-right" style="margin:0px; padding:0px;"> ${author}: </h4>
                        	</div>
                        </div> 
                    	<div class="row panel-body">
	                        <div class="col-md-12">
	                          <p> <br>
	                              Id probo mutat est. Qui ut prompta philosophia. Sea eu tritani sapientem suscipiantur, ad recteque ocurreret reformidans nam. Putant diceret his ne, nam quidam option id, primis numquam no cum.
	
	                              Ne volumus qualisque eloquentiam sit, eius patrioque at usu. Sit odio clita inciderint ea, per ignota menandri inciderint et. No eum nisl mutat corpora. Vel assum percipitur ne, modus dolorem signiferumque per ne.
	
	                               reformidans definitionem no, cu sale percipit maluisset mea. Accusam gloriatur eu sea. Omittam honestatis his ei. Ad melius aliquid sit. Sumo graeci erroribus et mea, fierent liberavisse mediocritatem no mei, illud facilis epicurei cum id. Ei adipisci eleifend similique mel, eos no putent deterruisset.
	
	                              Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.
	                          </p>
	                        </div>
                        </div>
                        <div class="row panel-footer" >
                        
                        	<div class="col-md-4">
                        		<h5 class="text-left">${type}: positive</h5>
                        	</div>
                        	<div class="col-md-4">
                        		<h5 class="text-center">${mark}: 4/5</h5>
                        	</div>
                        	<div class="col-md-4">
                        		<h5 class="text-right">${date}: 14.06.16</h5>
                        	</div>
                        </div>
                        
                        </div>

                         <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Бойцовский клуб</h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <p> <br>
                              Id probo mutat est. Qui ut prompta philosophia. Sea eu tritani sapientem suscipiantur, ad recteque ocurreret reformidans nam. Putant diceret his ne, nam quidam option id, primis numquam no cum.

                              Ne volumus qualisque eloquentiam sit, eius patrioque at usu. Sit odio clita inciderint ea, per ignota menandri inciderint et. No eum nisl mutat corpora. Vel assum percipitur ne, modus dolorem signiferumque per ne.

                               reformidans definitionem no, cu sale percipit maluisset mea. Accusam gloriatur eu sea. Omittam honestatis his ei. Ad melius aliquid sit. Sumo graeci erroribus et mea, fierent liberavisse mediocritatem no mei, illud facilis epicurei cum id. Ei adipisci eleifend similique mel, eos no putent deterruisset.

                              Eos nemore corrumpit aliquando ea, congue discere ut sed. Quo ei stet admodum dissentias, nec ex omnesque argumentum, et sea novum exerci. Eu nemore commune imperdiet pro. Id per aliquip fabellas, error fastidii has ex.
                          </p>
                        </div>
                        </div>
                        </div>

                         <div class="panel panel-default">
                        <div class=" panel-heading" >
                          <h4 class=" text-left" style="margin-bottom:0px; padding-bottom:0px;"> Босиком по мостовой </h4>
                        </div> 
                    <div class="row panel-body">
                        <div class="col-md-12">
                          <p> <br>
                              То, что я сопереживал не каждому герою по отдельности а симбиозу двух героев. Маленькая деталь, которая произвела на меня большое впечатление — выбор актера. В реальной жизни Дрисс был арабским парнем. Араб или человек негроидной расы — в жизни это не сыграло ни какой роли, но в фильме… богатый и бедный, образованный и не умеющий читать, инвалидная коляска и здоровый сильный амбал, «это на интеллектуальном и эмоциональном уровне, мне это важнее физической близости» и «вот увидишь, я с ней обязательно пересплю» и, наконец — белый и черный. Дальше можно продолжать — свет и мрак, инь и янь, добро и зло, мгновение и вечность ну и всякие другие пафосные полярности. Но именно такой ход мыслей позволил мне воспринимать этих двух как что-то одно целое. 

                          </p>
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