package com.example.lrb.service;
import com.example.lrb.pojo.User;
import com.example.lrb.vo.JsonResult;
import com.example.lrb.vo.RegisterUserVo;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
public interface UserService {
    JsonResult<User> login(User user, HttpServletResponse response);
    JsonResult<Object> register(RegisterUserVo user);
    JsonResult<List<User>> getUserAll(String name);
    JsonResult<User> delete(Integer id);
    JsonResult<User> getUserInfo(Integer id);
    JsonResult<User> updatePassword(Integer id, String oldPassword, String newPassword);
}
