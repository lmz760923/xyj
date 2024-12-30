package stu01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mediaconfig implements WebMvcConfigurer {
private String imagePath=System.getProperty("user.dir");

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// TODO Auto-generated method stub
	//WebMvcConfigurer.super.addResourceHandlers(registry);
	registry.addResourceHandler("/images/**").addResourceLocations("file:"+imagePath+"/");
}


}
