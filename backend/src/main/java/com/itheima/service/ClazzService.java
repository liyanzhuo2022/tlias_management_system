package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> getAll();

    void save(Clazz clazz);
}
