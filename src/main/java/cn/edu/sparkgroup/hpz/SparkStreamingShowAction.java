package cn.edu.sparkgroup.hpz;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

@Controller
public class SparkStreamingShowAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    public String execute(){
        return SUCCESS;
    }
}
