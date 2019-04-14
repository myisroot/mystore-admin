package com.mystore_admin.test;

import com.mystore_admin.dao.IGoodsTypeDao;
import com.mystore_admin.dao.impl.GoodsTypeImpl;
import com.mystore_admin.eneity.Goods;
import com.mystore_admin.eneity.Goodstype;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/UpdateGoodsSer")
public class UpdateGoodsSer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAdminService igs=new AdminServiceImpl();
        IGoodsTypeDao igt=new GoodsTypeImpl();
        try {
            Goods goods = igs.getGoods(new Integer(req.getParameter("id")));
            req.setAttribute("goods",goods);
            List<Goodstype> allType = igt.getAllType();
            req.setAttribute("allType",allType);
//            igs.updateGoods(goods);
            req.getRequestDispatcher("/admin/edit.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
