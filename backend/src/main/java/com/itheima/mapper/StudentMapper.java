package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(String name, Integer degree, Integer clazzId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "VALUES (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, #{violationScore}, #{createTime}, #{updateTime})")
    void save(Student student);

    Student getStudentById(Integer id);

    void update(Student student);

    void deleteIds(@Param("ids") List<Integer> ids);

    void violation(Integer id, Integer score);

    @MapKey("clazz")
    List<Map<String, Object>> countStudentClazzData();

    List<Map<String, Object>> countStudentDegreeData();
}
