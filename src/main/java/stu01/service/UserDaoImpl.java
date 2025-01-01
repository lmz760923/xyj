package stu01.service;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import stu01.model.Content;
import stu01.model.EntityList;
import stu01.model.Product;
import stu01.model.Category;
import stu01.model.User;
import stu01.model.carousel;
import stu01.model.cera;
import stu01.model.ctotalRow;
@Repository("userDao")
public class UserDaoImpl {
	@Resource(name="datasource")
	private DriverManagerDataSource ds;
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbc;
	
	public int insert(User user) throws SQLException {
		return jdbc.update("insert into users(name,password,email,created_at,updated_at) values(?,?,?,datetime('now','localtime'),datetime('now','localtime'))",user.getName(),user.getPassword(),user.getEmail());
		
	}
	
	public User login(User u) {
		return u;
		
	}
	
	public User getById(Integer userid) throws SQLException {
		
		return jdbc.queryForObject("select * from users where id=?",new Object[] {userid},new BeanPropertyRowMapper<User>(User.class));
		
	}
	
	public boolean auth(String user,String password) {
		Integer count=jdbc.queryForObject("select count(*) from users where name=? and password=?", new Object[] {user,password},Integer.class);
		return (count.intValue()==1);
		
	}
	
	public EntityList<User> userlist() throws SQLException {
		EntityList<User> li=new EntityList<User>();
		int c=0;
		li.setCode(0);
		li.setTotalRow(new ctotalRow(1, new cera(10, 1, 1)));
		li.setMsg("success");
		li.setData(jdbc.query("select * from users", new BeanPropertyRowMapper<User>(User.class)));
		c=jdbc.queryForObject("select count(*) from users", Integer.class);
		li.setCount(c);
		
		return li;
		
	}
	
	public EntityList<carousel> carousellist(int page,int limit) throws SQLException {
		EntityList<carousel> li=new EntityList<carousel>();
		
		li.setCode(0);
		li.setTotalRow(new ctotalRow(0, new cera(10, 1, 1)));
		li.setMsg("success");
		li.setData(jdbc.query("select * from carousel limit ?,?", new Object[] {(page-1)*limit, limit},new BeanPropertyRowMapper<carousel>(carousel.class)));
		li.setCount(jdbc.queryForObject("select count(*) from carousel",Integer.class));
		return li;
		
	}
	
	public List<carousel> carousel(){
		return jdbc.query("select * from carousel", new BeanPropertyRowMapper<carousel>(carousel.class));
	}
	
	public EntityList<Category> categorylist(int page,int limit) throws SQLException {
		EntityList<Category> li=new EntityList<Category>();
		
		li.setCode(0);
		li.setTotalRow(new ctotalRow(0, new cera(10, 1, 1)));
		li.setMsg("success");
		li.setData(jdbc.query("select * from categories limit ?,?", new Object[] {(page-1)*limit, limit},new BeanPropertyRowMapper<Category>(Category.class)));
		li.setCount(jdbc.queryForObject("select count(*) from categories", Integer.class));
		return li;
		
	}
	
	public List<Category> categories(){
		return jdbc.query("select * from categories", new BeanPropertyRowMapper<Category>(Category.class));
	}
	
	public EntityList<Product> productlist(int page,int limit) throws SQLException {
		EntityList<Product> li=new EntityList<Product>();
		
		li.setCode(0);
		li.setTotalRow(new ctotalRow(0, new cera(10, 1, 1)));
		li.setMsg("success");
		li.setData(jdbc.query("select * from products limit ?,?", new Object[] {(page-1)*limit, limit},new BeanPropertyRowMapper<Product>(Product.class)));
		li.setCount(jdbc.queryForObject("select count(*) from products", Integer.class));
		return li;}
	
	public EntityList<Product> productdetail(Integer id) throws SQLException {
		   EntityList<Product> li=new EntityList<Product>();
			
			li.setCode(0);
			li.setTotalRow(new ctotalRow(0, new cera(10, 1, 1)));
			li.setMsg("success");
			li.setData(jdbc.query("select * from products where category=?", new Object[] {id},new BeanPropertyRowMapper<Product>(Product.class)));
			li.setCount(jdbc.queryForObject("select count(*) from products where category=?",new Object[] {id},Integer.class));
			return li;
		
	}
	public EntityList<Content> contentlist(int page,int limit) throws SQLException {
		EntityList<Content> li=new EntityList<Content>();
		
		li.setCode(0);
		li.setTotalRow(new ctotalRow(0, new cera(10, 1, 1)));
		li.setMsg("success");
		li.setData(jdbc.query("select * from options limit ?,?", new Object[] {(page-1)*limit, limit},new BeanPropertyRowMapper<Content>(Content.class)));
		li.setCount(jdbc.queryForObject("select count(*) from options", Integer.class));
		return li;
		
	}
	
	public int adduser(User user) {
	 	return jdbc.update("insert into users(name,password,email,created_at,updated_at) values(?,?,?,datetime('now','localtime'),datetime('now','localtime')))", user.getName(),user.getPassword(),user.getEmail());
		
	}
	public int userdel(int userid) {
		return jdbc.update("delete from users where id=?",userid);
	}
	public int addcarousel(String file,String href) {
		return jdbc.update("insert into carousel(file,href,created_at,updated_at) values(?,?,datetime('now','localtime'),datetime('now','localtime'))", new Object[] {file,href});
	}
	public String delcarousel(Integer carid) {
		String hrf="";
		hrf=jdbc.queryForObject("select href from carousel where id=?", new Object[] {carid}, String.class);
		jdbc.update("delete from carousel where id=?", new Object[] {carid});
		return hrf;
	}
	public int addcategory(String category,String description,String file,String href) {
		return jdbc.update("insert into categories(category,description,created_at,updated_at,file,href) values(?,?,datetime('now','localtime'),datetime('now','localtime'),?,?)", new Object[] {category,description,file,href});
	}
	public String delcategory(Integer cateid) {
		String hrf="";
		hrf=jdbc.queryForObject("select href from categories where id=?", new Object[] {cateid}, String.class);
		jdbc.update("delete from categories where id=?", new Object[] {cateid});
		return hrf;
	}
	public int addproduct(Integer category,String description,String file,String href,String product) {
		return jdbc.update("insert into products(category,product,description,created_at,updated_at,file,href) values(?,?,?,datetime('now','localtime'),datetime('now','localtime'),?,?)", new Object[] {category,product,description,file,href});
	}
	public String delproduct(Integer id) {
		String hrf="";
		hrf=jdbc.queryForObject("select href from products where id=?", new Object[] {id}, String.class);
		jdbc.update("delete from products where id=?", new Object[] {id});
		return hrf;
	}
	public List<Category> getcategories() {
		return jdbc.query("select * from categories where 1=?",new Object[] {1}, new BeanPropertyRowMapper<Category>(Category.class));
	}
	public int addcontent(String name,String content) {
		return jdbc.update("insert into options(name,content,created_at,updated_at) values(?,?,datetime('now','localtime'),datetime('now','localtime'))", new Object[] {name,content});
	}
	public int delcontent(Integer id) {
		
		return jdbc.update("delete from options where id=?", new Object[] {id});
		
	}
	
    public int editcontent(Integer id,String content) {
		
		return jdbc.update("update options set content=? where id=?", new Object[] {content,id});
		
	}
    
    public String about() {
    	return jdbc.queryForObject("select content from options where name='about'", String.class);
    }
    public String about_content() {
    	return jdbc.queryForObject("select content from options where name='about_content'",String.class);
    }
    public String get_options(String name) {
    	return jdbc.queryForObject("select content from options where name=?", new Object[] {name}, String.class);
    }

}
