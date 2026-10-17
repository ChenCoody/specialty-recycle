package com.example.lrb.service.impl;

import com.example.lrb.common.utils.JwtUtils;
import com.example.lrb.common.utils.ResponseJsonUtils;
import com.example.lrb.dao.MerchantDao;
import com.example.lrb.dao.UserDao;
import com.example.lrb.enums.RoleEnums;
import com.example.lrb.pojo.Merchant;
import com.example.lrb.pojo.User;
import com.example.lrb.service.UserService;
import com.example.lrb.vo.JsonResult;
import com.example.lrb.vo.RegisterUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final MerchantDao merchantDao;
    public UserServiceImpl(UserDao userDao, MerchantDao merchantDao) { this.userDao = userDao; this.merchantDao = merchantDao; }

    @Override public JsonResult<User> login(User input, HttpServletResponse response) {
        if (input == null || input.getName() == null || input.getPassword() == null) return JsonResult.badRequest("用户名和密码不能为空");
        User query = new User(); query.setName(input.getName()); query.setPassword(input.getPassword());
        User user = userDao.selectOne(query);
        if (user == null) return JsonResult.badRequest("用户名或密码错误");
        user.setPassword(null);
        ResponseJsonUtils.setToken(response, JwtUtils.generateToken(user.getId().toString(), user));
        return JsonResult.ok(user);
    }

    @Transactional
    @Override public JsonResult<Object> register(RegisterUserVo input) {
        if (input == null || input.getName() == null || input.getPassword() == null) return JsonResult.badRequest("注册信息不完整");
        User exists = new User(); exists.setName(input.getName());
        if (userDao.selectCount(exists) > 0) return JsonResult.badRequest("用户名已存在");
        User user = new User(); user.setName(input.getName()); user.setPassword(input.getPassword()); user.setLocation(input.getLocation());
        user.setSex(input.getSex()); user.setMobile(input.getMobile()); user.setRole(input.getRegisterType() == null ? RoleEnums.USER : input.getRegisterType());
        try { if (input.getAge() != null && !input.getAge().isEmpty()) user.setAge(Integer.valueOf(input.getAge())); } catch (NumberFormatException ignored) { }
        user.setCreateTime(LocalDateTime.now()); userDao.insertSelective(user);
        if (user.getRole() == RoleEnums.MERCHANT) {
            Merchant merchant = new Merchant(); merchant.setUserId(user.getId()); merchant.setHometown(input.getHometown()); merchant.setIdcard(input.getIdcard());
            merchant.setAddress(input.getAddress()); merchant.setStoreName(input.getStoreName()); merchant.setProducts(input.getProducts()); merchantDao.insertSelective(merchant);
        }
        user.setPassword(null); return JsonResult.ok("注册成功", user);
    }
    @Override public JsonResult<List<User>> getUserAll(String name) { return JsonResult.ok(userDao.queryUserAll(name)); }
    @Override public JsonResult<User> delete(Integer id) { User user = userDao.selectByPrimaryKey(id); return userDao.deleteByPrimaryKey(id) > 0 ? JsonResult.ok(user) : JsonResult.notFound("用户不存在"); }
    @Override public JsonResult<User> getUserInfo(Integer id) { User user = userDao.selectByPrimaryKey(id); if (user == null) return JsonResult.notFound("用户不存在"); user.setPassword(null); return JsonResult.ok(user); }
    @Override public JsonResult<User> updatePassword(Integer id, String oldPassword, String newPassword) {
        User user = userDao.selectByPrimaryKey(id); if (user == null) return JsonResult.notFound("用户不存在");
        if (oldPassword == null || !oldPassword.equals(user.getPassword())) return JsonResult.badRequest("原密码错误");
        user.setPassword(newPassword); userDao.updateByPrimaryKeySelective(user); user.setPassword(null); return JsonResult.ok("密码修改成功", user);
    }
}
