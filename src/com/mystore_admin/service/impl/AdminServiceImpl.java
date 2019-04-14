package com.mystore_admin.service.impl;

import com.mystore_admin.dao.IAdminDao;
import com.mystore_admin.dao.IGoodsDao;
import com.mystore_admin.dao.IGoodsTypeDao;
import com.mystore_admin.dao.impl.AdminDaoImpl;
import com.mystore_admin.dao.impl.GoodsTypeImpl;
import com.mystore_admin.dao.impl.IGoodsDaoImpl;
import com.mystore_admin.eneity.Admin;
import com.mystore_admin.eneity.Goods;
import com.mystore_admin.eneity.Goodstype;
import com.mystore_admin.eneity.PageBean;
import com.mystore_admin.service.IAdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements IAdminService {
    private IAdminDao adminDao = new AdminDaoImpl();
    private IGoodsDao igd = new IGoodsDaoImpl();
    private IGoodsTypeDao itd = new GoodsTypeImpl();

    public Admin login(String name, String pwd) throws Exception {
        Admin admin = adminDao.login(name, pwd);
        if (admin != null) {
            System.out.println("登入成功！");
        } else {
            throw new Exception("用户名或者密码错误");
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmin() throws SQLException {
        return adminDao.getAllAdmin();
    }

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        return igd.getAllGoods();
    }

    @Override
    public int addGoods(Goods goods) throws SQLException {
        return igd.addGoods(goods);
    }

    @Override
    public int delGoods(int id) throws SQLException {
        return igd.delGoods(id);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return igd.updateGoods(goods);
    }

    @Override
    public List<Goodstype> getAllType() throws SQLException {
        return itd.getAllType();
    }

    @Override
    public Goods getGoods(int id) throws SQLException {
        return igd.getGoods(id);
    }

    @Override
    public PageBean getPageData(int current,int pageCount) throws SQLException {
        PageBean pb = new PageBean();
        //获取一共有几条数据
        Integer count = igd.getCount();
        //一页显示多少条数据pageCount

        //获取一共有几页
        double currentCount = Math.ceil(1.0 * count / pageCount);

        //获取当前页面的数据
        List<Goods> pageData = igd.getPageData(current, pageCount);
        //设置当前页
        pb.setCurrent(current);

        //设置读取页面的数据
        pb.setPageGoodsList(pageData);

        //设置一共有几页
        pb.setPageCount((int) currentCount);

        //设置一共有几条数据
        pb.setAllCounts(count);
        return pb;
    }

    @Override
    public PageBean getSelPageData(int current, int pageCount,String name) throws SQLException {
        PageBean pb2 = new PageBean();
        //获取一共有几条数据
        Integer count = igd.getSelCount(name);
        //一页显示多少条数据pageCount
        //获取一共有几页
        double currentCount = Math.ceil(1.0 * count / pageCount);
        //获取当前页面的数据
        List<Goods> pageData = igd.getSelPageData(current,pageCount,name);
        //设置当前页
        pb2.setCurrent(current);
        //设置读取页面的数据
        pb2.setPageGoodsList(pageData);
        //设置一共有几页
        pb2.setPageCount((int) currentCount);
        //设置一共有几条数据
        pb2.setAllCounts(count);
        return pb2;
    }

    @Override
    public void addAdmin(Admin admin) throws SQLException {
        adminDao.addAdmin(admin);
    }


}
