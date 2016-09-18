<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/public/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="/public/pub.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		$("#account").blur(function(event) {
			var account = $(this).val();
			$.ajax({
				url : '${pageContext.request.contextPath}/teacher/TeacherServlet',
				type : 'post',
				dataType : 'json',
				data : {
					account : account,
					method : "chkuserReg"
				},
				success : function(json) {
					debugger;
					if (json.success) {
						$("#accountalert").html(json.msg);
					} else {
						$("#accountalert").html(json.msg);
					}

				}
			})
		})
	})
</script>
<title>学生管理系统后台</title>
</head>
<body>
	<jsp:include page="/public/topadmin.jsp"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<jsp:include page="/public/leftadmin.jsp"></jsp:include>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">Home</a>
						</li>
						<li><i class="active"></i> <a href="#">欢迎页面</a></li>
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off" /> <i
								class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>欢迎学生管理系统</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-3"></div>
						<div class="col-xs-6">
							<!-- PAGE CONTENT BEGINS -->


							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header green lighter bigger">
											<i class="icon-group blue"></i> New Student Registration
										</h4>

										<div class="space-6"></div>
										<p>Enter your details to begin:</p>

										<form id="myform"
											action="${pageContext.request.contextPath}/teacher/TeacherServlet?method=regStudent&updateId=${updatebean.id}"
											method="post">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="Telphone"
														name="account" id="account" value="${updatebean.tel}" /> <i class="icon-envelope"></i>
												</span> <span id="accountalert"
													style="color: red; font-size: 12px;"></span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														name="username" type="text" class="form-control"
														placeholder="Username" value="${updatebean.username}" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" name="password"
														id="inputpassword" placeholder="Password"  value="${updatebean.password}" /> <i
														class="icon-lock"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" name="passwordrepeat"
														placeholder="Repeat password" value="${updatebean.password}"/> <i class="icon-retweet"></i>
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

													<button type="submit"
														class="width-65 pull-right btn btn-sm btn-success">
														Register <i class="icon-arrow-right icon-on-right"></i>
													</button>
												</div>
											</fieldset>
										</form>
									</div>
								</div>
								<c:if test="${param.status=='1'}">
									<div class="alert alert-danger" role="alert">添加成功</div>
								</c:if>
								<c:if test="${param.status=='2'}}">
									<div class="alert alert-danger" role="alert">添加失败</div>
								</c:if>
								<c:if test="${param.status=='3'}">
									<div class="alert alert-danger" role="alert">修改成功</div>
								</c:if>
								<c:if test="${param.status=='4'}}">
									<div class="alert alert-danger" role="alert">修改失败</div>
								</c:if>
								<!-- /widget-body -->
							</div>
							<!-- /signup-box -->
							<div class="col-xs-3"></div>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<jsp:include page="/public/container.jsp"></jsp:include>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	<!-- inline scripts related to this page -->
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
</body>
</html>