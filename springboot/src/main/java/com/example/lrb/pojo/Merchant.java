package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "merchant")
public class Merchant {
    @Id private Integer id;
    private Integer userId;
    private String hometown;
    private String idcard;
    private String address;
    private String storeName;
    private String products;
}
