package com.example.lrb.service.impl;
import com.example.lrb.dao.CategoryDao; import com.example.lrb.pojo.Category; import com.example.lrb.service.CategoryService; import com.example.lrb.vo.JsonResult; import org.springframework.stereotype.Service; import java.util.List;
@Service public class CategoryServiceImpl implements CategoryService {
 private final CategoryDao dao; public CategoryServiceImpl(CategoryDao dao){this.dao=dao;}
 public JsonResult<List<Category>> list(Category c){return JsonResult.ok(c==null?dao.selectAll():dao.select(c));}
 public JsonResult<Category> insert(Category c){dao.insertSelective(c);return JsonResult.ok(c);}
 public JsonResult<Category> update(Category c){dao.updateByPrimaryKeySelective(c);return JsonResult.ok(c);}
 public JsonResult<Category> delete(Integer id){Category c=dao.selectByPrimaryKey(id);return dao.deleteByPrimaryKey(id)>0?JsonResult.ok(c):JsonResult.notFound("分类不存在");}
}
