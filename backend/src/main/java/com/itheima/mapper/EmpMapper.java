package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);


    void delete(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map> countEmpGenderData();

    int getEmpNumberByDept(Integer id);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}

