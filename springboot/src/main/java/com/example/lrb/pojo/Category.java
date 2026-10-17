package com.example.lrb.pojo;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "category")
public class Category {
    @Id private Integer id;
    private String name;
    private Integer sort;
}
