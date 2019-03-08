<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<b>Choose a name:</b>
	<select id="show"></select>
	<script>
		$.ajax({
		url:"test.jsp",
		type:"GET",
		success: function(data){
			showNames(data);
			}
		});
		
		function showNames(data) {
			var names=data.split(",");
			$("#show").html("");
			for(i in names){
				var opt = $("<option>").val(names[i]).text(names[i]);
				$("#show").append(opt);
			}
		}
	</script>
</body>
</html>