package com.aaa;

import com.aaa.entity.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class AppTest {

    // 每次测试的固定开头：读配置 → 建工厂 → 开 session
    private SqlSession openSession() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        return sessionFactory.openSession();
    }

    // 2.7 查单条
    @Test
    public void test01() throws Exception {
        try (SqlSession session = openSession()) {
            Users user = session.selectOne("aaa.UserDao.get_id", 2);
            System.out.println(user);
        }
    }

    // 3.1 查全部
    @Test
    public void test02() throws Exception {
        try (SqlSession session = openSession()) {
            List<Users> list = session.selectList("aaa.UserDao.get_all");
            list.forEach(System.out::println);
        }
    }

    // 新增
    @Test
    public void test03() throws Exception {
        try (SqlSession session = openSession()) {
            Users user = new Users();
            user.setName("张三");
            user.setAge(18);

            int row = session.insert("aaa.UserDao.add", user);
            session.commit();                       // 增删改必须提交
            System.out.println("影响行数=" + row + ", 自增id=" + user.getId());
        }
    }

    // 删除
    @Test
    public void test04() throws Exception {
        try (SqlSession session = openSession()) {
            int row = session.delete("aaa.UserDao.delete", 4);
            session.commit();
            System.out.println("删除行数=" + row);
        }
    }

    // 修改
    @Test
    public void test05() throws Exception {
        try (SqlSession session = openSession()) {
            Users user = new Users();
            user.setId(5);
            user.setName("李四");
            user.setAge(88);

            int row = session.update("aaa.UserDao.update", user);
            session.commit();
            System.out.println("修改行数=" + row);
        }
    }
}