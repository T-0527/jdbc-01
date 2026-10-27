package com.aaa;

import com.aaa.dao.DeptDao;
import com.aaa.entity.Dept;

import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        DeptDao deptdao=new DeptDao();
        List<Dept> list=deptdao.findAll();
        //打印部门信息
        for(Dept d:list){
            System.out.println(d.getId()+"\t"+d.getName());
        }
        int row=deptdao.update(2,"财务部");
        if(row>0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
    }
}
