package com.mystore_admin.dao;

import com.mystore_admin.eneity.Goodstype;

import java.sql.SQLException;
import java.util.List;

public interface IGoodsTypeDao {
    List<Goodstype> getAllType() throws SQLException;

    int addGoodsType(Goodstype goodstype) throws SQLException;
}
