package com.mystore_admin.dao.impl;

import com.mystore_admin.dao.IGoodsTypeDao;
import com.mystore_admin.eneity.Goodstype;
import com.mystore_admin.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsTypeImpl  implements IGoodsTypeDao {
    private  QueryRunner qr = new JdbcUtils().getQueryRunner();
    @Override
    public List<Goodstype> getAllType() throws SQLException {
        return qr.query("select * from goodstype",new BeanListHandler<Goodstype>(Goodstype.class));
    }

    @Override
    public int addGoodsType(Goodstype goodstype) throws SQLException {
        return qr.update("insert into goodstype values(?,?); ",goodstype.getCid(),goodstype.getCname());
    }
}
