<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<form method="post" action="http://localhost:8080/Storymap/StoryboardServlet?command=storyboard_images_add" enctype="multipart/form-data" name="frm" id="frm">
		<input type="file" name="storymap_image1">
		<input type="file" name="storymap_image2">
		<input type="hidden" name="sm_code" value="sm16100411">
		<input type="hidden" name="mk_seq" value="1">
		
		<input type="submit" value="전송">
	</form>
</div>

</body>
</html>