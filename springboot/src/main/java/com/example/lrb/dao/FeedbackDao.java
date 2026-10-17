package com.example.lrb.dao;

import com.example.lrb.pojo.Feedback;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 * @date 2022-03-21 21:09
 */
public interface FeedbackDao extends Mapper<Feedback> {
    List<Feedback> queryList();

}
