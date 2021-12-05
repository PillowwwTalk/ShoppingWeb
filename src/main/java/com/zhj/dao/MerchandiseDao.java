package com.zhj.dao;

import com.zhj.pojo.Merchandise;
import java.util.List;
public interface MerchandiseDao {
    public int addMerchandise(Merchandise merchandise);
    public int delMerchandiseByid(Integer id);
    public int updateMerchandise(Merchandise merchandise);
    public Merchandise queryMerchandiseByid(Integer id);
    public List<Merchandise> queryMerchandise();

    Integer queryForPageTotalCount();

    List<Merchandise> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Merchandise> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
