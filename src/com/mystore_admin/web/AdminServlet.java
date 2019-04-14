package com.mystore_admin.web;

import com.alibaba.fastjson.JSON;
import com.mystore_admin.eneity.Admin;
import com.mystore_admin.service.IAdminService;
import com.mystore_admin.service.impl.AdminServiceImpl;
import com.mystore_admin.utils.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@WebServlet("/AdminServlet")

public class AdminServlet extends BaseServlet {

    private IAdminService ias = new AdminServiceImpl();

    public String getAllAdmin(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<Admin> allAdmin = ias.getAllAdmin();
        req.setAttribute("allAdmin",allAdmin);
        return "admin/account.jsp";
    }

    public String addAdmin(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Admin admin=new Admin();
        admin.setId(UUID.randomUUID().toString());
        admin.setUsername(req.getParameter("username"));
        admin.setPassword(req.getParameter("password"));
        ias.addAdmin(admin);
        List<Admin> allAdmin = ias.getAllAdmin();
        Collections.reverse(allAdmin);
        resp.getWriter().write(JSON.toJSONString(allAdmin));
        return null;
    }
}
