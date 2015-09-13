<%@page import="java.util.ArrayList"%>
<%@page import="com.hand.entity.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>index</title>
<link rel="stylesheet" type="text/css" href="CSS/style.css">
<!--<link rel="stylesheet" href="bootstrap.min.css">-->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div>
		<div class="logo">
			8814郑晓彬

			<!-- Single button -->
			<div class="btn-group userenter">
				<button type="button" class="btn btn-default dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					User<span class="glyphicon glyphicon-user"></span> <span
						class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li role="separator" class="divider"></li>
					<li><a
						href="<%=request.getContextPath()%>/Customer_logout.action">Logout</a></li>
				</ul>
			</div>

		</div>


		<!--导航列表-->
		<!--  <div class="toollist list-group col-md-2">
        <button type="button" class="list-group-item">
            <span class="badge">&gt</span>
            Customor管理</button>
        <button type="button" class="list-group-item">
            <span class="badge">&gt</span>
            Film设置
        </button>
    </div>-->
		<div class="toollist col-md-2">
			<ul class="list-group">
				<li class="list-group-item"><span class="badge">&gt</span><a
					href="index.jsp">Customor管理</a></li>
				<li class="list-group-item"><span class="badge">&gt</span><a
					href="filmindex.jsp">Film设置</a></li>

			</ul>
		</div>

		<div class="container col-md-10">
			<!-- 主体内容 -->
			<div class="usermanagertitle">客户管理</div>
			<div class="usermanagermain">

				<p class="text-left">
					客户列表 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						class="btn btn-primary" role="button" href="filmindex.jsp">新建</a>
					<!-- MODEL模态框 -->
					<!-- Modal -->
				</p>



			</div>

			<!-- 主体内容 -->
			<div class="usertable col-md-12">
				<table class="table table-bordered col-md-12">
					<th>操作</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Email</th>
					<th>Customerid</th>
					<th>lastupdate</th>

					<s:iterator value="cuslist" id="cus">
						<tr>
							<td><button type="button" class="edit btn btn-primary" data-toggle="modal" data-target="#myModal">编辑</button>
                            <button type="button" class="del btn btn-primary">删除</button></td>
							<td class="CusfirstName"><s:property value="#cus.firstName" /></td>
							<td class="CuslastName"><s:property value="#cus.lastName" /></td>
							<td class="Cusaddress"><s:property value="#cus.address.address" /><sapn class="addId hidden"><s:property value="#cus.address.addressId"/></sapn></td>
							<td class="Cusemail"><s:property value="#cus.email" /></td>
							<td class="cus_id" id="<s:property value="#cus.customerId" />"><s:property value="#cus.customerId" /></td>
							<td class="CuslastUpdate"><s:property value="#cus.lastUpdate" /></td>
						</tr>
					</s:iterator>

				</table>
				<!-- 模态框 -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">Edit Customer</h4>
							</div>
							<div class="modal-body">
								<span class="hidden" id="model_cusid"></span>
								<!-- 表单 -->
								<form class="form-horizontal">
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-3 control-label">First
											Name</label>
										<div class="col-sm-7">
											<input type="text" class="form-control" id="firstName"
												placeholder="Your fistName">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-3 control-label">Last
											Name</label>
										<div class="col-sm-7">
											<input type="text" class="form-control"
												id="lastName" placeholder="You lastName">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-3 control-label">Email</label>
										<div class="col-sm-7">
											<input type="email" class="form-control"
												id="email" placeholder="Your email: @example.com">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-3 control-label">Address</label>
										<div class="col-sm-7">
											<div class="dropdown">
												<button id="select_addr" class="btn btn-default dropdown-toggle col-sm-12"
													type="button" id="dropdownMenu1" data-toggle="dropdown"
													aria-haspopup="true" aria-expanded="false">
													<span class="caret"></span>   
												</button>
												<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
													<li><a href="#">Loading...</a></li>
													
												</ul>
											</div>
										</div>
									</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" id="submit" class=" btn btn-primary">Save
									changes</button>
							</div>
						</div>
						</form>
					</div>
				</div>

				<!-- 分页 -->
				<div class="fenye">
					<ul class="pagination">


						<li><a class="last_page" href="#" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>

						</a></li>
						<s:set name="number" value="cuscount" />
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
							<s:param name="first" value="1" />
							<s:param name="last" value="%{#number}" />
							<s:iterator>

								<li><a id="<s:property/>" href="#" class="paging"><s:property /></a></li>
							</s:iterator>
						</s:bean>
						<li><a class="next_page" href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>

					</ul>


				</div>
			</div>

		</div>
	</div>
	<script src="JS/jquery-2.1.4.min.js"></script>
	<script src="JS/bootstrap.min.js"></script>
	<script src="JS/jquery-ui.min.js"></script>
	<script src="JS/jPaginator-min.js"></script>
	<script src="JS/myjs.js"></script>

</body>
</html>