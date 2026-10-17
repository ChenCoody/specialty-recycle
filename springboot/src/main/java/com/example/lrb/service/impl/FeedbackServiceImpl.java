package com.example.lrb.service.impl;
import com.example.lrb.dao.FeedbackDao; import com.example.lrb.pojo.Feedback; import com.example.lrb.service.FeedbackService; import com.example.lrb.vo.JsonResult; import org.springframework.stereotype.Service; import java.time.LocalDateTime; import java.util.List;
@Service public class FeedbackServiceImpl implements FeedbackService {
 private final FeedbackDao dao; public FeedbackServiceImpl(FeedbackDao dao){this.dao=dao;}
 public JsonResult<Feedback> insert(Feedback f){f.setCreateTime(LocalDateTime.now());dao.insertSelective(f);return JsonResult.ok(f);}
 public JsonResult<Feedback> update(Feedback f){if(f.getReplyContent()!=null)f.setReplyTime(LocalDateTime.now());dao.updateByPrimaryKeySelective(f);return JsonResult.ok(f);}
 public JsonResult<Feedback> delete(Integer id){Feedback f=dao.selectByPrimaryKey(id);return dao.deleteByPrimaryKey(id)>0?JsonResult.ok(f):JsonResult.notFound("反馈不存在");}
 public JsonResult<List<Feedback>> list(Feedback f){return JsonResult.ok(f==null?dao.queryList():dao.select(f));}
 public JsonResult<List<Feedback>> listByUserId(Integer id){Feedback f=new Feedback();f.setUserId(id);return JsonResult.ok(dao.select(f));}
}
