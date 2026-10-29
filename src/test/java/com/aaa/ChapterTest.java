package com.aaa;

import com.aaa.dao.EmployeeDao;
import com.aaa.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class ChapterTest {

    private SqlSessionFactory factory() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        return new SqlSessionFactoryBuilder().build(reader);
    }

    /** #{}：注入串只是一个普通值，查不到数据 */
    @Test
    public void test01Safe() throws Exception {
        try (SqlSession session = factory().openSession()) {
            List<Employee> list = session.getMapper(EmployeeDao.class)
                    .selectByNameAndDept("陆欣", "市场部");
            System.out.println("正常查询条数=" + list.size());

            List<Employee> inject = session.getMapper(EmployeeDao.class)
                    .selectByNameAndDept("不存在的人", "x' or '1'='1");
            System.out.println("注入尝试条数=" + inject.size());   // 0
        }
    }

    /** ${}：只能传列名； */
    @Test
    public void test02OrderBy() throws Exception {
        String col = "salary";
        if (!Arrays.asList("emp_id", "emp_name", "age", "salary").contains(col)) {
            throw new IllegalArgumentException("非法列名: " + col);
        }
        try (SqlSession session = factory().openSession()) {
            session.getMapper(EmployeeDao.class).selectAllOrderBy(col)
                    .forEach(System.out::println);
        }
    }
}