package com.mystore_admin.service;

import com.mystore_admin.eneity.Admin;
import com.mystore_admin.eneity.Goods;
import com.mystore_admin.eneity.Goodstype;
import com.mystore_admin.eneity.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface IAdminService {
    //登入
    Admin login(String name, String pwd) throws Exception;

    //获取所有管理员用户
    List<Admin> getAllAdmin() throws SQLException;

    //获取所有商品
    public List<Goods> getAllGoods() throws SQLException;

    //添加商品
    public int addGoods(Goods goods) throws SQLException;

    //删除商品
    public int delGoods(int id) throws SQLException;

    //修改商品
    public int updateGoods(Goods goods) throws SQLException;

    //获取所有商品分类
    List<Goodstype> getAllType() throws SQLException;

    //根据id获取商品
    Goods getGoods(int id) throws SQLException;

    //获取分页数据
    PageBean getPageData(int current,int pageCount) throws SQLException;

    //获取查询的分页数据
    PageBean getSelPageData(int current,int pageCount,String name) throws SQLException;

    //添加管理员
    void  addAdmin(Admin admin) throws SQLException;
}
