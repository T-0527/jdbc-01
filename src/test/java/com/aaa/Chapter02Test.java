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

public class Chapter02Test {

    private SqlSessionFactory factory() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        return new SqlSessionFactoryBuilder().build(reader);
    }


    @Test
    public void test01Like() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectByNameLike("王");
            System.out.println("包含「王」的条数=" + list.size());
            list.forEach(System.out::println);
        }
    }


    @Test
    public void test02PrefixSuffix() throws Exception {
        try (SqlSession session = factory().openSession()) {
            EmployeeDao dao = session.getMapper(EmployeeDao.class);

            System.out.println("以「王」开头=" + dao.selectByNameLike("王").size());
            System.out.println("以「浩」结尾=" + dao.selectByNameLike("浩").size());
        }
    }


    @Test
    public void test03Inject() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectByNameLike("%' or '1'='1");
            System.out.println("注入尝试条数=" + list.size());   // 0
        }
    }


    @Test
    public void test04Empty() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectByNameLike("不存在的名字");
            System.out.println("空结果 size=" + list.size());
            System.out.println("list 是不是 null？" + (list == null));
        }
    }
}
