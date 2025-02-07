package stu01.wx.control;
import stu01.wx.model.LoginForm;
import stu01.wx.model.LoginResult;
import stu01.utils.EncryptionUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import stu01.model.t_order_info;
import stu01.mybatis.t_order_infoMapper;
import stu01.service.UserDaoImpl;
@RestController
@RequestMapping("/wx")
public class WXcontroller {
	@Resource
	private t_order_infoMapper mapper;
	
	@Value("${wx.pay.appid}")
	private String appid;
	@Value("${wx.pay.secret}")
	private String secret;
	@Autowired
	private UserDaoImpl udo;
	@Autowired
	private RestTemplate rest;
	@PostMapping("/login")
	public ResponseEntity<LoginResult> wxlogin(@RequestBody LoginForm loginform,HttpServletRequest request){
		System.out.println("wxlogin:"+ request.getSession().getAttribute("wxlogin"));
		Map<String, Object> map=null ;
		ObjectMapper objectMapper = new ObjectMapper();
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+loginform.getCode()+"&grant_type=authorization_code";
		System.out.println(url);
		if (udo.auth(loginform.getUser(), loginform.getPassword())) {
			request.getSession().setAttribute("wxlogin", true);
			ResponseEntity<String> result = rest.getForEntity(url, String.class);
	        System.out.println(result.getStatusCode());
	        System.out.println(result.getBody());
	        System.out.println(result.getHeaders());
	        
	        try {
				map= objectMapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>() {});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LoginResult rst=new LoginResult();
		rst.setErrorcode("0");
		rst.setOpenid((String)map.get("openid"));
		rst.setSession_key((String)map.get("session_key"));
		rst.setUnionid("unionid");
		return ResponseEntity.status(HttpStatus.OK).body(rst);
		
	}
	@PostMapping("/test")
	public ResponseEntity<Integer>testmybatis(@RequestBody t_order_info entity){
		Integer r=0;
				
		r=  mapper.addOrder2(entity);
		  
		 
		 
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	
	@PostMapping("/select")
	public ResponseEntity<List<t_order_info>> select(@RequestBody t_order_info entity){
			List<t_order_info> ret=mapper.select(entity.getTitle(), entity.getOrder_no());
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
	
	@PostMapping("/select2")
	public ResponseEntity<List<t_order_info>> select2(@RequestBody t_order_info entity){
			List<t_order_info> ret=mapper.select2(entity);
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
	
	@PostMapping("/select3")
	public ResponseEntity<List<t_order_info>> select3(@RequestBody t_order_info entity){
			List<t_order_info> ret=mapper.select2(entity);
			return ResponseEntity.status(HttpStatus.OK).body(ret);
		}
	

}
