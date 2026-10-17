package com.example.lrb.pojo;

import com.example.lrb.enums.NodeEnums;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "node")
public class Node {
    @Id private Integer id;
    private Integer userId;
    private Integer categoryId;
    private String title;
    private String content;
    private String images;
    private String videos;
    private String showImg;
    private String address;
    private String city;
    private NodeEnums status;
    private NodeEnums auditStatus;
    private Integer pageview;
    private String authorizedUser;
    private LocalDateTime authorizedTime;
    private String authorizedFile;
    private LocalDateTime createTime;
    @Transient private User user;
    @Transient private Merchant merchant;
    @Transient private Integer likes;
    @Transient private Integer likeCount;
    @Transient private Boolean isLike;
    @Transient private List<NodeComment> commentList;
    @Transient private List<Goods> goodsList;
}
