package stu01.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import stu01.model.User;

@Component
@WebService(targetNamespace="http://webservice.stu01/",endpointInterface="stu01.webservice.UserService")
public class UserImpl implements UserService {

	@Override
	public User getUserByName(@WebParam(name = "userName")String userName) {
		System.out.println("input parameter:"+userName);
		User user=new User();
		user.setId(1001);
		user.setName(userName);
		user.setPassword("xyz");
		return user;
	}

}
