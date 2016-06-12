<%@ include file="../taglibs.jinc" %>
<div id="top-panel"></div>
<div id="login-container" class="container-fluid">
  <div id="wrapper">
    <form:form id="loginform" method="post">
      <div id="login-body">
        <div id="section-header">
          <span id="welcomeMsg">Let's GetTogether</span>
        </div>
        <div id="section-body"></div>
        <input name="username" class="form-control" type="text" placeholder="Username"/>
        <input name="password" class="form-control" type="password" placeholder="Password"/>
        <div class="checkbox">
          <label>
            <input type="checkbox"> Remember me
          </label>
        </div>
        <div id="section-footer">
          <div>
            <button type="submit" class="btn btn-primary btn-md btn-block">Login</button>
          </div>
          <div>
            <button id="login_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
            <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
          </div>
          <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <div id="loginErrorMessageBox">
              <div class="form-output error">
                <fmt:message key="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
              </div>
            </div>
          </c:if>
        </div>
      </div>
    </form:form>
  </div>
</div>