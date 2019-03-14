package com.baizhi.util;

import com.baizhi.dao.LuceneDao;
import com.baizhi.dao.LuceneDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConf {
    @Bean
    public LuceneDao luceneDao(){

        return new LuceneDaoImpl();
    }
}
