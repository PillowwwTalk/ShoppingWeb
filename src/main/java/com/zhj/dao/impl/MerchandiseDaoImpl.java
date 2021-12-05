package com.zhj.dao.impl;

import com.zhj.dao.MerchandiseDao;
import com.zhj.pojo.Merchandise;

import java.util.List;

public class MerchandiseDaoImpl extends BaseDao implements MerchandiseDao {
    @Override
    public Merchandise queryMerchandiseByid(Integer id) {
        String sql="select `id`,`name`, `shop`, `price`, `sales`, `stock`, `img_path` imgPath from merchandise where id=?";
        return queryForOne(Merchandise.class, sql, id);
    }

    @Override
    public int addMerchandise(Merchandise merchandise) {
        String sql="insert into merchandise(`name`, `shop`, `price`, `sales`, `stock`, `img_path`) values(?,?,?,?,?,?)";
        return update(sql,merchandise.getName(),merchandise.getShop(),merchandise.getPrice(),merchandise.getSales(),
                merchandise.getStock(),merchandise.getImgPath());
    }

    @Override
    public int delMerchandiseByid(Integer id) {
        String sql="delete from merchandise where id =?";
        return update(sql,id);
    }

    @Override
    public int updateMerchandise(Merchandise merchandise) {
        String sql = "update merchandise set `name`=?, `shop`=?, `price`=?, `sales`=?, `stock`=?, `img_path`=? where id=?";
        return update(sql,merchandise.getName(),merchandise.getShop(),merchandise.getPrice(),merchandise.getSales(),
                merchandise.getStock(),merchandise.getImgPath(),merchandise.getId());
    }

    @Override
    public List<Merchandise> queryMerchandise() {
        String sql="select `id`, `name`, `shop`, `price`, `sales`, `stock`, `img_path` from merchandise";
        return queryForList(Merchandise.class, sql);
    }
    @Override
    public List<Merchandise> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `shop` , `price` , `sales` , `stock` , `img_path` imgPath from  merchandise limit ?,?";
        return queryForList(Merchandise.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from merchandise";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from merchandise where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min ,max);
        return count.intValue();
    }

    @Override
    public List<Merchandise> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `shop` , `price` , `sales` , `stock` , `img_path` imgPath from  merchandise " +
                "where price between ? and ? order by price limit ?,?";
        return queryForList(Merchandise.class,sql,min, max, begin,pageSize);
    }
}
