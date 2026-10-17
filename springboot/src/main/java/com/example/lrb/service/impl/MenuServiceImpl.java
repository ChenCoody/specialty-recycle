package com.example.lrb.service.impl;

import com.example.lrb.dao.MenuDao;
import com.example.lrb.pojo.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl {
    private final MenuDao menuDao;

    public MenuServiceImpl(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public List<Menu> getMenusByRoleId(Integer roleId) {
        return menuDao.getMenusByRoleId(roleId);
    }
}
