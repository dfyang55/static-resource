package com.dfyang.staticresource.config1;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.dfyang.staticresource.db2.dao", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class DB2Config {

    // 配置数据源
    @Bean(name = "db2DataSource")
    public DataSource db2DataSource(DB2ConfigProperty properties) throws SQLException {
        //创建数据源
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(properties.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(properties.getPassword());
        mysqlXaDataSource.setUser(properties.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //将数据源交给Atomikos进行管理
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("db2DataSource");
        xaDataSource.setMinPoolSize(properties.getMinPoolSize());
        xaDataSource.setMaxPoolSize(properties.getMaxPoolSize());
        xaDataSource.setMaxLifetime(properties.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(properties.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(properties.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(properties.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(properties.getMaxIdleTime());
        xaDataSource.setTestQuery(properties.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/db2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(
            @Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
