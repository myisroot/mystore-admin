package com.mystore_admin.dao;

import com.mystore_admin.eneity.Goods;

import java.sql.SQLException;
import java.util.List;

public interface IGoodsDao {
    public List<Goods> getAllGoods() throws SQLException;

    public int addGoods(Goods goods) throws SQLException;

    public int delGoods(int id) throws SQLException;

    public int updateGoods(Goods goods) throws SQLException;

    Goods getGoods(int id) throws SQLException;

    //获取分页数据
    List<Goods> getPageData(int current, int pageCount) throws SQLException;

    List<Goods> getSelPageData(int current, int pageCount, String name) throws SQLException;


    int getCount() throws SQLException;

    int getSelCount(String name) throws SQLException;
}
