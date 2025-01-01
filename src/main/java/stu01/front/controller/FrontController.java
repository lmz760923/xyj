package stu01.front.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import stu01.model.EntityList;
import stu01.model.Product;
import stu01.service.UserDaoImpl;

@Controller
@RequestMapping("/")
public class FrontController {
	@Autowired
	private UserDaoImpl udo;
	@GetMapping("index")
	ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("layui/front/index");
		mv.addObject("carousel",udo.carousel());
		mv.addObject("categories", udo.categories());
		mv.addObject("about", udo.get_options("about"));
		mv.addObject("about_content",udo.get_options("about_content"));
		mv.addObject("flowme", udo.get_options("flowme"));
		mv.addObject("flowme_content",udo.get_options("flowme_content"));
		mv.addObject("company",udo.get_options("company"));
		return mv;
	}
	@GetMapping("products/{id}")
    ModelAndView products(@PathVariable Integer id) {
	ModelAndView mv=new ModelAndView();
	mv.addObject("cateid", id);
	mv.setViewName("layui/front/products");
	return mv;
    }
   
   @GetMapping("productdetail/{cateid}")
   @ResponseBody
   ResponseEntity<EntityList<Product>> productdetail(@PathVariable Integer cateid) throws SQLException{
	   return ResponseEntity.status(HttpStatus.OK).body(udo.productdetail(cateid));
   }

}
