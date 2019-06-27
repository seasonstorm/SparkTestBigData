package cn.edu.sparkgroup.hhy;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;


@Controller
public class searchMovieAction extends ActionSupport{
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    private String keyword=null;
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String execute() throws Exception{
        return SUCCESS;
    }

}
