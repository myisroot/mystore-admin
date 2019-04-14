package com.mystore_admin.test;

import com.mystore_admin.eneity.Goods;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/addGoodsSer")
public class addGoodsSer extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAdminService ids = new AdminServiceImpl();
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> infoMap = req.getParameterMap();
        Goods goods = new Goods();
        try {
            //封装成一个商品对象
            BeanUtils.populate(goods,infoMap);
            //添加到数据库
            ids.addGoods(goods);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("/ShowGoodsSer").forward(req,resp);
    }
}
