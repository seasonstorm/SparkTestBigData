package cn.edu.sparkgroup.hhy;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;
import  cn.edu.sparkgroup.bean.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;

@Controller
public class searchResultAction extends ActionSupport {
    private String keyword = "";
    private String page = "0";
    private String limit = "10";
    private String[] movies = null;
    private ArrayList<moviesBean> res=new ArrayList<moviesBean>();

    public HashMap<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(HashMap<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    private HashMap<String,Object> dataMap=new HashMap<String,Object>();
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String[] getMovies() {
        return movies;
    }

    public void setMovies(String[] movies) {
        this.movies = movies;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public String execute() throws Exception {
        System.out.println(keyword);
        String[] args = {keyword};
        movies= sparkbigdata.q1.search(args);
        ServletActionContext.getRequest().setCharacterEncoding("utf-8");
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        System.out.println(page+"  "+limit+" "+movies.length);
        for (int i=0;i<movies.length;i++){
            if(i >= (Integer.valueOf(page)-1)*Integer.valueOf(limit) && i<(Integer.valueOf(page)*Integer.valueOf(limit))){
                moviesBean m=new moviesBean();
                System.out.println(i);
               String data=movies[i];
                m.setMovieId(Integer.valueOf(data.split(",")[0]));
                m.setTitle(data.split(",")[1]);
                m.setGenres(data.split(",")[2]);
                m.setHtml("<button type=\"button\" class=\"layui-btn layui-btn-normal\"  style='height:25px;line-height:25px;' onclick=jump(\"/setScore?id="+Integer.valueOf(movies[i].split(",")[0])+"\")>set score</button>");
                res.add(m);
            }
        }
        dataMap.put("code",0);
        dataMap.put("count",movies.length);
        dataMap.put("data",res);
        return SUCCESS;
    }
}
