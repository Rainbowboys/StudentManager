<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/public/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset="utf-8" >
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<jsp:include page="/public/pub.jsp"></jsp:include>
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
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="table-responsive">
								<table id="sample-table-1"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center"><label> <input
													type="checkbox" class="ace" /> <span class="lbl"></span>
											</label></th>
											<th>Id</th>
											<th>名字</th>
											<th class="hidden-480">电话号码</th>

											<th><i class="icon-time bigger-110 hidden-480"></i> 密码</th>
											<th class="hidden-480">Super</th>
											<th></th>
										</tr>
									</thead>

									<tbody>

										<!-- forEach遍历出adminBeans -->
										<c:forEach items="${requestScope.adminBeans}" var="item"
											varStatus="status">
											<tr>
												<td class="center"><label> <input
														type="checkbox" class="ace" /> <span class="lbl"></span>
												</label></td>
												<td>${item.id}</td>
												<td><a>${item.name}</a></td>
												<td class="hidden-480"><a>${item.telphone}</a></td>
												<td>${item.password}</td>
												<c:if test="${item.isSuper=='0'}">
													<td class="hidden-480"><span
														class="label label-sm label-warning">普通管理员</span></td>

												</c:if>
												<c:if test="${item.isSuper=='1'}">
													<td class="hidden-480"><span
														class="label label-sm label-danger">超级管理员</span></td>

												</c:if>
												<td>
													<div
														class="visible-md visible-lg hidden-sm hidden-xs btn-group">
														<a href="javascript:void((0)" class="btn btn-xs btn-success"> <i
															class="icon-ok bigger-120"></i>
														</a> <a
															href="${pageContext.request.contextPath}/admin/AdminServlet?method=toupdate&id=${item.id}"
															class="btn btn-xs btn-info"> <i
															class="icon-edit bigger-120"></i>
														</a>
														<c:if test="${item.isSuper=='0'}">
															<a
																href="${pageContext.request.contextPath}/admin/AdminServlet?method=delete&id=${item.id}"
																class="btn btn-xs btn-danger"> <i
																class="icon-trash bigger-120"></i>
															</a>
														</c:if>
														<c:if test="${item.isSuper=='1'}">
														
															<button href="javascript:void(0)" 
																class="btn btn-xs btn-danger  popover-show"  data-toggle="popover" title="删除提示"
																data-content="超级管理员不允许删除"> <i
																class="icon-trash bigger-120"></i>
															</button>
														</c:if>
														<a class="btn btn-xs btn-warning"> <i
															class="icon-flag bigger-120"></i>
														</a>
													</div>

												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="row-fluid">
									<div class="col-md-4"></div>
									<div class="col-md-6">
										<div>
											<util:page pageBean="${pageBean}" />
										</div>
									</div>
									<div class="col-md-2"></div>
								</div>
							</div>
							<!-- /.table-responsive -->

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
	
	<script type="text/javascript">

	$(function () { $('.popover-show').popover('hide');});
	
	</script>

	
</body>
</body>
</html>