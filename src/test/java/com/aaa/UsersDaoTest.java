package com.aaa;

import com.aaa.dao.UsersDao;
import com.aaa.entity.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class UsersDaoTest {
    /** 公共开头：读配置 → 建工厂 → 开 session */
    private SqlSession openSession() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }
    @Test
    public void test1() throws Exception{
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session=sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        UsersDao mapper = session.getMapper(UsersDao.class);
        List<Users> all = mapper.get_all();
        System.out.println(all);
    }

    @Test
    public void test2() throws Exception{
        //1.读取配置文件---解析配置文件. mybatis知道连接的数据库和账号密码。 知道映射文件中内。
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //1. 获取SqlSession对象----理解为: Connection对象。类中包含对表的各种操作方法。
        SqlSession session=sessionFactory.openSession();
        //2.获取UsersDao的代理实现类对象。
        UsersDao mapper = session.getMapper(UsersDao.class);
        Users user=new Users();
        user.setName("王华");
        user.setAge(22);
        int row=mapper.insert(user);
        session.commit();
        System.out.println("影响行数=" + row + "，回填id=" + user.getId());
        session.close();
    }

    @Test
    public void testUpdate() throws Exception {
        try (SqlSession session = openSession()) {
            UsersDao mapper = session.getMapper(UsersDao.class);
            Users user = new Users();
            user.setId(3);
            user.setName("张三丰");
            user.setAge(30);
            int row = mapper.update(user);
            session.commit();
            System.out.println("修改行数=" + row);
        }
    }

    @Test
    public void testDelete() throws Exception {
        try (SqlSession session = openSession()) {
            UsersDao mapper = session.getMapper(UsersDao.class);
            int row = mapper.delete(6);
            session.commit();
            System.out.println("删除行数=" + row);
        }
    }
}
