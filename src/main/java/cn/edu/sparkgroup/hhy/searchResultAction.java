package cn.edu.sparkgroup.hhy;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;
import  cn.edu.sparkgroup.bean.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        String[] args = {keyword, page, limit};
        movies= sparkbigdata.q1.search(args);
        System.out.println(page+"  "+limit+" "+movies.length);
        for (int i=0;i<movies.length;i++){
            if(i >= Integer.valueOf(page)*Integer.valueOf(limit) && i<((Integer.valueOf(page)+1)*Integer.valueOf(limit))){
                moviesBean m=new moviesBean();
                System.out.println(i);
                m.setMovieId(Integer.valueOf(movies[i].split(",")[0]));
                m.setTitle(movies[i].split(",")[1]);
                m.setGenres(movies[i].split(",")[2]);
                m.setHtml("<div><div id=\"test4\"></div></div>");
                res.add(m);
            }
        }
        dataMap.put("code",0);
        dataMap.put("count",movies.length);
        dataMap.put("data",res);
        return SUCCESS;
    }
}
