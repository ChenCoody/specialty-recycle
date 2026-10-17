package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Data
@Table(name = "feedback")
public class Feedback {
    @Id private Integer id;
    private Integer userId;
    private String content;
    private String images;
    private String replyContent;
    private LocalDateTime replyTime;
    private LocalDateTime createTime;
    @Transient private String name;
}
