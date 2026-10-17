package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "node_comment")
public class NodeComment {
    @Id private Integer id;
    private Integer nodeId;
    private Integer parentId;
    private Integer fromId;
    private Integer toId;
    private String content;
    private LocalDateTime createTime;
    @Transient private String fromName;
    @Transient private String fromAvatar;
    @Transient private String toName;
    @Transient private List<NodeComment> childrenComment;
}
