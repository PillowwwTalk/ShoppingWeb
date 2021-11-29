package com.zhj.test;

import com.zhj.dao.MerchandiseDao;
import com.zhj.pojo.Merchandise;
import com.zhj.service.MerchandiseService;
import com.zhj.service.impl.MerchandiseServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MerchandiseServiceImplTest {
        private MerchandiseService merchandiseService = new MerchandiseServiceImpl();
    @Test
    public void addMerchandise() {
        merchandiseService.addMerchandise(new Merchandise(null,"洗发水","飘柔官方旗舰店",new BigDecimal(10000),400000, 1000, null));
    }

    @Test
    public void delMerchandise() {
        merchandiseService.delMerchandise(1);
    }

    @Test
    public void updateMerchandise() {
        merchandiseService.updateMerchandise(new Merchandise(1,"洗发水","飘柔官方旗舰店",new BigDecimal(10000),400000, 0, null));
    }

    @Test
    public void queryMerchandiseByid() {
        System.out.println(merchandiseService.queryMerchandiseByid(1));
    }

    @Test
    public void queryMerchandise() {
        System.out.println(merchandiseService.queryMerchandise());
    }
}