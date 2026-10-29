package com.aaa;

import com.aaa.dao.EmployeeDao;
import com.aaa.entity.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class PageTest {

    @Test
    public void PageTest() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis_config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sessionFactory.openSession();

        EmployeeDao mapper = session.getMapper(EmployeeDao.class);

        // 第 8 页，每页 5 条
        PageHelper.startPage(8, 5);
        List<Employee> list = mapper.selectAll();

        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("当前页的数据:" + pageInfo.getList());
        System.out.println("总页码:" + pageInfo.getPages());
        System.out.println("页码序列:" + Arrays.toString(pageInfo.getNavigatepageNums()));

        session.close();
    }
}