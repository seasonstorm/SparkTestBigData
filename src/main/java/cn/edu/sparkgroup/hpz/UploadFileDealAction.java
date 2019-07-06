package cn.edu.sparkgroup.hpz;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadFileDealAction  extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private File upload;//定义一个File ,变量名要与jsp中的input标签的name一致
    private String uploadContentType;//上传文件的mimeType类型
    private String uploadFileName;//上传文件的名称
    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void uploadFile() throws IOException {
//        String path = ServletActionContext.getServletContext().getRealPath("/upload");
        String path="D://upload";

        String filename= RandomStringUtils.randomAlphanumeric(20);

        String tailfilename=uploadFileName.substring(uploadFileName.lastIndexOf("."));
        //创建一个服务器端的文件
        File dest = new File(path,filename+tailfilename);
        FileUtils.copyFile(upload, dest);
        System.out.println(uploadFileName);
    }

}
