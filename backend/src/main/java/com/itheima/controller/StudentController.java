package com.itheima.controller;


import com.itheima.anno.LogOperation;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("Query the data of the students with page: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("Get the data of the student by id: {}", id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("Save the data of the student: {}", student);
        studentService.save(student);
        return Result.success();
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("Update the data of the student: {}", student);
        studentService.update(student);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping("{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("Delete the data of the students: {}", ids);
        studentService.detele(ids);
        return Result.success();
    }

    @LogOperation
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score){
        log.info("Update the violation of studnet: {}, score: {}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
