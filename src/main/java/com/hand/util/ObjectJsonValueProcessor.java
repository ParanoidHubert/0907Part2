package com.hand.util;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 解决JSONObject.fromObject抛出"There is a cycle in the hierarchy"异常导致死循环的解决办法 
 * @author Hubrt
 * @date 2015-09-1 
 */
public class ObjectJsonValueProcessor implements JsonValueProcessor{

	 /** 
     * 需要留下的字段数组 
     */  
    private String[] properties;  
      
    /** 
     * 需要做处理的复杂属性类型 
     */  
    private Class<?> clazz;  
      
    /** 
     * 复杂属性类型的构造方法,参数必须 
     * @param properties 
     * @param clazz 
     */  
    public ObjectJsonValueProcessor(String[] properties,Class<?> clazz){  
        this.properties = properties;  
        this.clazz =clazz;  
    }  
    
    
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		
		return "";
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		 PropertyDescriptor pd = null;  
	        Method method = null;  
	        StringBuffer json = new StringBuffer("{");  
	        try{  
	            for(int i=0;i<properties.length;i++){  
	                pd = new PropertyDescriptor(properties[i], clazz);  
	                method = pd.getReadMethod();
	                System.out.println(properties[i]);
	                String v = String.valueOf(method.invoke(value));  
	                json.append("'"+properties[i]+"':'"+v+"'");  
	                json.append(i != properties.length-1?",":"");  
	            }  
	            json.append("}");
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return JSONObject.fromObject(json.toString());  
	    }  
	

}
