package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam.getName(), clazzQueryParam.getBegin(), clazzQueryParam.getEnd());
        clazzList.forEach(clazz -> {
            if (clazz.getBeginDate() != null & clazz.getEndDate() != null) {
                LocalDate today = LocalDate.now();
                if (today.isBefore(clazz.getBeginDate())) {
                    clazz.setStatus("未开班");
                } else if (today.isAfter(clazz.getEndDate())) {
                    clazz.setStatus("已结课");
                } else {
                    clazz.setStatus("在读中");
                }
            } else {
                clazz.setStatus("状态不明");
            }
        });
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        if (clazzHasStudent(id)) {
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        } else {
            clazzMapper.deleteById(id);
        }
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.list(null, null, null);
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.save(clazz);
    }

    private boolean clazzHasStudent(Integer id) {
        int studentNum = clazzMapper.getStudentNumByClazz(id);
        return studentNum > 0;
    }
}
