package com.dfyang.staticresource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private com.dfyang.staticresource.db1.dao.Test1Dao db1Dao;

    @Autowired
    private com.dfyang.staticresource.db2.dao.Test2Dao db2Dao;

    @GetMapping("/insert1")
    public String insert1(String id, String message) {
        db1Dao.insertTest(id, message);
        return "添加成功！";
    }

    @GetMapping("/insert2")
    public String insert2(String id, String msg) {
        db2Dao.insertTest(id, msg);
        return "添加成功！";
    }

    @GetMapping("/insert3")
    @Transactional
    public String insert3(String id, String message) {
        db1Dao.insertTest(id, message);
        int i = 1 / 0;
        db2Dao.insertTest(id, message);
        return "添加成功！";
    }

    @GetMapping("/insert4")
    @Transactional
    public String insert4(String id, String message) {
        db2Dao.insertTest(id, message);
        int i = 1 / 0;
        db1Dao.insertTest(id, message);
        return "添加成功！";
    }

}
