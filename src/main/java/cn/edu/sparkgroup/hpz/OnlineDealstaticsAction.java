package cn.edu.sparkgroup.hpz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.spark.sql.types.ObjectType;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import scala.Array;

@Controller
public class OnlineDealstaticsAction extends ActionSupport{

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
	public String execute() throws Exception {


//		cn.edu.sparkgroup.hpz.test.getstatics();
		List<String> typelist=new ArrayList<String>();
		List<Double>boyslist=new ArrayList<Double>();
		List<Double>girlslist=new ArrayList<Double>();
		java.lang.String[][]totalarr=cn.edu.sparkgroup.hpz.test.getstatics();

		for(int i=0;i<totalarr[0].length;i++){
			typelist.add(totalarr[0][i]);
			System.out.println(totalarr[0][i]);
		}
		System.out.println("*****************************");
		for(int i=0;i<totalarr[1].length;i++){
			System.out.println(totalarr[1][i]);
			boyslist.add(Double.parseDouble(totalarr[1][i]));
		}
		System.out.println("*****************************");
		for(int i=0;i<totalarr[2].length;i++){
			System.out.println(totalarr[2][i]);
			girlslist.add(Double.parseDouble(totalarr[2][i]));
		}
		totalmap.put("boys",boyslist);
		totalmap.put("girls",girlslist);
		totalmap.put("types",typelist);


		return SUCCESS;
		
	}

}
