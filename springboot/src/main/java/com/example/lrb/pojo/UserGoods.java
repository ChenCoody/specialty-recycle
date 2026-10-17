package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@Table(name = "user_goods")
public class UserGoods {
    @Id private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer merchantId;
    private LocalDateTime createTime;
    @Transient private User user;
    @Transient private Goods goods;
}
