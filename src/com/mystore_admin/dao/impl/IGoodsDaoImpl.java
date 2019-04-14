package com.mystore_admin.dao.impl;

import com.mystore_admin.dao.IGoodsDao;
import com.mystore_admin.eneity.Goods;
import com.mystore_admin.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class IGoodsDaoImpl implements IGoodsDao {
    private QueryRunner qr = new JdbcUtils().getQueryRunner();

    public List<Goods> getAllGoods() throws SQLException {
        return qr.query("select * from goods", new BeanListHandler<Goods>(Goods.class));
    }

    @Override
    public int addGoods(Goods goods) throws SQLException {
        System.out.println(goods.getName());
        return qr.update("insert into goods (name,price,image,`desc`,is_host,cid) values (?,?,?,?,?,?);",
                goods.getName(), goods.getPrice(), goods.getImage(), goods.getDesc(), goods.getIs_host(), goods.getCid());
    }

    @Override
    public int delGoods(int id) throws SQLException {
        return qr.update("DELETE FROM `goods` WHERE id=?;", id);
    }

    @Override
    public int updateGoods(Goods goods) throws SQLException {
        return qr.update("update goods set name=?,price=?,image=? ,`desc`=?,is_host=?,cid=? where id=?;",
                goods.getName(), goods.getPrice(), goods.getImage(), goods.getDesc(), goods.getIs_host(), goods.getCid(), goods.getId());
    }

    @Override
    public Goods getGoods(int id) throws SQLException {
        return qr.query("select * from Goods where id=?", new BeanHandler<Goods>(Goods.class), id);
    }

    @Override
    public List<Goods> getPageData(int current, int pageCount) throws SQLException {
        return qr.query("select * from goods limit ?,?", new BeanListHandler<Goods>(Goods.class), (current - 1) * pageCount, pageCount);
    }


    @Override
    public List<Goods> getSelPageData(int current, int pageCount, String name) throws SQLException {
        List<Goods> goodsList = qr.query("select * from goods where name like ? limit ?,?", new BeanListHandler<Goods>(Goods.class), "%" + name + "%", (current - 1) * pageCount, pageCount);
        return goodsList;
    }

    @Override
    public int getCount() throws SQLException {
        List<Goods> allGoods = getAllGoods();
        return allGoods.size();
    }

    @Override
    public int getSelCount(String name) throws SQLException {
        return qr.query("select * from goods where name like ?", new BeanListHandler<Goods>(Goods.class), "%" + name + "%").size();
    }
}