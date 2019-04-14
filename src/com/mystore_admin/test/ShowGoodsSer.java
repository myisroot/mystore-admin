package com.mystore_admin.test;

import com.mystore_admin.eneity.Goods;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/ShowGoodsSer")
public class ShowGoodsSer extends HttpServlet {
    IAdminService ias = new AdminServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Goods> allGoods = null;
        try {
            allGoods = ias.getAllGoods();
        } catch (SQLException e) {
            System.err.println("发生错误！");
        }
        //对集合进行返转
        Collections.reverse(allGoods);
        req.setAttribute("allGoods",allGoods);

        req.getRequestDispatcher("/admin/main.jsp").forward(req, resp);
    }
}
