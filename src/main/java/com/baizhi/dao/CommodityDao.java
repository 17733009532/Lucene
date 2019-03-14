package com.baizhi.dao;

import com.baizhi.entity.Commodity;

import java.util.List;

public interface CommodityDao {
    void inser(Commodity commodity);
    List<Commodity> queryall();
}
