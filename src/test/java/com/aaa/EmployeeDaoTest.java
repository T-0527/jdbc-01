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

public class EmployeeDaoTest {

    private SqlSession openSession() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }

    @Test
    public void test01IfWhere() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee q = new Employee();
            q.setEmpName("王浩");
            q.setDept("研发部");
            List<Employee> list = mapper.selectByCondition(q);
            System.out.println("if+where 条数=" + list.size());
            list.forEach(System.out::println);
        }
    }

    @Test
    public void test02Choose() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee q = new Employee();
            q.setEmpName("王浩");
            q.setDept("市场部");
            List<Employee> list = mapper.selectByCondition2(q);
            System.out.println("choose 条数=" + list.size());
            list.forEach(System.out::println);
        }
    }

    @Test
    public void test03Trim() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            Employee q = new Employee();
            q.setEmpName("王浩");
            q.setDept("市场部");
            List<Employee> list = mapper.selectByCondition3(q);
            System.out.println("trim 条数=" + list.size());
        }
    }

    @Test
    public void test04Update() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);

            Employee e = new Employee();
            e.setEmpId(1);
            e.setSalary(9999.0);
            e.setHiredate(java.sql.Date.valueOf("2030-01-01"));

            int row = mapper.update(e);
            session.commit();
            System.out.println("update 影响行数=" + row);

            Employee db = mapper.getById(1);
            System.out.println("改后：" + db);
        }
    }

    @Test
    public void test05Foreach() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            List<Employee> list = mapper.selectByNames(
                    Arrays.asList("王浩", "李萌", "张伟"));
            System.out.println("foreach 条数=" + list.size());
            list.forEach(System.out::println);
        }
    }

    @Test
    public void test06Like() throws Exception {
        try (SqlSession session = openSession()) {
            EmployeeDao mapper = session.getMapper(EmployeeDao.class);
            List<Employee> list = mapper.selectByNameLike("王");
            System.out.println("模糊查询条数=" + list.size());
            list.forEach(System.out::println);
        }
    }
}