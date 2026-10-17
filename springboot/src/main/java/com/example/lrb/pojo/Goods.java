package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "goods")
public class Goods {
    @Id private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private String images;
    private String showImg;
    private BigDecimal price;
    private Integer inventory;
    private BigDecimal estimatedWeight;
    private String address;
    private String contactMobile;
    private LocalDateTime createTime;
    @Transient private User user;
    @Transient private Merchant merchant;
}
