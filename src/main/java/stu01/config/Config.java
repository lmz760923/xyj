package stu01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
 
@Configuration
public class Config {
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String dcn;

    @Bean(name="datasource")
    DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(dcn);
        return dataSource;
    }
    
    @Bean //JdbcTemplate模板
    @Autowired
    JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}