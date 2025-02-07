package stu01.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import stu01.Application;
import stu01.service.UserDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes=Application.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.yml")
public class mycontrollerTest {
	@Autowired
	UserDaoImpl ud;
    @Value("${spring.datasource.data1.driver-class-name}")
    private String name;
  	@Test
	public void testInsert() {
		
		System.out.println("testtesttest-------------------------------------");
	}
	
	
}
