package com.baizhi.service;

import com.baizhi.entity.Commodity;
import org.apache.lucene.search.ScoreDoc;

import java.util.List;

public interface CommodityService {
    void inser(Commodity commodity);
    List<Commodity> queryall(String name);
}
