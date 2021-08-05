package com.servlet.Auth;

import com.ServiceImp.AuthServiceImp;
import com.Util.MyHandler;
import com.Util.UUIDUtil;
import com.domain.Auth;
import com.domain.User;
import com.service.AuthService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(urlPatterns = {"/servlet/SaveAuth"})
public class SaveAuth extends HttpServlet {
    private static Class a=Auth.class;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
        //服务器一次接收的文件大小，若大于该数值，服务器将分块接收
        diskFileItemFactory.setSizeThreshold(1024*1024);
        //若文件大于所设置的文件，不完整的文件将寻出在缓存目录中，若不大于，则直接存储在目标目录中！
        String path="E:\\EGOV项目\\EGOV\\temp\\";//修改路径（以及下面）
        diskFileItemFactory.setRepository(new File(path));

        //核心对象
        ServletFileUpload fileUpload=new ServletFileUpload(diskFileItemFactory);
        fileUpload.setSizeMax(1024*1024*1024);//1G

        //创建Auth对象
        Auth auth=new Auth();
        String fileName=null;
        boolean ok=false;
        try {
           List<FileItem> items= fileUpload.parseRequest(request);
            for (FileItem item:items) {
                if (item.isFormField()){
                    String name=item.getFieldName();
                    String value=item.getString("UTF-8");
                    System.out.println(name+","+value);
                    String MethedName="set"+name.toUpperCase().charAt(0)+name.substring(1);
                    Method method=a.getDeclaredMethod(MethedName,String.class);
                    method.invoke(auth,value);
                }else{
                    fileName=item.getName();
                    auth.setFilename(fileName);
                    String path2="E:\\EGOV项目\\EGOV\\upload\\"+fileName;
                    item.write(new File(path2));
                }
            }
            HttpSession session=request.getSession(false);
            User user=(User)session.getAttribute("user");
            auth.setUsercode(user.getUsercode());
            auth.setAuthno(UUIDUtil.getUUID());
            System.out.println(auth);
            AuthService authService=(AuthService)new MyHandler(new AuthServiceImp()).getProxy();
            ok=authService.saveAuto(auth);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ok){
                response.sendRedirect(request.getContextPath()+"/auth/inputOrg.jsp");
            }else{
                if (fileName!=null||fileName.equals("")){
                    File file=new File("E:\\EGOV项目\\EGOV\\upload\\"+fileName);
                    file.delete();
                }

            }
        }
    }
}
