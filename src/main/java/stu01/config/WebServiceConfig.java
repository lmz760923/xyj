package stu01.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import stu01.webservice.UserService;

@Configuration
public class WebServiceConfig {
	@Bean(name=Bus.DEFAULT_BUS_ID)
	SpringBus springBus() {
		return new SpringBus();
	}

	@Bean(name="wbsBean")
	ServletRegistrationBean<CXFServlet> dispatcherServlet() {
	ServletRegistrationBean<CXFServlet> wbsServlet=new ServletRegistrationBean<CXFServlet>(new CXFServlet(),"/wbs/*");
	return wbsServlet;
	}

    @Bean
    Endpoint endpointPurchase(SpringBus springBus, UserService userService) {
		EndpointImpl endpoint=new EndpointImpl(springBus,userService);
		endpoint.publish("/user-server");
		System.out.println("service:http://localhost:8080/wbs/user-server");
		return endpoint;
	}
	
}
