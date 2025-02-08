package stu01.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CustomMybatisConfig {

    @Bean(name = "sqlSessionFactoryRef1")
    SqlSessionFactory sqlSessionFactory(@Qualifier("data1Source") DataSource ds) throws Exception{
    	//使用MybatisSqlSessionFactoryBean 可以使用mybatis-BaseMapper中的方法
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**.xml"));
        return bean.getObject();
    }

    @Bean(name = "data1SqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryRef1") SqlSessionFactory sessionFactory) throws  Exception{
        return  new SqlSessionTemplate(sessionFactory);
    }

    @Bean(name = "data1TransactionManager")
    DataSourceTransactionManager transactionManager(@Qualifier("data1Source") DataSource ds){
        return new DataSourceTransactionManager(ds);
    }


    1

}
