package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "node_like")
public class NodeLike {
    @Id private Integer id;
    private Integer nodeId;
    private Integer userId;
    private Integer score;
    private LocalDateTime createTime;
}
