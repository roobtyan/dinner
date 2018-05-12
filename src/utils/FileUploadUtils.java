package utils;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.UUID;

/**
 * @author 言立慧
 * 描述：上传文件工具类
 */
public class FileUploadUtils {
    public static String upLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*----文件上传组件，文件上传处理-----*/
        try {
            //文件工厂
            FileItemFactory itemFactory = new DiskFileItemFactory();
            //创建文件上传工具类
            ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);

            //配置
            //设置文件的大小
            fileUpload.setFileSizeMax(10 * 1024 * 1024);
            //设置文件上传表单的名的编码
            fileUpload.setHeaderEncoding("UTF-8");

            //判断是否为文件上传表单(不推荐使用类名访问静态成员变量)
            if (ServletFileUpload.isMultipartContent(request)) {
                //将对象转换
                List<FileItem> list = fileUpload.parseRequest(request);
                //遍历
                for (FileItem fileItem : list) {
                    //判断普通文本数据
                    if (fileItem.isFormField()) {
                        //文本数据
                        //制定编码
                        request.setCharacterEncoding("UTF-8");
                        //封装数据到request对象中---格式：名称+值
                        request.setAttribute(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                    } else {
                        //文件域
                        //获取文件名
                        String fileName = fileItem.getName();
                        //获取文件上传流
                        InputStream in = fileItem.getInputStream();
                        //保证文件不重名，设置唯一名称
                        String id = UUID.randomUUID().toString();
                        //文件名与UUID拼接
                        String endName = id + "#" + fileName;
                        //判断是否上传了文件
                        if (fileName == null || "".equals(fileName)) {
                            endName = "无图片";
                        } else {
                            //获取文件上传路径
                            ServletContext context = request.getSession().getServletContext();
                            String path = context.getRealPath("/attachment");
                            //创建文件
                            File file = new File(path, endName);
                            //文件上传
                            fileItem.write(file);
                            //清理垃圾
                            fileItem.delete();
                        }
                        //返回文件名称
                        return endName;
                    }
                }
            } else {
                System.out.println("当前表单不是文件上传表单");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
