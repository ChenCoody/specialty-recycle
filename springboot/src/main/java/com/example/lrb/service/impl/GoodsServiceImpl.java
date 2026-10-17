package com.example.lrb.service.impl;
import com.example.lrb.dao.GoodsDao; import com.example.lrb.pojo.Goods; import com.example.lrb.service.GoodsService; import com.example.lrb.vo.JsonResult; import org.springframework.stereotype.Service; import java.time.LocalDateTime; import java.util.List;
@Service public class GoodsServiceImpl implements GoodsService {
 private final GoodsDao dao; public GoodsServiceImpl(GoodsDao dao){this.dao=dao;}
 public JsonResult<Goods> insert(Goods g){g.setCreateTime(LocalDateTime.now());dao.insertSelective(g);return JsonResult.ok(g);}
 public JsonResult<Goods> updateInventory(Goods g){dao.updateByPrimaryKeySelective(g);return JsonResult.ok(g);}
 public JsonResult<List<Goods>> listByUserId(Integer id){Goods g=new Goods();g.setUserId(id);return JsonResult.ok(dao.select(g));}
 public JsonResult<List<Goods>> list(Goods g){return JsonResult.ok(g==null?dao.selectAll():dao.select(g));}
 public JsonResult<Goods> query(Integer id){Goods g=dao.selectByPrimaryKey(id);return g==null?JsonResult.notFound("商品不存在"):JsonResult.ok(g);}
 public JsonResult<Goods> delete(Integer id){Goods g=dao.selectByPrimaryKey(id);return dao.deleteByPrimaryKey(id)>0?JsonResult.ok(g):JsonResult.notFound("商品不存在");}
}
