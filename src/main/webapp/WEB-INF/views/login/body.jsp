<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="top-panel"></div>
<div id="login-container" class="container-fluid">
  <div id="login-box">
    <form id="login-form">
      <div class="panel panel-login">
        <div class="login-body">
          <div id="div-login-msg">
            <span id="text-login-msg">Welcome to GetTogether</span>
          </div>
          <input id="login_username" class="form-control" type="text"
                 placeholder="Username (type ERROR for error effect)"
                 required>
          <input id="login_password" class="form-control" type="password" placeholder="Password" required>
          <div class="checkbox">
            <label>
              <input type="checkbox"> Remember me
            </label>
          </div>
        </div>
        <div class="modal-footer">
          <div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
          </div>
          <div>
            <button id="login_lost_btn" type="button" class="btn btn-link">Lost Password?</button>
            <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>