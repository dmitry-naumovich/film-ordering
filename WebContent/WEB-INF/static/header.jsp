<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
                              <c:out value="${errorMessage}" />
                              <div class="form-group">
                              	<input type="hidden" name="command" value="logination" />
                              </div>
                              <div class="form-group">
                                <input type="text" placeholder="Логин" class="form-control" name="login" required>
                              </div>
                              <div class="form-group">
                                <input type="password" placeholder="Пароль" class="form-control" name="password" required>
                              </div>
                              <button type="submit" class="btn btn-primary" name="Sign in">Вход</button>
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