package com.example.lrb.service.impl;

import com.example.lrb.enums.RoleEnums;
import com.example.lrb.pojo.User;
import com.example.lrb.service.AdminService;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.JsonResult;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    public AdminServiceImpl(UserService userService) { this.userService = userService; }
    @Override public JsonResult<User> login(User user, HttpServletResponse response) {
        JsonResult<User> result = userService.login(user, response);
        if (result.isSuccess() && result.getData() != null && result.getData().getRole() != RoleEnums.ADMIN) {
            return JsonResult.forbidden("该账号不是管理员");
        }
        return result;
    }
}
