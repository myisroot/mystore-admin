package com.mystore_admin.test;

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

@WebServlet("/addGoodsTypeSer")
public class addGoodsTypeSer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IAdminService ids = new AdminServiceImpl();
        List<Goodstype> allType = null;
        try {
            //获取所有商品的类型
            allType = ids.getAllType();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("allGoods",allType);

        req.getRequestDispatcher("/admin/add.jsp").forward(req, resp);
    }
}
