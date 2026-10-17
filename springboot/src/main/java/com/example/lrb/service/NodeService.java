package com.example.lrb.service;
import com.example.lrb.pojo.Node;
import com.example.lrb.vo.JsonResult;
import java.util.List;
public interface NodeService {
    JsonResult<Node> waitingAuthNode(Node node);
    JsonResult<Node> releasePoem(Node node);
    JsonResult<Node> auditReleaseNode(Node node);
    JsonResult<Node> auditDefeatNode(Node node);
    JsonResult<Node> defeatPoem(Node node);
    JsonResult<Node> insert(Node node);
    JsonResult<List<Node>> listByUserId(Integer userId);
    JsonResult<List<Node>> listAll(Node node);
    JsonResult<List<Node>> list(Node node);
    JsonResult<Node> query(Integer nodeId, Integer userId);
    JsonResult<Node> delete(Integer id);
}
