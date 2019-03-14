package com.baizhi.controller;

import com.baizhi.entity.Commodity;
import com.baizhi.service.CommodityService;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/Commodity")
public class CommodityController {  ///Commodity/query
    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/query")
    @ResponseBody
    public List<Commodity> query(String name){
        return commodityService.queryall(name);
    }
    @RequestMapping("/inser")
    @ResponseBody
    public String inser(Commodity commodity, MultipartFile addFile, HttpServletRequest request){
        System.out.println("11"+commodity+"22"+addFile);
        String realPath =  request.getSession().getServletContext().getRealPath("/upload");
        System.out.println(realPath);
        String name = addFile.getOriginalFilename();
        System.out.println(name);
        new File(realPath).mkdir();
        try {
            addFile.transferTo(new File(realPath+"\\"+name));
            commodity.setPicture(name);
            commodityService.inser(commodity);
            return "ok";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "on";
    }
}
