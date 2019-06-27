package cn.edu.sparkgroup.hpz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;




@Controller
public class GetdealstaticsAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String,Object> totalmap = new TreeMap<String,Object>();  
	public Map<String, Object> getTotalmap() {
		return totalmap;
	}
	public void setTotalmap(Map<String, Object> totalmap) {
		this.totalmap = totalmap;
	}
	public String execute() throws Exception{	
		BufferedReader  readerboys=new BufferedReader(new FileReader(new File(ServletActionContext.getServletContext().getRealPath("")+"/result/boys/part-00000")));
		BufferedReader  readergirls=new BufferedReader(new FileReader(new File(ServletActionContext.getServletContext().getRealPath("")+"/result/grils/part-00000")));
		BufferedReader  readertypes=new BufferedReader(new FileReader(new File(ServletActionContext.getServletContext().getRealPath("")+"/result/types/part-00000")));		
		List<String>typelist=new ArrayList<String>();
		List<Double>boyslist=new ArrayList<Double>();
		List<Double>girlslist=new ArrayList<Double>();
		Map<String,Object> bmap = new TreeMap<String,Object>();  
		Map<String,Object> gmap = new TreeMap<String,Object>(); 
		String tempString;
	    while ((tempString=readertypes.readLine()) != null) {  
	    	typelist.add(tempString); 
        }
	    readertypes=new BufferedReader(new FileReader(new File(ServletActionContext.getServletContext().getRealPath("")+"/result/types/part-00001")));
	    while ((tempString=readertypes.readLine()) != null) {  
	    	typelist.add(tempString); 
        }
	    Collections.sort(typelist);
	    readertypes.close(); 
	    while ((tempString=readerboys.readLine()) != null) {     	
	    	bmap.put(tempString.split(",")[0].substring(1, tempString.split(",")[0].length()), tempString.split(",")[1].substring(0, tempString.split(",")[1].length()-1));
        }  
	    while ((tempString=readergirls.readLine()) != null) {     	
	    	gmap.put(tempString.split(",")[0].substring(1, tempString.split(",")[0].length()), tempString.split(",")[1].substring(0, tempString.split(",")[1].length()-1));
        }
	    readerboys.close(); 
	    readergirls.close();
	  
	    for(Entry<String, Object> entry : bmap.entrySet()){	       
	        Object mapValue = entry.getValue();
	        boyslist.add(Double.parseDouble(mapValue.toString()));
	    }

	    for(Entry<String, Object> entry : gmap.entrySet()){
	        Object mapValue = entry.getValue();
	        girlslist.add(Double.parseDouble(mapValue.toString()));
	    }
	    

	    totalmap.put("boys",boyslist);
	    totalmap.put("girls",girlslist);
	    totalmap.put("types",typelist);
		
		return SUCCESS;	
	}
	

}
