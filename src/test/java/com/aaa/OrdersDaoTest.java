package com.aaa;

import com.aaa.dao.OrdersDao;
import com.aaa.entity.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class OrdersDaoTest {

    private SqlSession openSession() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }

    @Test
    public void test01GetByNo() throws Exception {
        try (SqlSession session = openSession()) {
            OrdersDao mapper = session.getMapper(OrdersDao.class);
            Orders orders = mapper.getByNo("aaaa");
            System.out.println(orders);
        }
    }

    @Test
    public void test02GetAll() throws Exception {
        try (SqlSession session = openSession()) {
            OrdersDao mapper = session.getMapper(OrdersDao.class);
            List<Orders> all = mapper.getAll();
            all.forEach(System.out::println);
        }
    }

    @Test
    public void test03Alias() throws Exception {
        try (SqlSession session = openSession()) {
            OrdersDao mapper = session.getMapper(OrdersDao.class);
            List<Orders> all = mapper.getAllByAlias();
            all.forEach(System.out::println);
        }
    }
}