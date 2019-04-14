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
import java.util.Map;

@WebServlet("/GoodsEditSer")
public class GoodsEditSer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        IAdminService igs=new AdminServiceImpl();
        Map<String, String[]> map = req.getParameterMap();
        Goods goods=new Goods();
        try {
            BeanUtils.populate(goods,map);
            igs.updateGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("/ShowGoodsSer").forward(req,resp);
    }
}
