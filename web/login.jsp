<%@ page  pageEncoding="UTF-8"%>
<%@include file="include/header.jsp" %>
<div id="loginDiv" style="position: relative">

    <div class="simpleLogo">
        <a href=""><img src="img/site/simpleLogo.png"></a>
    </div>

    <img id="loginBackgroundImg" class="loginBackgroundImg" src="img/site/loginBackground.png">

<form class="loginForm" action="loginRes.jsp" method="post" onsubmit="return validate(this);">
		<input type="hidden" name="type" value="login"/>
        <div id="loginSmallDiv" class="loginSmallDiv">
            <div class="loginErrorMessageDiv">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>
            </div>

            <div class="login_acount_text">账户登录</div>
            <div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-user"></span>
                </span>
                <input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">
            </div>

            <div class="loginInput ">
                <span class="loginInputIcon ">
                    <span class=" glyphicon glyphicon-lock"></span>
                </span>
                <input id="password" name="password" type="password" placeholder="密码" type="text">
            </div>
            <span class="text-danger"></span><br><br>

            <div>
                <a class="notImplementLink" href="#nowhere">忘记登录密码</a>
                <a href="register.jsp" class="pull-right">免费注册</a>
            </div>
            <div style="margin-top:20px">
                <button class="btn btn-block redButton" type="submit">登录</button>
            </div>
        </div>
    </form>
    <script type="text/javascript">
    function validate(loginForm) {
        if(loginForm.name.value == "admin" && loginForm.password.value == "123456")  {
            return true;
        }
        alert("登录失败");
        return false;
    }
    </script>

    </div>

<%@include file="include/footer.jsp" %>