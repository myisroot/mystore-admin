package com.mystore_admin.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        String path = null;
        Class<?> clazz = this.getClass();
        try {
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            if (method != null) {
                path = (String) method.invoke(this, req, resp);
                if (path != null) {
                    req.getRequestDispatcher(path).forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     HttpServletRequest req   请求对象
     String filePath  指定文件的上传路径相对于web目录
     String bufferPath 指定临时缓存的目录
     Integer SizeThreshold 设置缓冲区大小
     Integer FileSizeMax 设置一个完整的请求的文件大小
     List<String> list 指定文件上传格式
     */
    public Map<String, Object> fileUpload(HttpServletRequest req, String filePath, String bufferPath, Integer SizeThreshold, Integer FileSizeMax, List<String> list) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        //判断form是否设置为二进制的上传格式
        boolean flag = ServletFileUpload.isMultipartContent(req);
        //获取上传目录的绝对路径
        String path = req.getServletContext().getRealPath("/" + filePath + "/");
        if (flag) {
            //缓冲区
            ////磁盘对象：采用默认临界值和系统临时文件夹构造文件项工厂对象。
            //创建DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            if (null != SizeThreshold) {
                //设置临时缓冲区大小
                diskFileItemFactory.setSizeThreshold(SizeThreshold);
            }
            //判断是否要创建临时目录
            if (bufferPath != null) {
                File file = new File(bufferPath);
                if (!file.exists()) {
                    file.mkdirs();
                    //设置临时的缓存路径
                    diskFileItemFactory.setRepository(file);
                }
            }
            //将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象的任务
            //实例化ServletFileUpload对象
            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
            if (null != FileSizeMax) {
                //设置一个完整的请求的文件大小
                upload.setFileSizeMax(FileSizeMax);
            }
            try {
                //解析form表单的所有属性
                //解析上传文件的multipart/form-data流，返回按照传输顺序解析出的FileItem实例列表
                List<FileItem> items = upload.parseRequest(req);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    //处理普通的表单字段
                    if (item.isFormField()) {
                        //获取普通的input属性和值
                        String fieldName = item.getFieldName();
                        String val = item.getString("utf-8");
                        //添加到map
                        map.put(fieldName, val);
                    } else {
                        //处理表单文件的字段
                        String fileName = item.getName();
                        //设置文件上传格式
                        if (null!=list) {
                            //判断上传格式是否正确
                            String s = fileName.substring(fileName.lastIndexOf(".") + 1);
                            if (!list.contains(s)) {
                                return null;
                            }
                        }
                        if (fileName != null && !fileName.equals("")) {
                            map.put(item.getFieldName(), fileName);
                            try {
                                //上传到文件目录下
                                //创建文件
                                File fullFile = new File(item.getName());
                                //绝对路径
                                File saveFile = new File(path, fullFile.getName());
                                //上传
                                item.write(saveFile);
                            } catch (FileExistsException e) {
//                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (FileUploadBase.SizeLimitExceededException e) {
                System.err.println("不能超过文件大小限制！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }



}
