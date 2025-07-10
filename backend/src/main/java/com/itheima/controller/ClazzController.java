package com.itheima.controller;


import com.itheima.anno.LogOperation;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private EmpService empService;

    // 3.1 班级列表查询：该接口用于班级列表数据的条件分页查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("Query the data of the class with page: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("Get the data of the class with id: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @GetMapping("/list")
    public Result list(){
        log.info("Get the data of all classes");
        List<Clazz> clazzList = clazzService.getAll();
        return Result.success(clazzList);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("Update a clazz: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("Save the data of the class: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("Delete the data of the class with id: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
