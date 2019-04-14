package com.mystore_admin.web;

import com.mystore_admin.eneity.Admin;
import com.mystore_admin.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AdminLoginSer")
public class AdminLoginSer extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            //获取用户对象
            Admin admin = new AdminServiceImpl().login(req.getParameter("username"), req.getParameter("password"));
            session.setAttribute("user",admin);
            resp.sendRedirect("admin/admin_index.jsp");
            //铺货异常发生异常时回显异常信息
        } catch (Exception e) {
            if (e.getMessage().equals("用户名或者密码错误")){
                req.setAttribute("err",e.getMessage());
                req.getRequestDispatcher("admin/admin_login.jsp").forward(req,resp);
            }else {
                e.printStackTrace();
            }
        }
    }
}
