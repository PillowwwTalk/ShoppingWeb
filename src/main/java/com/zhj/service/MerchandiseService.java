package com.zhj.service;

import com.zhj.pojo.Merchandise;
import com.zhj.pojo.Page;

import java.util.List;

public interface MerchandiseService {
    public void addMerchandise(Merchandise merchandise);

    public void delMerchandise(Integer id);

    public void  updateMerchandise(Merchandise merchandise);

    public Merchandise queryMerchandiseByid(Integer id);

    public List<Merchandise> queryMerchandise();

    Page<Merchandise> page(int pageNo, int pageSize);

    Page<Merchandise> pageByPrice(int pageNo, int pageSize , int min , int max);
}
