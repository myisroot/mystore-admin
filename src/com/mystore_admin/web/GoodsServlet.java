package com.mystore_admin.web;

import com.alibaba.fastjson.JSON;
import com.mystore_admin.eneity.Goods;
import com.mystore_admin.eneity.Goodstype;
import com.mystore_admin.eneity.PageBean;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;
import com.mystore_admin.utils.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
    private IAdminService ias = new AdminServiceImpl();

    public String getAllGoods(HttpServletRequest req, HttpServletResponse resp) {
        List<Goods> allGoods = null;
        try {
            allGoods = ias.getAllGoods();
        } catch (SQLException e) {
            System.err.println("发生错误！");
        }
        //对集合进行返转
        Collections.reverse(allGoods);
        req.setAttribute("allGoods", allGoods);
        return "/admin/main.jsp";
    }

    public String getUpdateGoodsType(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Goods goods = ias.getGoods(new Integer(req.getParameter("id")));
        List<Goodstype> allType = ias.getAllType();
        req.setAttribute("goods", goods);
        req.setAttribute("allType", allType);
        return "/admin/edit.jsp";
    }

    public String deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        ias.delGoods(new Integer(id));
        String path = getPageData(req, resp);
        return path;
    }

    public String getGoodsType(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<Goodstype> allType = ias.getAllType();
        req.setAttribute("allGoodsType", allType);
        return "/admin/add.jsp";
    }

    public String addGoods(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Goods goods = new Goods();
        Map<String, Object> map = fileUpload(req, "/admin/pimages/", null, null, null, Arrays.asList("jpg", "png", "gif"));
        if (null != map) {
            BeanUtils.populate(goods, map);
            //添加到数据库
            ias.addGoods(goods);
        } else {
            System.out.println("格式错误");
            resp.getWriter().write("<script>alert('格式错误！');</script>");
        }
        return getPageData(req, resp);
    }

    public String updateGoods(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Goods goods = new Goods();
        Map<String, Object> map = fileUpload(req, "/admin/pimages/", null, null, null, null);
        BeanUtils.populate(goods, map);
        //添加到数据库
        ias.updateGoods(goods);
        return getPageData(req, resp);
    }

    public String getPageData(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String current = req.getParameter("current");
        PageBean pageData = ias.getPageData(new Integer(current), 5);
        //集合返转
        Collections.reverse(pageData.getPageGoodsList());
        req.getSession().setAttribute("num", new Integer(current));
        req.setAttribute("pageData", pageData);
        String str = JSON.toJSONString(pageData.getPageGoodsList());
        resp.getWriter().write(str);
        return "/admin/main.jsp";
    }


//    private Goods fileUnload(Goods goods, HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            SmartUpload upload = new SmartUpload();//创建组件对象
//            upload.initialize(this.getServletConfig(), req, resp);// 初始化
//            upload.setMaxFileSize(1 * 1024 * 1024);// 每个文件最大1M
//            upload.setTotalMaxFileSize(10 * 1024 * 1024);// 总共最大10M
//            upload.setAllowedFilesList("jpg,png,gif");// 允许上传jpg,png
//            upload.upload();
//            Files uploadFiles = upload.getFiles();
//            String fileName = uploadFiles.getFile(0).getFileName();
//            fileName = new String(fileName.getBytes("gbk"), "utf-8");
//            uploadFiles.getFile(0).saveAs("/admin/pimages/" + fileName, File.SAVEAS_VIRTUAL);//将文件保存在磁盘根目录下的upload文件夹
//        } catch (Exception e) {
//        }
//
//        return goods;
//    }

}
