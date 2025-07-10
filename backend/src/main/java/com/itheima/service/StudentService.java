package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    Student getStudentById(Integer id);

    void update(Student student);

    void detele(List<Integer> ids);

    void violation(Integer id, Integer score);
}
