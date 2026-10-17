package com.example.lrb.service;
import com.example.lrb.pojo.Category;
import com.example.lrb.vo.JsonResult;
import java.util.List;
public interface CategoryService {
    JsonResult<List<Category>> list(Category category);
    JsonResult<Category> insert(Category category);
    JsonResult<Category> update(Category category);
    JsonResult<Category> delete(Integer id);
}
