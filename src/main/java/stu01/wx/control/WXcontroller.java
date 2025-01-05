package stu01.wx.control;
import stu01.wx.model.LoginForm;
import stu01.wx.model.LoginResult;
import stu01.utils.EncryptionUtil;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import stu01.service.UserDaoImpl;
@RestController
@RequestMapping("/wx")
public class WXcontroller {
	@Value("${wx.appid}")
	private String appid;
	@Value("${wx.secret}")
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
		String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+EncryptionUtil.decrypt(secret)+"&js_code="+loginform.getCode()+"&grant_type=authorization_code";
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

}
