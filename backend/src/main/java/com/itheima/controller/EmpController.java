package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.time.LocalDate;

/**
 * 员工管理
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private ErrorPageRegistrar errorPageRegistrar;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("Query the data with page: {}", empQueryParam );
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/list")
    public Result list(){
        log.info("Query the data of all employees");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("Save an employee: {}", emp);
        empService.save(emp);
        return Result.success();
    }


    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("Delete employees by ids: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("Get an employee with id: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("Update an employee: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}