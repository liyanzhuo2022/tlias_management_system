package com.itheima.controller;


import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.pojo.StudentOption;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("Get the employee job data");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("Get the employee gender data");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("Get the students count data by class");
        StudentOption studentOption = reportService.getStudentCountData();
        return Result.success(studentOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("Get the students degree data.");
        List<Map<String, Object>> list = reportService.getStudentDegreeData();
        return Result.success(list);
    }
}
