package com.itheima.service;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ReportService {


    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    StudentOption getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}
