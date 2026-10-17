package com.example.lrb.service;
import com.example.lrb.pojo.Feedback;
import com.example.lrb.vo.JsonResult;
import java.util.List;
public interface FeedbackService {
    JsonResult<Feedback> insert(Feedback feedback);
    JsonResult<Feedback> update(Feedback feedback);
    JsonResult<Feedback> delete(Integer id);
    JsonResult<List<Feedback>> list(Feedback feedback);
    JsonResult<List<Feedback>> listByUserId(Integer userId);
}
