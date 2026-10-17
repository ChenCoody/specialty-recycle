package com.example.lrb.pojo;

import com.example.lrb.enums.RoleEnums;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "user")
public class User {
    @Id private Integer id;
    private String name;
    private String password;
    private String avatar;
    private String location;
    private String sex;
    private Integer age;
    private String mobile;
    private RoleEnums role;
    private LocalDateTime createTime;
}
