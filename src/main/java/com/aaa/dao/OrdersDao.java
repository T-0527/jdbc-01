package com.aaa.dao;

import com.aaa.entity.Orders;

import java.util.List;

public interface OrdersDao {

    /** 根据订单号查询 */
    Orders getByNo(String no);

    /** 查询所有（用 resultMap 映射） */
    List<Orders> getAll();

    /** 查询所有（用 SQL 别名映射，对比用，可删） */
    List<Orders> getAllByAlias();
}