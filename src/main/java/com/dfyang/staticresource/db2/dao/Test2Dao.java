package com.dfyang.staticresource.db2.dao;

        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

@Repository
public interface Test2Dao {
    void insertTest(@Param("id") String id, @Param("msg") String msg);
}
