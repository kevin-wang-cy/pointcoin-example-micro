package com.upbchain.pointcoin.examplemicro.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 
 * @author kevin.wang.cy@gmail.com
 *
 */


@Profile({"mysql"})
@Configuration
public class AliDruidDatasourceConfiguration {
    private final static Logger LOG = LoggerFactory.getLogger(AliDruidDatasourceConfiguration.class);
    
    @Autowired
    AliDruidDatasourceProperties aliDruidDatasourceProperties;
    
    @Bean
    @Primary
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();
        
        datasource.setUrl(aliDruidDatasourceProperties.getUrl());
        datasource.setUsername(aliDruidDatasourceProperties.getUsername());
        datasource.setPassword(aliDruidDatasourceProperties.getPassword());
        datasource.setDriverClassName(aliDruidDatasourceProperties.getDriverClassName());
        
        //configuration
        datasource.setInitialSize(aliDruidDatasourceProperties.getInitialSize());
        datasource.setMinIdle(aliDruidDatasourceProperties.getMinIdle());
        datasource.setMaxActive(aliDruidDatasourceProperties.getMaxActive());
        datasource.setMaxWait(aliDruidDatasourceProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(aliDruidDatasourceProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(aliDruidDatasourceProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(aliDruidDatasourceProperties.getValidationQuery());
        datasource.setTestWhileIdle(aliDruidDatasourceProperties.isTestWhileIdle());
        datasource.setTestOnBorrow(aliDruidDatasourceProperties.isTestOnBorrow());
        datasource.setTestOnReturn(aliDruidDatasourceProperties.isTestOnReturn());
        datasource.setPoolPreparedStatements(aliDruidDatasourceProperties.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(aliDruidDatasourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(aliDruidDatasourceProperties.getFilters());
        } catch (SQLException ex) {
            LOG.error("Exception occurs while settig Druid filters", ex);
        }
        datasource.setConnectionProperties(aliDruidDatasourceProperties.getConnectionProperties());
        
        return datasource;
    }
}
