package com.aaa;

import com.aaa.dao.EmployeeDao;
import com.aaa.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class TestTwoLevelCache {

    @Test
    public void test2() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession session = sessionFactory.openSession();
        System.out.println("第一次查询 id=2----执行sql");
        EmployeeDao mapper1 = session.getMapper(EmployeeDao.class);
        Employee e1 = mapper1.getById(2);
        System.out.println(e1);

        System.out.println("第二次查询 id=2----不执行sql（一级缓存）");
        EmployeeDao mapper2 = session.getMapper(EmployeeDao.class);
        Employee e2 = mapper2.getById(2);
        System.out.println(e2);

        session.close();   // 关键：close 把结果写进二级缓存

        SqlSession session2 = sessionFactory.openSession();
        System.out.println("第三次查询 id=2----不执行sql（二级缓存）");
        EmployeeDao mapper3 = session2.getMapper(EmployeeDao.class);
        Employee e3 = mapper3.getById(2);
        System.out.println(e3);

        System.out.println("第四次查询 id=2----不执行sql（二级缓存）");
        EmployeeDao mapper4 = session2.getMapper(EmployeeDao.class);
        Employee e4 = mapper4.getById(2);
        System.out.println(e4);

        System.out.println("第五次查询 id=2----不执行sql（二级缓存）");
        EmployeeDao mapper5 = session2.getMapper(EmployeeDao.class);
        Employee e5 = mapper5.getById(2);
        System.out.println(e5);

        session2.close();
    }
}
