package com.example.lrb.pojo;

import lombok.Data;
import java.util.List;

@Data
public class Menu {
    private Integer id;
    private String authName;
    private String path;
    private Integer parentId;
    private Integer order;
    private List<Menu> children;
}
