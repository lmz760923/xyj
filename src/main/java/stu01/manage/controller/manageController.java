package stu01.manage.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import stu01.service.*;
import stu01.model.ReturnCode;
import stu01.model.User;
import stu01.model.Content;
import stu01.model.EntityList;
import stu01.model.Product;
import stu01.model.Category;
import stu01.model.carousel;

@Controller
@RequestMapping("/manage")
public class manageController {
	private String realPath=System.getProperty("user.dir");
	@Autowired
	private UserDaoImpl udo;
	@GetMapping("/index")
	public ModelAndView manage(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/manage/login");
		Optional<Boolean> log=Optional.ofNullable((Boolean)request.getSession().getAttribute("login"));
		log.ifPresent(val->{if (val) mv.setViewName("layui/manage/index"); else {mv.setViewName("layui/manage/login");}});
		
		return mv;
	}
	
	
	@GetMapping("/login")
	public ModelAndView getlogin(){
		ModelAndView mav= new ModelAndView();
		mav.setViewName("layui/manage/login");
	
		return mav;
	}


	@PostMapping("/login")
	public ResponseEntity<String> postlogin(@RequestParam(name="user") String usr,@RequestParam String password, HttpServletRequest request){
		    if (udo.auth(usr,password)) {
		    request.getSession().setAttribute("user", "lmz");
		    request.getSession().setAttribute("login", true);
			return ResponseEntity.status(HttpStatus.OK).body(null);
			
		    }
		    else
		    {
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login fail");
		    }
		
	}

	@PostMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv=new ModelAndView();
		mv.setStatus(HttpStatus.OK);
		mv.setViewName("layui/manage/login");
		return mv;
	}
	
	@GetMapping("/users")
	public ModelAndView getusers(){
		ModelAndView mav= new ModelAndView();
		mav.setViewName("layui/manage/userlist");
	
		return mav;
	}
	
	@ResponseBody
	@GetMapping("/userlist")
	public EntityList<User> userlist() throws SQLException
	{
	  return udo.userlist();	
	}
	
	@ResponseBody
	@PostMapping("/adduser")
	
	public ResponseEntity<Integer> adduser(@RequestParam(name="username") String name,@RequestParam String email,@RequestParam(name="reg_password") String password) throws SQLException {
		return ResponseEntity.status(HttpStatus.OK).body(udo.insert(new User(name,password,email)));
	}
	@PostMapping("/userdel")
	public ResponseEntity<Integer> userdel(@RequestParam int userid) throws SQLException{
		return ResponseEntity.status(HttpStatus.OK).body(udo.userdel(userid));
	}
	@GetMapping("/carousel")
	public ModelAndView carousel() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/manage/carousel");
		return mv;
	}
	@GetMapping("/carousellist")
	@ResponseBody
	public EntityList<carousel> carousellist(@RequestParam Integer page,@RequestParam Integer limit) throws SQLException
	{
		return udo.carousellist(page, limit);
		
	}
	
	@PostMapping("/addcarousel")
	@ResponseBody
	public ResponseEntity<ReturnCode> addcarousel(@RequestParam MultipartFile file) throws IllegalStateException, IOException{
		
		String path=realPath+File.separator+"images/";
		File realFile = new File(path);
        if (!realFile.exists()) {
            System.out.println(realFile.mkdir());
        }
        
        String fullPath=path+file.getOriginalFilename();
		
        file.transferTo(new File(fullPath));
        udo.addcarousel(file.getOriginalFilename(), fullPath.replace(realPath, ""));
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,fullPath));
		
	}
	
	@PostMapping("/carouseldel")
	@ResponseBody
	public ResponseEntity<ReturnCode> delcarousel(@RequestParam Integer carouselid){
		String path=udo.delcarousel(carouselid);
		File del=new File(realPath+path);
		del.delete();
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"deleted"));
	}
	
	@GetMapping("/categorylist")
	@ResponseBody
	public EntityList<Category> categorylist(@RequestParam Integer page,@RequestParam Integer limit) throws SQLException
	{
		return udo.categorylist(page, limit);
		
	}
	
	@GetMapping("/categories")
	public ModelAndView categories() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/manage/categories.html");
		return mv;
	}
	
	@PostMapping("/addcategory")
	@ResponseBody
	public ResponseEntity<ReturnCode> addcategory(@RequestParam MultipartFile file,@RequestParam String category,@RequestParam String description) throws IllegalStateException, IOException{
		
		String path=realPath+File.separator+"images/";
		File realFile = new File(path);
        if (!realFile.exists()) {
            System.out.println(realFile.mkdir());
        }
        
        String fullPath=path+file.getOriginalFilename();
		
        file.transferTo(new File(fullPath));
        udo.addcategory(category,description,file.getOriginalFilename(), fullPath.replace(realPath, ""));
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,fullPath));
		
	}
	
	@PostMapping("/categorydel")
	@ResponseBody
	public ResponseEntity<ReturnCode> delcategory(@RequestParam Integer categoryid){
		String path=udo.delcategory(categoryid);
		File del=new File(realPath+path);
		del.delete();
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"deleted"));
	}
	
	@GetMapping("/products")
	public ModelAndView products() {
		List<Category> li=udo.getcategories();
		
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/manage/products.html");
		mv.addObject("categories", li);
		mv.addObject("mytest","ok");
		
		return mv;
	}
	
	@GetMapping("/productlist")
	@ResponseBody
	public EntityList<Product> productlist(@RequestParam Integer page,@RequestParam Integer limit) throws SQLException
	{
		return udo.productlist(page, limit);
		
	}
	@GetMapping("/contentlist")
	@ResponseBody
	public EntityList<Content> contentlist(@RequestParam Integer page,@RequestParam Integer limit) throws SQLException
	{
		return udo.contentlist(page, limit);
		
	}
	
	@PostMapping("/addproduct")
	@ResponseBody
	public ResponseEntity<ReturnCode> addproduct(@RequestParam MultipartFile file,@RequestParam Integer category,@RequestParam String description,@RequestParam String product) throws IllegalStateException, IOException{
		

		String path=realPath+File.separator+"images/";
		File realFile = new File(path);
        if (!realFile.exists()) {
            System.out.println(realFile.mkdir());
        }
        
        String fullPath=path+file.getOriginalFilename();
		
        file.transferTo(new File(fullPath));
        udo.addproduct(category,description,file.getOriginalFilename(), fullPath.replace(realPath, ""),product);
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,fullPath));
		
	}
	
	@PostMapping("/productdel")
	@ResponseBody
	public ResponseEntity<ReturnCode> delproduct(@RequestParam Integer productid){
		String path=udo.delproduct(productid);
		File del=new File(realPath+path);
		del.delete();
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"deleted"));
	}
	@GetMapping("/content")
	public ModelAndView content() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/manage/content");
		return mv;
	}
	
	@PostMapping("/addcontent")
	@ResponseBody
	public ResponseEntity<ReturnCode> addcontent(@RequestParam String name,@RequestParam String content) throws IllegalStateException, IOException{
	    udo.addcontent(name,content);
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"success"));
		
	}
	@PostMapping("/editcontent")
	@ResponseBody
	public ResponseEntity<ReturnCode> editcontent(@RequestParam Integer contentid,@RequestParam String content){
		udo.editcontent(contentid, content);
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"updated"));
	}
	@PostMapping("/contentdel")
	@ResponseBody
	public ResponseEntity<ReturnCode> delcontent(@RequestParam Integer contentid){
		udo.delcontent(contentid);
		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,"deleted"));
	}
	
	@PostMapping("/uploadvideo")
	@ResponseBody
	public ResponseEntity<ReturnCode> uploadvideo(@RequestParam MultipartFile file) throws IllegalStateException, IOException{
		
		String path=realPath+File.separator+"images/";
		File realFile = new File(path);
        if (!realFile.exists()) {
            System.out.println(realFile.mkdir());
        }
        
        String fullPath=path+file.getOriginalFilename();
		
        file.transferTo(new File(fullPath));

		return ResponseEntity.status(HttpStatus.OK).body(new ReturnCode(0,fullPath));
		
	}
	
}
