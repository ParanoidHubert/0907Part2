<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- forward指令使用page来指定要重定向的文件或url -->
<!-- forward传递参数需要param指令，用来设置将要传递的参数  -->
<jsp:forward page="login.jsp">
	<jsp:param value="zxb" name="username" />
	<jsp:param value="hubert" name="password" />
</jsp:forward>

<!-- forward指令下面的代码是不会被执行，也不会被输出到客户端的 -->