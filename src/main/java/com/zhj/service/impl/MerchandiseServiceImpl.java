package com.zhj.service.impl;

import com.zhj.dao.MerchandiseDao;
import com.zhj.dao.impl.MerchandiseDaoImpl;
import com.zhj.pojo.Merchandise;
import com.zhj.pojo.Page;
import com.zhj.service.MerchandiseService;

import java.util.List;

public class MerchandiseServiceImpl implements MerchandiseService {
    private MerchandiseDao merchandiseDao = new MerchandiseDaoImpl();
    @Override
    public void addMerchandise(Merchandise merchandise) {
        merchandiseDao.addMerchandise(merchandise);
    }

    @Override
    public void delMerchandise(Integer id) {
        merchandiseDao.delMerchandiseByid(id);
    }

    @Override
    public void updateMerchandise(Merchandise merchandise) {
        merchandiseDao.updateMerchandise(merchandise);
    }

    @Override
    public Merchandise queryMerchandiseByid(Integer id) {
        return merchandiseDao.queryMerchandiseByid(id);
    }

    @Override
    public List<Merchandise> queryMerchandise() {
        return merchandiseDao.queryMerchandise();
    }

    @Override
    public Page<Merchandise> page(int pageNo, int pageSize) {
        Page<Merchandise> page = new Page<Merchandise>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = merchandiseDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据items
        List<Merchandise> items = merchandiseDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Merchandise> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Merchandise> page = new Page<Merchandise>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = merchandiseDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Merchandise> items = merchandiseDao.queryForPageItemsByPrice(begin,pageSize,min ,max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
