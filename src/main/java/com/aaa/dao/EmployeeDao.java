package com.aaa.dao;

import com.aaa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {

    /** 7.2.1 if + where：姓名、部门任意组合 */
    List<Employee> selectByCondition(Employee employee);

    /** 7.2.2 choose + when + otherwise：多个条件只取第一个成立的 */
    List<Employee> selectByCondition2(Employee employee);

    /** 7.2.3 trim + if */
    List<Employee> selectByCondition3(Employee employee);

    /** 7.2.4 set + if：只更新传了值的字段 */
    int update(Employee employee);

    /** 7.2.5 foreach：姓名 in (...) */
    List<Employee> selectByNames(@Param("nameList") List<String> nameList);

    /** 7.2.6 模糊查询 */
    List<Employee> selectByNameLike(@Param("keyword") String keyword);

    /** 测试用：按主键查回来，验证 update 有没有改错字段 */
    Employee getById(Integer id);


    /** 9 分页用：全表查（排序字段一定要有，否则每页顺序可能不一样） */
    List<Employee> selectAll();


//     10
    List<Employee> selectByNameAndDept(@Param("name") String name,
                                       @Param("dept") String dept);

//     10
    List<Employee> selectAllOrderBy(@Param("col") String col);

    /** 11 方式一：不起名，XML 里只能用 param1 / param2 */
    List<Employee> selectAge2(Integer minAge, Integer maxAge);

    /** 11 方式二：@Param 起名 */
    List<Employee> selectAge(@Param("minAge") Integer minAge,
                             @Param("maxAge") Integer maxAge);
}
