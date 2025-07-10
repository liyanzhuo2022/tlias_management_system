package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    Clazz getById(Integer id);

    void updateById(Clazz clazz);

    int getStudentNumByClazz(Integer id);

    void deleteById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "VALUES (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void save(Clazz clazz);
}
