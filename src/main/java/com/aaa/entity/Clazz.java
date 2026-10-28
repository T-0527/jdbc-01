package com.aaa.entity;

import lombok.Data;
import java.util.List;
@Data
public class Clazz {
    private Integer cid;
    private String cname;
    private Integer teacherId;

    /** 该班级对应的老师 */
    private Teacher teacher;
    private List<Student> students;
}
