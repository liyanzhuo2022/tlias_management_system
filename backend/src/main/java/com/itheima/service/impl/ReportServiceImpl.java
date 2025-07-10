package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        JobOption jobOption = new JobOption(jobList, dataList);
        return jobOption;
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public StudentOption getStudentCountData() {
        List<Map<String, Object>> list = studentMapper.countStudentClazzData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("count")).toList();
        return new StudentOption(clazzList, dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> list = studentMapper.countStudentDegreeData();
        return list;
    }
}
