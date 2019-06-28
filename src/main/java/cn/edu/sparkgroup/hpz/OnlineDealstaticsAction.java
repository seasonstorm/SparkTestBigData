package cn.edu.sparkgroup.hpz;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

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
		cn.edu.sparkgroup.hpz.test.getstatics();

		return SUCCESS;
		
	}

}
