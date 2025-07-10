package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping
    public Result list(){
        log.info("Query all the data");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("Delete the data with id:" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Dept dept){
        log.info("Save the data:" + dept);
        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
        log.info("Query the data with id:" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @LogOperation
    @PutMapping("/{id}")
    public Result update(@RequestBody Dept dept){
        log.info("Update the data:" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
