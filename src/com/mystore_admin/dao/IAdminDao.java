package com.mystore_admin.dao;

import com.mystore_admin.eneity.Admin;

import java.sql.SQLException;
import java.util.List;

public interface IAdminDao  {
    Admin login(String name, String pwd)throws SQLException;

    List<Admin> getAllAdmin() throws SQLException;

    void addAdmin(Admin admin) throws SQLException;
}
