package com.example.lrb.service;
import com.example.lrb.pojo.Goods;
import com.example.lrb.vo.JsonResult;
import java.util.List;
public interface GoodsService {
    JsonResult<Goods> insert(Goods goods);
    JsonResult<Goods> updateInventory(Goods goods);
    JsonResult<List<Goods>> listByUserId(Integer userId);
    JsonResult<List<Goods>> list(Goods goods);
    JsonResult<Goods> query(Integer id);
    JsonResult<Goods> delete(Integer id);
}
