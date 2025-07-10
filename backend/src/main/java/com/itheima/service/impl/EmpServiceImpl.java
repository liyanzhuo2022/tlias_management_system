package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@Slf4j
@Service
 public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList= empMapper.list(empQueryParam.getName(), empQueryParam.getGender(), empQueryParam.getBegin(), empQueryParam.getEnd());
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public List<Emp> list() {
        return empMapper.list(null, null, null, null);
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        if(e != null){
            log.info("登录成功！员工信息：{}", e);
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", e.getId());
            dataMap.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(e.getId(), e.getUsername(),
                    e.getName(), jwt);
            return loginInfo;
        }
        return null;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // int i = 1/0;

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
        empExprMapper.delete(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        empExprMapper.delete(Arrays.asList(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }


}