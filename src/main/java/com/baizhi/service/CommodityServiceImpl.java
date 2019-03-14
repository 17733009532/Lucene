package com.baizhi.service;

import com.baizhi.dao.CommodityDao;
import com.baizhi.dao.LuceneDao;
import com.baizhi.entity.Commodity;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CommodityServiceImpl implements CommodityService{
    @Autowired
    private CommodityDao commoditydao;
    @Autowired
    private LuceneDao luceneDao;

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void inser(Commodity commodity) {
        commodity.setId(UUID.randomUUID().toString().replace("-",""));
        luceneDao.inser(commodity);
        commoditydao.inser(commodity);
    }

    @Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public List<Commodity> queryall(String name) {
        return luceneDao.queryall(name);
    }
}
