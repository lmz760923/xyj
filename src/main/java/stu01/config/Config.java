package stu01.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
 
@Configuration
public class Config {
	@Value("${spring.datasource.data1.jdbcUrl}")
	private String url;
	@Value("${spring.datasource.data1.username}")
	private String username;
	@Value("${spring.datasource.data1.password}")
	private String password;
	@Value("${spring.datasource.data1.driver-class-name}")
	private String dcn;
/*
    @Bean(name="datasource")
    DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dcn);
        return dataSource;
    }
*/
    @Bean(name = "data1Source")
    @Primary//主数据源
    //@ConfigurationProperties("spring.datasource.data1")
    DataSource dataSource1(){
    	
        //return DataSourceBuilder.create().build();
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dcn);

        return dataSource;
    }

    
    @Bean //JdbcTemplate模板
    @Autowired
    JdbcTemplate jdbcTemplate(@Qualifier("data1Source") DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}