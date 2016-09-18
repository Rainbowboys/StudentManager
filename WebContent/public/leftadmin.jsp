<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>top</title>

</head>

<body>
	<div class="sidebar" id="sidebar">
		<script type="text/javascript">
			try {
				ace.settings.check('sidebar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="sidebar-shortcuts" id="sidebar-shortcuts">
			<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
				<button class="btn btn-success">
					<i class="icon-signal"></i>
				</button>

				<button class="btn btn-info">
					<i class="icon-pencil"></i>
				</button>

				<button class="btn btn-warning">
					<i class="icon-group"></i>
				</button>

				<button class="btn btn-danger">
					<i class="icon-cogs"></i>
				</button>
			</div>

			<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
				<span class="btn btn-success"></span> <span class="btn btn-info"></span>

				<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
			</div>
		</div>
		<!-- #sidebar-shortcuts -->

		<ul class="nav nav-list">
			<li><a href="#" class="dropdown-toggle"> <i
					class="icon-desktop"></i> <span class="menu-text"> 管理员管理 </span> <b
					class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/admin/addAdmin.jsp"> <i
							class="icon-double-angle-right"></i> 添加管理员
					</a></li>

					<li><a href="${pageContext.request.contextPath}/admin/AdminServlet?method=tolist"> <i
							class="icon-double-angle-right"></i> 查看 &amp;操作
					</a></li>
				</ul></li>

			<li><a href="#" class="dropdown-toggle"> <i
					class="icon-list"></i> <span class="menu-text"> 学生管理</span> <b
					class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/admin/addStudent.jsp"> <i
							class="icon-double-angle-right"></i>添加学生
					</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/listStudent.jsp"> <i
							class="icon-double-angle-right"></i>查看 &amp;操作
					</a></li>
				</ul></li>

			<li><a href="#" class="dropdown-toggle"> <i
					class="icon-edit"></i> <span class="menu-text"> 教师管理 </span> <b
					class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/admin/addStudent.jsp"> <i
							class="icon-double-angle-right"></i> 添加教师
					</a></li>

					<li><a href="${pageContext.request.contextPath}/admin/listTeacher.jsp"> <i
							class="icon-double-angle-right"></i> 查看 &amp;操作
					</a></li>
				</ul></li>
			<li><a href="#" class="dropdown-toggle"> <i
					class="icon-edit"></i> <span class="menu-text"> 课程管理 </span> <b
					class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="form-elements.html"> <i
							class="icon-double-angle-right"></i> 添加课程
					</a></li>

					<li><a href="form-wizard.html"> <i
							class="icon-double-angle-right"></i> 查看 &amp;操作
					</a></li>
				</ul></li>
			<li><a href="#" class="dropdown-toggle"> <i class="icon-tag"></i>
					<span class="menu-text"> 更多页面 </span> <b
					class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="profile.html"> <i
							class="icon-double-angle-right"></i> 用户信息
					</a></li>

					<li><a href="inbox.html"> <i
							class="icon-double-angle-right"></i> 收件箱
					</a></li>

					<li><a href="pricing.html"> <i
							class="icon-double-angle-right"></i> 售价单
					</a></li>

					<li><a href="invoice.html"> <i
							class="icon-double-angle-right"></i> 购物车
					</a></li>

					<li><a href="timeline.html"> <i
							class="icon-double-angle-right"></i> 时间轴
					</a></li>

					<li><a href="login.html"> <i
							class="icon-double-angle-right"></i> 登录 &amp; 注册
					</a></li>
				</ul></li>

			<li class="active open"><a href="#" class="dropdown-toggle">
					<i class="icon-file-alt"></i> <span class="menu-text"> 其他页面
						<span class="badge badge-primary ">5</span>
				</span> <b class="arrow icon-angle-down"></b>
			</a>

				<ul class="submenu">
					<li><a href="faq.html"> <i class="icon-double-angle-right"></i>
							帮助
					</a></li>

					<li><a href="error-404.html"> <i
							class="icon-double-angle-right"></i> 404错误页面
					</a></li>

					<li><a href="error-500.html"> <i
							class="icon-double-angle-right"></i> 500错误页面
					</a></li>

					<li><a href="grid.html"> <i
							class="icon-double-angle-right"></i> 网格
					</a></li>

					<li class="active"><a href="blank.html"> <i
							class="icon-double-angle-right"></i> 空白页面
					</a></li>
				</ul></li>
		</ul>
		<!-- /.nav-list -->

		<div class="sidebar-collapse" id="sidebar-collapse">
			<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
				data-icon2="icon-double-angle-right"></i>
		</div>

		<script type="text/javascript">
			try {
				ace.settings.check('sidebar', 'collapsed')
			} catch (e) {
			}
		</script>
	</div>
</body>
</html>
