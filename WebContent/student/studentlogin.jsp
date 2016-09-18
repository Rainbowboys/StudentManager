<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@include file="/public/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="/public/pub.jsp"></jsp:include>
<title>login</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#account").blur(function(event) {
			var account = $(this).val();
			
			if(account!=""){
				$.ajax({
					url : '${pageContext.request.contextPath}/student/StudentServlet',
					type : 'post',
					dataType : 'json',
					data : {
						account : account,
						method : "chkuser"
					},
					success : function(json) {
						if (json.success) {
							$("#accountalert").html(json.msg);
						} else {
							$("#accountalert").html("");
						}
					}
				})
				
			}
		})
	})
</script>
</head>
<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red">Student</span>
								<span class="white">Manager</span>
							</h1>
							<h4 class="blue">&copy; 学生登录</h4>
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 请输入账号信息
										</h4>

										<div class="space-6"></div>
                                         <c:if test="${param.status.equals('1')}">
					                    <div class="alert alert-danger" role="alert">用户名或密码错误</div>
			                             	</c:if>
                                         <c:if test="${param.status.equals('2')}">
					                    <div class="alert alert-danger" role="alert">请先登录</div>
			                             	</c:if>
										<form role="form"
											action="${pageContext.request.contextPath}/student/StudentServlet?method=login"
											method="post" id="loginform">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" name="account"
														placeholder="Username"  id="account"/> <i class="icon-user"></i>
												</span>
											<span id="accountalert" style="color:red;	font-size: 14px;"></span>
												</label>
												<label class="block clearfix" for="password"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" name="password"
														id="inputPassword" placeholder="Password" /> <i
														class="icon-lock"></i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox"
														class="ace" /> <span class="lbl"><small>记住账户</small></span>
													</label>

													<button type="submit"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>

										<div class="social-or-login center">
											<span class="bigger-110">第三方登录</span>
										</div>

										<div class="social-login center">
											<a class="btn btn-primary"> <i class="icon-facebook"></i>
											</a> <a class="btn btn-info"> <i class="icon-twitter"></i>
											</a> <a class="btn btn-danger"> <i class="icon-google-plus"></i>
											</a>
										</div>
									</div>
									<!-- /widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="#" onclick="show_box('forgot-box'); return false;"
												class="forgot-password-link"> <i class="icon-arrow-left"></i>
												忘记密码
											</a>
										</div>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>输入手机号用于找回密码</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="tel" class="form-control" placeholder="Telphone" />
														<i class="icon-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i> 发送验证码!
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> 返回登录 <i
											class="icon-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /forgot-box -->

							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header green lighter bigger">
											<i class="icon-group blue"></i> New User Registration
										</h4>

										<div class="space-6"></div>
										<p>Enter your details to begin:</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="Email" />
														<i class="icon-envelope"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="Username" />
														<i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="Password" /> <i class="icon-lock"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="Repeat password" /> <i class="icon-retweet"></i>
												</span>
												</label> <label class="block"> <input type="checkbox"
													class="ace" /> <span class="lbl"> I accept the <a
														href="#">User Agreement</a>
												</span>
												</label>

												<div class="space-24"></div>

												<div class="clearfix">
													<button type="reset" class="width-30 pull-left btn btn-sm">
														<i class="icon-refresh"></i> Reset
													</button>

													<button type="button"
														class="width-65 pull-right btn btn-sm btn-success">
														Register <i class="icon-arrow-right icon-on-right"></i>
													</button>
												</div>
											</fieldset>
										</form>
									</div>

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> <i class="icon-arrow-left"></i>
											Back to login
										</a>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /signup-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<!-- /.main-container -->
	<!-- inline scripts related to this page -->

	<script type="text/javascript">
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
		type="text/javascript"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/IDK.js"></script>

</body>
</html>