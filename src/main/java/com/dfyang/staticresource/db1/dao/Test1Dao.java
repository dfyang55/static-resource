package com.dfyang.staticresource.db1.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Test1Dao {
    void insertTest(@Param("id") String id, @Param("message") String message);

    int count();
}
