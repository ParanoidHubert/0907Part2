package com.hand.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hand.entity.Address;
import com.hand.entity.Customer;
import com.hand.service.impl.AddressServiceImpl;
import com.hand.service.impl.CusServiceImpl;
import com.hand.util.ObjectJsonValueProcessor;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

//@Action("Customer")
public class CustomerAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4634175981265924020L;
	/**
	 * 注入CusService
	 */
	@Autowired
	private CusServiceImpl CusService;
	@Autowired
	private AddressServiceImpl addressService;

	//登录名
	private String firstName;
	private String lastName;

	//获取客户pagestart页的信息（20条）
	private String pagestart;
	//获取指定要删除/修改的客户id
	private String cusid;

	//获取从页面请求的customer的属性
	private String email;
	private String addressId;
	
	



	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCusid() {
		return cusid;
	}
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	public String getPagestart() {
		return pagestart;
	}
	public void setPagestart(String pagestart) {
		this.pagestart = pagestart;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String login(){
		List<Customer> list = CusService.getCusList(0);

		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("cuslist", list);
		context.put("cuscount", CusService.getCusCount()/20+1);
		stack.push(context);
		//System.out.println("Size of the valueStack: " + stack.size());
		Customer cus = new Customer();
		cus.setFirstName(firstName);
		cus.setLastName(lastName);
		//System.out.println(CusService.checkLogin(cus));
		if(CusService.checkLogin(cus))
		{
			//取得session
			ActionContext actionContext = ActionContext.getContext();
			Map<String, Object> session = actionContext.getSession();
			session.put("flag", "login");
			return "login";
		}
		else return ERROR;
	}

	//分页ajax加载
	public void loadmore(){
		System.out.println("in Cus loadmore");
		List<Customer> cuslist = CusService.getCusList((Integer.valueOf(pagestart)-1)*20);
		//System.out.println(pagestart);
		//数据获取正确
		//		for(int i = 0 ;i<cuslist.size();i++){
		//			System.out.println(cuslist.get(i).getAddress().getAddress());
		//		}
		//outputJson(cuslist);

		//		for(int i = 0;i<cuslist.size();i++){
		//			Customer cus = cuslist.get(i);
		//			
		//		}
		try {
			//Gson gson = new Gson();
			//		String json = gson.toJson(cuslist);
					JsonConfig jsonConfig = new JsonConfig(); //建立配置
					jsonConfig.registerJsonValueProcessor(Address.class,
							new ObjectJsonValueProcessor(new String[]{"address","addressId"}, Address.class));
					jsonConfig.setExcludes(new String[]{"store","rentals","payments"});
					JSONArray json = JSONArray.fromObject(cuslist, jsonConfig);
					String json1 = json.toString();
					System.out.println(json1);
					outputJson(json1);
					}
		catch(Exception e){
				e.printStackTrace();
				}	
			
//			JsonConfig jsonConfig1 = new JsonConfig(); //建立配置
//			jsonConfig1.setIgnoreDefaultExcludes(false);  //设置默认忽略	
//			jsonConfig1.setExcludes(new String[]{"address","store","rentals","payments"});   //将所需忽略字段加到数组中
//			JSONArray json1 = JSONArray.fromObject(cuslist,jsonConfig1);
			
//			List<Address> addrlist = new ArrayList<Address>();
//			for(int i =0;i<cuslist.size();i++){
//				Address addr = new Address();
//				addr = (cuslist.get(i).getAddress());
//				addrlist.add(addr);
//			}
//			JsonConfig jsonConfig2 = new JsonConfig(); //建立配置
//			jsonConfig2.setIgnoreDefaultExcludes(false);  //设置默认忽略	
//			jsonConfig2.setExcludes(new String[]{"address","store","rentals","payments"});   //将所需忽略字段加到数组中
//			JSONArray json2 = JSONArray.fromObject(addrlist,jsonConfig2);
//			System.out.println("json2:"+json2);
//			//合并两个Json
//			JSONArray alljson = new JSONArray();
//			alljson.addAll(json1);
//			alljson.addAll(json2);
//			
//			System.out.println(json);
//			HttpServletResponse resp = ServletActionContext.getResponse();
//			PrintWriter out= resp.getWriter();
//			out.println(json);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "notlogin";
	}

	public void delete(){
		System.out.println("in Cus delete");
		System.out.println("will delete customer_id:"+cusid);
		boolean bool = CusService.delCus(Short.valueOf(cusid));
		Gson gson = new Gson();
		String json = gson.toJson(bool);
		outputJson(json);
	}

	public void addcus(){
		System.out.println("in Cus addcus");
		System.out.println(addressId+firstName+lastName+email+addressId);
		Customer cus = new Customer();
		cus.setFirstName(firstName);
		cus.setLastName(lastName);
		cus.setEmail(email);
		cus.setAddress(addressService.getAddress(Short.valueOf(addressId)));
		
//		st.setStoreId((Byte)1);
		cus.setStore(CusService.getstoreforadd((byte) 1));
		cus.setActive(true);
		cus.setCreateDate(new Date());
		cus.setLastUpdate(new Date());
		System.out.println("cus:id"+cus.getAddress().getAddress());
		boolean bool = CusService.addCus(cus);
		System.out.println("BOOL:"+bool);
		Gson gson = new Gson();
		String json = gson.toJson(bool);
		outputJson(json);


	}

	public void updateCus(){
		System.out.println("in Cus updatecus");
		//取得持久化对象
		System.out.println("cusid:"+cusid);
		System.out.println("addId:"+addressId);
		Customer cus = CusService.getCus(Short.valueOf(cusid));
		cus.setCustomerId(Short.valueOf(cusid));
		cus.setFirstName(firstName);
		cus.setLastName(lastName);
		//无法进入addressService中
		cus.setAddress(addressService.getAddress(Short.valueOf(addressId)));
		cus.setEmail(email);
		cus.setLastUpdate(new Date());
		System.out.println("before goto Cusservice");
		boolean bool = CusService.editCus(cus);
		System.out.println("after update:"+bool);
		Gson gson = new Gson();
		String json = gson.toJson(bool);
		outputJson(json);

	}



	public void outputJson(String json){
		try {
			//Gson无法将Hibernate关联表对象转换成Json

//			Gson gson = new Gson();
//			String json = gson.toJson(object);
			System.out.println(json);
			HttpServletResponse resp = ServletActionContext.getResponse();
			PrintWriter out = resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
