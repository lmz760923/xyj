package stu01.webservice;
import stu01.model.User;
import javax.jws.WebService;
import javax.jws.WebParam;
@WebService
public interface UserService {
	public	User getUserByName(@WebParam(name = "userName")String userName);
}
