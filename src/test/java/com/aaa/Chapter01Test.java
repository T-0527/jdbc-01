package com.aaa;

import com.aaa.dao.EmployeeDao;
import com.aaa.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class Chapter01Test {

    private SqlSessionFactory factory() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        return new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void test01DefaultName() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectAge2(20, 30);
            System.out.println("20~30 岁条数=" + list.size());
        }
    }

    @Test
    public void test02Param() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectAge(20, 30);
            System.out.println("20~30 岁条数=" + list.size());
        }
    }
}
