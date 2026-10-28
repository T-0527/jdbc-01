package com.aaa;

import com.aaa.dao.ClazzDao;
import com.aaa.entity.Clazz;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

import com.aaa.entity.Student;
public class ClazzDaoTest {

    private SqlSession openSession() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }

    @Test
    public void test01WithTeacher() throws Exception {
        try (SqlSession session = openSession()) {
            ClazzDao mapper = session.getMapper(ClazzDao.class);
            Clazz clazz = mapper.getById(1);
            System.out.println("班级名:" + clazz.getCname()
                    + ";对应的老师:" + clazz.getTeacher().getTname());
        }
    }

    @Test
    public void test02WithStudents() throws Exception {
        try (SqlSession session = openSession()) {
            ClazzDao mapper = session.getMapper(ClazzDao.class);
            Clazz clazz = mapper.getById(2);
            System.out.println("班级:" + clazz.getCname() + "下的所有学生如下:");
            for (Student s : clazz.getStudents()) {
                System.out.println("学号:" + s.getSid() + ";名字:" + s.getSname());
            }
        }
    }
}