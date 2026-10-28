package com.aaa.dao;

import com.aaa.entity.Clazz;

public interface ClazzDao {

    /**
     * 根据班级号查询班级信息 + 老师信息。
     *  会在这个结果上再带学生列表（同一个方法，改 resultMap 和 SQL）。
     */
    Clazz getById(Integer cid);
}
