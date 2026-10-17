package com.example.lrb.service;
import com.example.lrb.pojo.UserGoods;
import com.example.lrb.vo.JsonResult;
import java.util.List;
public interface UserGoodsService {
    JsonResult<UserGoods> insert(UserGoods userGoods);
    JsonResult<List<UserGoods>> listByPayUserId(Integer userId);
    JsonResult<List<UserGoods>> listByMerchantId(Integer merchantId);
}
