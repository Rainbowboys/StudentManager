<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-form.js"></script>
<title>Insert title here</title>
</head>
<body>


	<form id="myform" action="" enctype="multipart/form-data">

		<input type="text" name="username">
		 <input type="password" name="password">
		 <input type="file" name="file">
		  <input type="submit" value="提交">
		<div id="inputout"></div>

	</form>


	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : 'test/TestServlet?method=AjaxFormUpfile',
				target : "#inputout",
				type : 'POST',
				dataType : "json",
				clearForm : false,
				beforeSubmit : showRequest,
				success : function(data) {
					$("#inputout").html(data);
				}
			}
			//    ajaxForm  ajaxSubmit 两种提交方式
			//   $('#myform').ajaxForm(options);
			$('#myform').submit(function() {
				$(this).ajaxSubmit(options);
				return false;
			})

			function showRequest() {
				if ($("input[name='username']").val() == 0) {
					alert("不能为空");
					return false;

				}

			}

		})
	</script>

</body>
</html>