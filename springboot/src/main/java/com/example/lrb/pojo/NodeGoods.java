package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "node_goods")
public class NodeGoods {
    @Id private Integer id;
    private Integer nodeId;
    private Integer goodsId;
}
