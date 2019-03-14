<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head >
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" >
	
</head>
<body>


    <h1>添加商品</h1>
    <form action="${pageContext.request.contextPath}/Commodity/inser" method="post" enctype="multipart/form-data" >
		商品名称:<input type="text" name="name"><br/>
		商品价格:<input type="text" name="price"><br/>
		商品描述:<input type="text" name="describe"><br/>
		商品图片:<input type="file" name="addFile"><br/>
		商品状态:<input type="text" name="status"><br/>
		商品生产日期:<input type="text" name="datamanufacture"><br/>
		商品产地:<input type="text" name="origin"><br/>
		<input type="submit" value="提交"><br/>
    </form>


</body>
</html>
