<%@ include file="../taglibs.jinc" %>
<fmt:message key="app.name" var="appName"/>
<fmt:message key="nav.home" var="home"/>
<fmt:message key="nav.events" var="events"/>
<fmt:message key="nav.events.view" var="viewEvent"/>
<fmt:message key="nav.events.create" var="createEvent"/>
<fmt:message key="nav.groups" var="groups"/>
<fmt:message key="nav.groups.view" var="viewGroup"/>
<fmt:message key="nav.groups.create" var="createGroup"/>
<fmt:message key="nav.search.placeholder" var="searchPlaceholder"/>
<fmt:message key="button.search" var="searchBtn"/>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
              data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand text-uppercase" href="#">${appName}</a>
    </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">${home}<span class="sr-only">(current)</span></a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
               aria-expanded="false">${events}<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">${viewEvent}</a></li>
              <li><a href="#">${createEvent}</a></li>
              >
            </ul>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
               aria-expanded="false">${groups}<span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">${viewGroup}</a></li>
              <li><a href="#">${createGroup}</a></li>
              >
            </ul>
          </li>
        </ul>
        <form class="navbar-form navbar-left" role="search">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="${searchPlaceholder}">
          </div>
          <button type="submit" class="btn btn-default">${searchBtn}</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">${currentUser}</a></li>
        </ul>
      </div>
  </div>
</nav>