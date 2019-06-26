package cn.edu.sparkgroup.hpz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;




@Controller
public class GetdealstaticsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resultdata;
	public String getResultdata() {
		return resultdata;
	}
	public void setResultdata(String resultdata) {
		this.resultdata = resultdata;
	}
	public String execute() throws Exception{
//		Map<String,Object> map = new HashMap<String,Object>();  
		BufferedReader  reader=new BufferedReader(new FileReader(new File("D:/result/boys/part-00000")));
		String tempString;
	    while ((tempString=reader.readLine()) != null) {  
           System.out.println(tempString);      
        }  
        reader.close(); 
//		map.put("registerstatus", "0");
//		JSONObject json = JSONObject.fromObject(map); //将map对象转换成json类型数据      
//		resultdata = json.toString();
		return SUCCESS;	
	}
	

}
