$(document).ready(function(){
	
	//ajax拼接跨域
	var localObj = window.location;
	var contextPath = localObj.pathname.split("/")[1];
	var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
	var server_context=basePath;
	//点击下拉菜单按钮，将请求的address加载到下拉列表中
	$(".dropdown").find("#select_addr").click(function(){
		console.log(server_context+"/Address_getaddress.action");
		$.ajax({
			url:server_context+"/Address_getaddress.action",
			dataType:'json',
			error:function(){console.log("ajax,error!")},
			success:function(json)
			{
				console.log(json);
				
				var str = "";
				if(json.length){
					$.each(json,function(index,array){
						//str += '<li> <a href="#" id = "'+array.addressId+'">'+array.address+'</a></li>';
						str += '<li> <a href="#" id="'+array[1]+'">'+array[0]+'</a></li>';
					})
					
				}
				//json.length跳出添加元素
				$(".dropdown").find(".dropdown-menu").empty();
				$(".dropdown").find(".dropdown-menu").append(str);
				//给刚添加的a标签绑定点击事件
				$(".dropdown-menu li a").bind("click", function (){
					//alert("in li a bind");
					//alert($(this).text());
					//alert($(this).attr("id"));
					$("#select_addr").text($(this).text());
					$("#select_addr").append('<span class="hidden">'+$(this).attr("id")+'</span>');
				})
			}
		});
	})
	
	//下拉菜单选中，给列表a标签绑定事件，点击显示到按钮中
	$(".dropdown-menu li a").bind("click", function (){
		alert("in li a bind");
		$("#select_addr").val($(this).text());
	})
	//点击添加到后台
	$(".addcus").find(".submit").click(function(){
		var $firstName = $("#inputfirstName").val();
		
		var $lastName = $("#inputlastName").val();
		var $email = $("#inputEmail").val();
		//var $address = $("#select_addr").text();
		var $addressId = $("#select_addr span").text();
		console.log("firstName:"+$firstName);
		console.log("firstName:"+$lastName);
		console.log("firstName:"+$email);
		console.log("addressId:"+$addressId);
		$.ajax({
			url:server_context+"/Customer_addcus.action",
			data:{
				firstName:$firstName,
				lastName:$lastName,
				email:$email,
				addressId:$addressId
				},
			dataType:'json',
			error:function(){alert("ajax error!!!")},
			success:function(json){
					alert(json);
					console.log(json);
					if(JSON.stringify(json)=="true"){alert("删除成功")}
					else alert("插入失败");
					
				}
					
			});
	})
	
	
	
	
})