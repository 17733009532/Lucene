package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity {
    private String id;
    private String name;
    private String price;
    private String describe;
    private String picture;
    private String status;
    private String datamanufacture;
    private String origin;
}
