package com.mystore_admin.test;

import com.mystore_admin.dao.IGoodsDao;
import com.mystore_admin.dao.impl.IGoodsDaoImpl;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DelGoodsSer")
public class DelGoodsSer extends HttpServlet {
    IAdminService igs=new AdminServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            igs.delGoods(new Integer(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/ShowGoodsSer").forward(req,resp);
    }
}
