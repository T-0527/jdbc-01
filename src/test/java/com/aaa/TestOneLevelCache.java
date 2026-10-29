package com.aaa;

import com.aaa.dao.EmployeeDao;
import com.aaa.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

public class TestOneLevelCache {

    @Test
    public void testOneLevelCache() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

        // ===== session1 =====
        SqlSession session1 = factory.openSession();
        System.out.println("===== 第1次查询：执行SQL =====");
        session1.getMapper(EmployeeDao.class).getById(1);

        System.out.println("===== 第2次查询：不执行SQL（一级缓存） =====");
        session1.getMapper(EmployeeDao.class).getById(1);

        System.out.println("===== 第3次查询：不执行SQL（一级缓存） =====");
        session1.getMapper(EmployeeDao.class).getById(1);
        session1.close();                                   // 一级缓存随 session 消失

        // ===== session2 =====
        SqlSession session2 = factory.openSession();
        EmployeeDao dao = session2.getMapper(EmployeeDao.class);

        System.out.println("===== 第4次查询：新session，执行SQL =====");
        dao.getById(1);

        System.out.println("===== 第5次查询：不执行SQL（session2 的一级缓存） =====");
        dao.getById(1);

        System.out.println("===== 中间执行 update =====");
        Employee e = new Employee();
        e.setEmpId(1);
        e.setSalary(8888.0);
        dao.update(e);                                      // CUD 会清空一级缓存
        session2.commit();

        System.out.println("===== 第6次查询：缓存已清，执行SQL =====");
        dao.getById(1);

        System.out.println("===== 第7次查询：不执行SQL =====");
        dao.getById(1);

        System.out.println("===== 中间 clearCache() =====");
        session2.clearCache();

        System.out.println("===== 第8次查询：缓存已清，执行SQL =====");
        dao.getById(1);

        System.out.println("===== 第9次查询：不执行SQL =====");
        dao.getById(1);

        session2.close();
    }
}
