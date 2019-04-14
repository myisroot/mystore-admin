package com.mystore_admin.dao.impl;

import com.mystore_admin.dao.IAdminDao;
import com.mystore_admin.eneity.Admin;
import com.mystore_admin.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements IAdminDao {
    private QueryRunner qr = new JdbcUtils().getQueryRunner();
    public Admin login(String name, String pwd) throws SQLException {
        return qr.query("select * from admin where username=? and password=?",new BeanHandler<Admin>(Admin.class),name,pwd);
    }

    @Override
    public List<Admin> getAllAdmin() throws SQLException {
        return qr.query("select * from admin",new BeanListHandler<Admin>(Admin.class));
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        System.out.println(admin);
        qr.update("insert into admin values(?,?,?);",admin.getId(),admin.getUsername(),admin.getPassword());
    }
}
